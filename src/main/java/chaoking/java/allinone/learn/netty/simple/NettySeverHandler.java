package chaoking.java.allinone.learn.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 1、自定义一个Handler需要继承netty规定好的某个handler适配器
 * 2、這是我们自定义一个handler，才能称为一个handler
 */
public class NettySeverHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据
     * @param ctx 上下文对象 包含 管道pipeline ， 通道channel ，地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{

        // region 以主线程睡10s演示阻塞
        /*
        Thread.sleep(10*1000);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~   1    ",CharsetUtil.UTF_8));
        System.out.printf("go on ....");*/
        // endregion


        // region 常规演示服务端读取  但是这里如果是一个耗时较长的业务处理，会阻塞
        /*
        System.out.println("服务器读取线程："+Thread.currentThread().getName());
        System.out.println("server ctx ="+ctx);
        System.out.println("看看channel 和 pipeline 的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); // 本质是一个双向链表，出栈入栈

        // 将msg转成buffer
        // ByteBuf 是Netty提供的，不是NIO 的ByteBuffer
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端发送消息是：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址是：" + channel.remoteAddress());
        */
        //endregion

        // region 演示长耗时业务处理方案
        // 比如这里我们有一个耗时较长的业务 --> 异步执行 -->提交channel 对应的NIOEventLoop 的taskQueue中
        // region 解决方案1 用户程序自定义的普通任务

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~   2    ",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 上面的执行完才会执行这个任务 即 10s之后
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~   3    ",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // endregion

        // region 解决方案2 用户自定义定时任务 --> 该任务是提交到 scheduleTaskQueue 中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~   4    ",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },5, TimeUnit.SECONDS);
        // endregion

        // region 解决方案3 非当前 Reactor 线程调用 Channel 的各种方法
        //

        // endregion


        // endregion

    }

    // 数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        // writeAndFlush 是write+flush
        // 将数据写入到缓存，并刷新
        // 一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~",CharsetUtil.UTF_8));
    }

    // 处理异常，一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
