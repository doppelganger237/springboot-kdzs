package com.zhang.kdzs.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable{
	
	
	private static final long serialVersionUID = -4138866402607590568L;
	
	private Long id;
	
	
	private String name;

	
	private Long parentId;
	

	private String url;

	private String permission;
	


	private String type;

	private String icon;

	private long priority;
	
	private boolean status;
	
}
