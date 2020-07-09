package com.stackroute.userservice.repository;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.entity.User;

import junit.framework.Assert;
/**
 * @author Zaithoon M
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)

public class UserRepositoryTest {
	
	@Autowired
	UserRepository repo;
	
	User user;
	
	 @Before
	 public void setup() throws Exception
	 {
		 user=new User();
		 user.setUserid("userid");;
		 user.setFirstname("firstname");
		 user.setLastname("lastname");
		 user.setPassword("password");
		 user.setCreated(new Date());
		 
	 }
//	 
//	 @After
//	 public void tearDown() throws Exception {
//		 repo.deleteAll();
//	 }

	 
	 @Test
	 public void registerUserTest() throws Exception
	 {
		 repo.save(user);
		 User usertest=repo.findByUserid("userid");
		 Assert.assertEquals(usertest.getFirstname(), user.getFirstname());
	 }
	 
	 
//	@Test
//	public void loginUserTest() throws Exception {
//		
//		repo.save(user);
//		User usertest = repo.findByUseridAndPassword("userid","password");
//	    Assert.assertEquals(usertest.getUserid(), user.getUserid());
//	}
}
