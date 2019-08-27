package com.zhang.kdzs.system.controller;

import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.common.dto.StatusCode;
import com.zhang.kdzs.system.entity.Role;
import com.zhang.kdzs.system.entity.User;
import com.zhang.kdzs.system.service.RoleService;
import com.zhang.kdzs.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;


	@PostMapping(value = "/getRoles")
	public Result getRoles() {

		Result result = new Result(StatusCode.ERROR);

		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {

			List<Role> roles = roleService.findUserRole(user.getUsername());

			if (!roles.isEmpty()) {

				List<Serializable> list = new ArrayList<Serializable>();

				roles.forEach(role -> list.add(role));
				result.setCode(StatusCode.SUCCESS);
				result.setData(list);
			}

		}

		return result;
	}

	/**
	 * 这一段照抄了 写出来的真他娘的是个人才
	 *
	 * @return
	 */
	@PostMapping(value = "/getMenus")
	public Result getMenus() {

		Result result = new Result(StatusCode.ERROR);

		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user != null) {

				
				result.setCode(StatusCode.SUCCESS);


				result.setData (userService.getMenus(user.getUsername()));
			

		}

		return result;
	}
	

	@PostMapping(value = "/info")
	public Result info() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Result result = new Result();
		if (user != null) {

			Map<String,Object>  map  = new HashMap<>();

			String name = user.getUsername();

			map.put("username",name);

			map.put("roles",roleService.findUserRole(name));

			result.setCode(StatusCode.SUCCESS);
			result.setData(map);
		}
		return result;
	}
}
