package com.zhang.kdzs.system.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Role implements Serializable {

	

	private static final long serialVersionUID = 5970834770491550952L;

	private Long id;
	
	private String name;
	

    private String description;

    private Boolean status;
    

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }
}
