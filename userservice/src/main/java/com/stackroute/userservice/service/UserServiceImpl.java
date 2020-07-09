package com.stackroute.userservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UsernotFoundException;
import com.stackroute.userservice.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Zaithoon M
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException{
		
		final Optional<User> userObj = userRepository.findById(user.getUserid());
		if(userObj.isPresent()) {
			//System.out.println("User already exists");
			//return false;
			throw new UserAlreadyExistsException("User already exists in the database");
		}
		//else {
			userRepository.save(user);
			return true;
		//}
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UsernotFoundException{
		
		final User user = userRepository.findByUseridAndPassword(userId, password);
		System.out.println(user);
		if(user==null) {
			//System.out.println("User doesnot exists");
			//return null;
			throw new UsernotFoundException("User does not exists in the database");
		}
		return user;
	}
	
	@Override
	public boolean updUser(String userid) throws UsernotFoundException {
		
		final User user = userRepository.findByUserid(userid);
		System.out.println("User:"+user);
		if(user==null) {
			throw new UsernotFoundException("User doesnot exists in database");
		}
		return true;
	}	

	@Override
	public Boolean updateUser(User newuser) throws UsernotFoundException {
		
		final Optional<User> userObj = userRepository.findById(newuser.getUserid());
		System.out.println("before upd userobj:"+userObj);
		if(!userObj.isPresent()) {
			throw new UsernotFoundException("User do not exists in the database");
		}
		//newuser.setCreated(new Date());
		userRepository.save(newuser);
		System.out.println("Update user :"+newuser);
		return true;
	}
	
	/**
	 * To generate JSON web token
	 * @param userid
	 * @param firstname
	 * @return
	 */
	public String generateToken(String userid, String firstname) {
		//tracking session
		return Jwts.builder().setSubject(userid)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256,"ustkey")
				.compact();
	}

	@Override
	public List<User> getAllUsers() {
		
		System.out.println("Find all");
		return userRepository.findAll();

	}
	
}
