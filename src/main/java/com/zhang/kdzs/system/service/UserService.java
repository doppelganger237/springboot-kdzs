package com.zhang.kdzs.system.service;

import com.zhang.kdzs.common.dto.Tree;
import com.zhang.kdzs.system.entity.Menu;
import com.zhang.kdzs.system.entity.User;

import java.util.List;

public interface UserService {

	List<User> findAll();

	List<User> findById(Long id);

	void create(User user);

	void delete(Long... ids);

	void update(User user);

	User findByName(String name);


    List<Tree<Menu>> getMenus(String username);

    int getUserNum();
}
