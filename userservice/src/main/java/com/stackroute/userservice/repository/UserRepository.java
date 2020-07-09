package com.stackroute.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.entity.User;


/**
 * @author Zaithoon M
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	

	public User findByUseridAndPassword(String userid, String password);
	
	public User findByUserid(String userid);

}
