package com.zhang.kdzs.system.mapper;

import com.zhang.kdzs.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> findUserMenus(String username);
}
