package com.zhang.kdzs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLogin() throws Exception {
		String uri = "/login";
		MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).param("username", "zjw")).andReturn();
		 System.out.println(mvcResult.getResponse().getContentAsString());
	}

}
