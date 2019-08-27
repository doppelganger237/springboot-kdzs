package com.zhang.kdzs.area.mapper;

import com.zhang.kdzs.area.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：zjw
 * @date ：Created in 2019/8/12 18:15
 * @description：
 * @modified By：
 */
@Mapper
public interface AreaMapper {

    @Select("select * from area where code = #{id} limit 1")
    Area getById(String id);


    @Select("select * from area where citycode = #{id}")
    List<Area> getAreaByCityId(String id);
}
