package chaoking.java.allinone.learn.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 说明
         * 1、创建两个线程组 bossGroup 和 workerGroup
         * 2、bossGroup 只处理连接请求，真正的和客户端业务处理，会交给 workerGroup完成
         * 3、两个都是无限循环
         * 4、bossGroup 和 workerGroup 含有的子线程（NioEventLoop）的个数
         *      默认实际 cpu核数 * 2
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // 配置
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workerGroup)  // 设置两个线程组
                .channel(NioServerSocketChannel.class)    // 使用 NioSocketChannel 作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE,true) // 设置保持活动连接状态
                //.handler(null) // 该handler在bossgroup上生效
                .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道测试对象（匿名对象）
                    // 给pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 可以使用一个集合管理socketchannel，在推送消息时，可以将业务加入到各个channel对应的NIOEventLoop的taskQueue 或者 scheduleTaskQueue
                        System.out.println("这里是每个客户连接过来的channel，其hashcode为："+ch.hashCode());
                        ch.pipeline().addLast(new NettySeverHandler());
                    }
                }); // 给我们的 workerGroup 的 EventLoop 对应的管道设置处理器
            System.out.println(" 服务器 is ready .");

            // 绑定一个端口号并同步，生成一个 ChannelFuture 对象
            // 启动服务器并绑定端口
            ChannelFuture cf = bootstrap.bind(6668).sync();

            // 给cf注册监听器，监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(cf.isSuccess()){
                        System.out.println("监听端口 6668 成功");
                    }else{
                        System.out.println("监听端口 6668 失败");
                    }
                }
            });

            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
