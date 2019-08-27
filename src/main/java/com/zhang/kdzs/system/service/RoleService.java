package com.zhang.kdzs.system.service;

import com.zhang.kdzs.system.entity.Role;

import java.util.List;

public interface RoleService {

	
	
	List<Role> findUserRole(String username);
	
	/*
		List<Role> findUserRole(User user);*/
}
