package gps.server;

import gps.server.mapper.GpsDataMapper;
import gps.server.service.TCPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/1/20.
 */
@SpringBootApplication
public class AppMain implements CommandLineRunner {
    private Logger logger = Logger.getLogger("appmain");

    public static void main(String[] args) {
        ApplicationContext ctx =   SpringApplication.run(AppMain.class);

    }

    @Autowired
    GpsDataMapper mapper;

    @Override
    public void run(String... strings) throws Exception {

        logger.info ("启动TCP服务器...");

        try {
            TCPServer.SetMapper(mapper);
            TCPServer.run();
        } catch (Exception e) {
            e.printStackTrace();
            TCPServer.shutdown();
        }
    }
}
