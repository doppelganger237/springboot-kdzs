package com.zhang.kdzs.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable{

	
	private static final long serialVersionUID = 2289699690164148459L;

	private long id;

	@NotEmpty
	@Size(min=2,max=15)
	private String username;
	
	@NotEmpty
	private String password;
	
	private String salt;

}
