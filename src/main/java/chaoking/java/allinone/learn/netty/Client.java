package chaoking.java.allinone.learn.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger();


        ClientBootstrap bootstrap = new ClientBootstrap();

        // 线程池
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        // Socket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boos,worker));

        // 管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                return null;
            }
        });
    }
}
