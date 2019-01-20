package gps.server.service;

import gps.server.domain.GpsData;
import gps.server.mapper.GpsDataMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/1/20.
 */
public class TcpServerHandler  extends SimpleChannelInboundHandler<Object> {
    private final GpsDataMapper mapper;
    private Logger logger = Logger.getLogger("TcpServerHandler");
    public TcpServerHandler(GpsDataMapper mapper) {
        this.mapper = mapper;
    }

    // 从客户端接收到的消息
    /*
     *  服务器向指定客户端发送消息,只需要通过`map`将客户端的`id`和`channel`存起来
     *  在需要的时候通过`writeAndFlush`方法发送即可
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        try {
            GpsData data = new GpsData();
            data.setGpsdata(body);
            data.setTs(System.currentTimeMillis());
            mapper.insert(data);
        }catch (Exception ex){
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        logger.info("接收客户端数据:" + body);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
