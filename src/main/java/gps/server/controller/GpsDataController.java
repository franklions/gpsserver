package gps.server.controller;

import gps.server.domain.GpsData;
import gps.server.mapper.GpsDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/1/20.
 */
@RestController
public class GpsDataController {

    @Autowired
    GpsDataMapper mapper;

    @GetMapping("/api/list")
    public ResponseEntity<?> getListData(){
        List<GpsData> retval = mapper.selectAllList();
        return ResponseEntity.ok(retval);
    }
}
