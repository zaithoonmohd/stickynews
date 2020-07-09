package com.stackroute.userservice.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UsernotFoundException;
import com.stackroute.userservice.service.UserService;

/**
 * @author Zaithoon M
 *
 */
@CrossOrigin(origins= "*")
@RestController
@RequestMapping(path="/api/userservice")
public class UserController {
	
	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private UserService userservice;
	
	public UserController(UserService userservice) {
		this.userservice = userservice;
		
	}
	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path= "/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			log.info("registerUser: STARTED");
			log.info("Input values from requestbody - user :"+user);
			if(user.getUserid()==null || user.getFirstname() == null || user.getLastname() == null || user.getPassword() == null) {
				return new ResponseEntity<String>("Fields cannot be empty",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			userservice.saveUser(user);
			return new ResponseEntity<String>("User registered successfully",HttpStatus.OK);
		}
		catch(UserAlreadyExistsException e) {
			//return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	/**
	 * @param user
	 * @return
	 */
	@PostMapping(path="/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		log.info("loginUser: STARTED");
		log.info("Input values from requestbody -User:"+user);
		try {
			String userid = user.getUserid();
			String password = user.getPassword();
			if(userid == null || password == null) {
				return new ResponseEntity<String>("Userid and password cannot be empty",HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			User userinfo = null;
			try {
				 userinfo = userservice.findByUserIdAndPassword(userid, password);
				//System.out.println("Userinfo"+userinfo);
			}
			catch(UsernotFoundException e) {
				return new ResponseEntity<String>("Invalid userid or password",HttpStatus.NOT_FOUND);
			}
		
			String webtoken = userservice.generateToken(userinfo.getUserid(), userinfo.getFirstname());
			System.out.println("User"+userinfo);
			Map<String,String> map= new HashMap<String, String>();
			map.put("token", webtoken);
			//map.put("userid", userinfo.getUserid());
			map.put("firstname",userinfo.getFirstname());
			log.info("set token:"+webtoken);
			return new ResponseEntity<Map>(map,HttpStatus.OK);
			
		}
		catch(Exception e) {
			
			//return new ResponseEntity<String>("{ \"message\":\"" +e.getMessage() +"\"}",HttpStatus.UNAUTHORIZED);

			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/find/{userid}")
	public ResponseEntity<?> findUser(@PathVariable String userid){
		log.info("findUser: STARTED");
			
			try {
				boolean extuser = userservice.updUser(userid);
				return new ResponseEntity<String>("User found",HttpStatus.OK);

			}
			catch (UsernotFoundException e) {
				return new ResponseEntity<String>("User does not exists",HttpStatus.NOT_FOUND);
			}
		
	}
	@RequestMapping("/showall")
	public ResponseEntity<?> showAllUser(){
		log.info("showAllUser: STARTED");
		List<User> userlist = userservice.getAllUsers();
		return new ResponseEntity<List>(userlist,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<?> updateUser(@RequestBody User newuser,@PathVariable String userid){
		System.out.println("pathid"+userid);
		System.out.println("newuserid"+newuser.getUserid());
		if(!(userid.equals(newuser.getUserid()))) {
			return new ResponseEntity<String>("User id mismatch",HttpStatus.CONFLICT);

		}
		
		
		
		try {
			
			Boolean updateStatus = userservice.updateUser(newuser);
			return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);

		
		}
		
		catch (Exception e) {
			return new ResponseEntity<String>("User does not exists- cannot update",HttpStatus.NOT_FOUND);
		}
		
	}
		
	
}
