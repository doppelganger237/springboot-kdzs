package com.zhang.kdzs.system.mapper;

import com.zhang.kdzs.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findUserRole(String username);
}
