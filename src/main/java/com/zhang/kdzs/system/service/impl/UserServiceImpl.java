package com.zhang.kdzs.system.service.impl;

import com.zhang.kdzs.common.dto.Tree;
import com.zhang.kdzs.system.entity.Menu;
import com.zhang.kdzs.system.entity.User;
import com.zhang.kdzs.system.mapper.MenuMapper;
import com.zhang.kdzs.system.mapper.UserMapper;
import com.zhang.kdzs.system.service.UserService;
import com.zhang.kdzs.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public List<User> findById(Long id) {
		return userMapper.findById(id);
	}

	@Override
	public void create(User user) {
		userMapper.create(user);
	}

	@Override
	public void delete(Long... ids) {
		for (Long id : ids) {
			userMapper.delete(id);
		}
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}



	@Override
	public List<Tree<Menu>> getMenus(String username) {
		  List<Menu> menus = menuMapper.findUserMenus(username);

		  //log.info(menus.toString());


	        List<Tree<Menu>> treeList = new ArrayList<>();


		  menus.forEach(menu -> {
	            Tree<Menu> tree = new Tree<>();
	            tree.setId(menu.getId());
	            tree.setParentId(menu.getParentId());
	            tree.setName(menu.getName());
	            tree.setUrl(menu.getUrl());
	            tree.setIcon(menu.getIcon());
	            treeList.add(tree);
	        });
		  
		 
		 // System.out.println(TreeUtils.build(treeList).toString());
		  return TreeUtils.build(treeList);
	}

	@Override
	public int getUserNum() {
		return userMapper.getUserNum();
	}

}
