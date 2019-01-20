package gps.server.mapper;

import gps.server.domain.GpsData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GpsDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpsData record);

    int insertSelective(GpsData record);

    GpsData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpsData record);

    int updateByPrimaryKey(GpsData record);

    List<GpsData> selectAllList();
}