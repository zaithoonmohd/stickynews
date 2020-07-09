package com.stackroute.userservice.service;

import java.util.List;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UsernotFoundException;

/**
 * @author Zaithoon M
 *
 */
public interface UserService {

	public boolean saveUser(User user) throws UserAlreadyExistsException;
	
	public User findByUserIdAndPassword(String userId,String password) throws UsernotFoundException;

	public String generateToken(String userid, String firstname);

	public boolean updUser(String userid) throws UsernotFoundException;

	public Boolean updateUser(User newuser) throws UsernotFoundException;
	
	public List<User> getAllUsers();
}
