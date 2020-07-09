package com.stackroute.favouriteservice.repository;

import org.springframework.stereotype.Repository;
import com.stackroute.favouriteservice.entity.News;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.repository.query.Param;


/**
 * @author Zaithoon M
 *
 */
@Repository
public interface NewsRepo extends MongoRepository<News,Integer>{

	//@Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
	// @Query(value = "{'title': {$regex : '^?0$', $options: 'i'}}")
	List<News> findByTitleIgnoreCase(String title);
	 //findByTitleIgnoreCase
	News findByNewsIdAndUserId (@Param("id") int id,@Param("userId") String userId);
	//List<News> findAllWatchListNews (@Param("userId") String userId);
	List<News> findAllByUserId(@Param("userId") String userId);
	
	News findByNewsId(@Param("newsId") int newsId);

}
