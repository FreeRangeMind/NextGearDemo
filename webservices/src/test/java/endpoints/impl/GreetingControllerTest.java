package endpoints.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {

	 @Autowired
	    private MockMvc mvc;

	    @Test
	    public void getHello() throws Exception {
	    	
	    	ResultActions result = mvc.perform(
	        		MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON));
	    	
	    	result.andExpect(status().isOk());
	    	result.andExpect(content().string(equalTo("{\"id\":1,\"content\":\"Hello, World!\"}")));
	    }
	
}
