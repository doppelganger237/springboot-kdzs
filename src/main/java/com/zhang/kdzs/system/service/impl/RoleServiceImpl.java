package com.zhang.kdzs.system.service.impl;

import com.zhang.kdzs.system.entity.Role;
import com.zhang.kdzs.system.mapper.RoleMapper;
import com.zhang.kdzs.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findUserRole(String username ) {

		return roleMapper.findUserRole(username);
		
	}

	/*	@Override
		public List<Role> findUserRole(User user) {
			return roleMapper.findUserRole(user.getUsername());
		}
		*/
	

}
