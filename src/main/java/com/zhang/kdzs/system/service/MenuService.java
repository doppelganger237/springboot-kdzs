package com.zhang.kdzs.system.service;

import com.zhang.kdzs.system.entity.Menu;

import java.util.List;

public interface MenuService {

	
	List<Menu> findUserMenus(String username);
}
