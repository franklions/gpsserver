package gps.server.service;

import gps.server.mapper.GpsDataMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Logger;


public class TCPServer {
   static  Logger logger = Logger.getLogger("TCPServer");
    private static GpsDataMapper mapper ;
    public  static void SetMapper(GpsDataMapper m){
        mapper=m;
    }

    private static final String IP = "0.0.0.0";
    private static final int PORT = 12340;
    /** 用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认
    /** 业务出现线程大小 */
    protected static final int BIZTHREADSIZE = 4;
    /*
     * NioEventLoopGroup实际上就是个线程池,
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
     * 每一个NioEventLoop负责处理m个Channel,
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
     */
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void run() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
//                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                pipeline.addLast(new ObjectEncoder());
//                pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));

                pipeline.addLast(new TcpServerHandler(mapper));
            }
        });

        b.bind(IP, PORT).sync();

       logger.info ("TCP服务器已启动");
    }

    public static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }


}