package com.stackroute.userservice.userservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UsernotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserServiceImpl;



public class UserServiceTest {

	private User user;
	
	@InjectMocks
	UserServiceImpl userservice;
	
	@Mock
	private transient UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	transient Optional<User> options;
	
	@Before
	public void init() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		 user=new User();
		 user.setUserid("userid");;
		 user.setFirstname("firstname");
		 user.setLastname("lastname");
		 user.setPassword("password");
		 user.setCreated(new Date());
		
		
	}

	@Test
	public void saveUserTest_success() throws UserAlreadyExistsException {
		when(userRepo.save(user)).thenReturn(user);
		final boolean flag = userServiceImpl.saveUser(user);
		assertTrue(flag);

}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void saveUserTest_failure() throws UserAlreadyExistsException {
		
		options=Optional.of(user);
		Mockito.when(userRepo.findById("userid")).thenReturn(options);
		userServiceImpl.saveUser(user);
	
}
	
	@Test
	public void loginUserTest_success() throws UsernotFoundException{
		
		Mockito.when(userRepo.findByUseridAndPassword("userid","password")).thenReturn(user);
		User userobj = userServiceImpl.findByUserIdAndPassword(user.getUserid(), user.getPassword());
		System.out.println(userobj);
		assertEquals(userobj,user);
	}
	
	@Test(expected= UsernotFoundException.class)
	public void loginUserTest_failure() throws UsernotFoundException{
		
		Mockito.when(userRepo.findByUseridAndPassword("userid","password")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserid(), user.getPassword());
	
	}
}

