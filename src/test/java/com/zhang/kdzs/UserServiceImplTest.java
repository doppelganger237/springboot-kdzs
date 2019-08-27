package com.zhang.kdzs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.kdzs.system.entity.User;
import com.zhang.kdzs.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());





    @Autowired
    private UserService userService;
	@Test
	public void testFindByName() {

		Map<Long,Double> results = new HashMap<>();

		results.put(1L,20D);




		try {
			ObjectMapper mapper = new ObjectMapper();




			String json = mapper.writeValueAsString(results);

			//Map<Long,Double> map = mapper.readValue(json,new TypeReference<Map<Long,Double>>(){});

			Map<Long,Double> map =mapper.readValue(json,Map.class);

			logger.info(map.get(1L).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}



		//User user = new User(1, "zjw", "123456");
		User user = userService.findByName("zjw");


		//logger.info(new Result<>(StatusCode.SUCCESS,user).toString());
		
	}

class Deserializer extends JsonDeserializer<Long>{


	@Override
	public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		return p.getValueAsLong();
	/*	if(p.getCurrentToken() != JsonToken.VALUE_NUMBER_INT){

		}*/
	}
}

}
