package com.stackroute.userservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UsernotFoundException;
import com.stackroute.userservice.service.UserService;

/**
 * @author Zaithoon M
 *  Test class for User Controller
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	

	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient UserService userservice;
	
	@InjectMocks
	private UserController usercontroller;
	 
	@Mock
	private transient User user;
	
	@Before
	public void setup() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		
		mvc=MockMvcBuilders.standaloneSetup(usercontroller).build();	 
		user = new User();
		user.setFirstname("firstname");
		user.setLastname("lastname");
		user.setUserid("admin");
		user.setPassword("pass");
	}
	
	@After
	public  void teardown() throws Exception{
		
	}
	
	@Test
	public void testLoginUser_success() throws Exception{
		
		String userId = "admin";
		String password ="pass";
		Mockito.when(userservice.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mvc.perform(post("/api/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
		
	}
	
	@Test
	public void testLoginUser_failure() throws Exception{
		
		Mockito.when(userservice.findByUserIdAndPassword(Mockito.anyString(), Mockito.anyString())).thenThrow(UsernotFoundException.class);
		mvc.perform(post("/api/userservice/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		
		
	}

	@Test
	public void testRegisterUser_success() throws Exception{
		
		Mockito.when(userservice.saveUser(Mockito.any(User.class))).thenReturn(true);
		mvc.perform(post("/api/userservice/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
	
	@Test
	public void testRegisterUser_failure() throws Exception{
		
		Mockito.when(userservice.saveUser(Mockito.any(User.class))).thenThrow(UserAlreadyExistsException.class);
		mvc.perform(post("/api/userservice/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user)))
				.andExpect(status().isConflict())
				.andDo(MockMvcResultHandlers.print());

	}
	private static String jsonToString(final Object obj) throws JsonProcessingException{

		String result;
		
			final ObjectMapper mapper = new ObjectMapper();
			try {
				final String jsonContent = mapper.writeValueAsString(obj);
				result = jsonContent;
			} catch (JsonProcessingException e) {
				result = "Json processing error";
			}
			return result;
	
	}
	
}
