package chaoking.java.allinone.learn.netty.websocket;

import chaoking.java.allinone.learn.netty.simple.NettySeverHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http2.Http2ChannelDuplexHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // 配置
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup)  // 设置两个线程组
                    .channel(NioServerSocketChannel.class)    // 使用 NioSocketChannel 作为服务器的通道实现
                    .handler(new LoggingHandler(LogLevel.INFO)) // 该handler在bossgroup上生效
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道测试对象（匿名对象）
                        // 给pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 因为基于Http协议，使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 是以块方式写，添加ChunkedWriteHandler 处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            // 说明：
                            // 1、http数据在传输过程中是分段的，HttpObjectAggregator，就是可以将多个段聚合
                            // 2、这就是为什么当浏览器发送大量数据时，会发出多次http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 说明：
                            // 1、对应websocket，它的数据是以 帧（frame）形式传递的
                            // 2、可以看到websocketFrame 下面有六个子类
                            // 3、浏览器请求时 ws://localhost:7000/hello 表示请求的uri
                            // 4、WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            // 自定义的handler，处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });

            ChannelFuture cf = serverBootstrap.bind(7000).sync();

            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
