package com.zhang.kdzs.system.controller;

import com.zhang.kdzs.common.dto.Result;
import com.zhang.kdzs.system.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

	@PostMapping(value = "/register")
	public Result register(@Validated User user) {
		
		
		
		
		return null;
	}
	
	
}
