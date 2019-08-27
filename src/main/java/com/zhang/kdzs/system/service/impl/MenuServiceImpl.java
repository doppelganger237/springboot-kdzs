package com.zhang.kdzs.system.service.impl;

import com.zhang.kdzs.system.entity.Menu;
import com.zhang.kdzs.system.mapper.MenuMapper;
import com.zhang.kdzs.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

	

	@Autowired
	private MenuMapper mapper;

	@Override
	public List<Menu> findUserMenus(String username) {
		return mapper.findUserMenus(username);
	}
	
	
	
	
	
}
