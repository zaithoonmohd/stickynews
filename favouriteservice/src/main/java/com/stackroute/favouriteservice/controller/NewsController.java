package com.stackroute.favouriteservice.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.favouriteservice.entity.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.service.NewsService;

import io.jsonwebtoken.Jwts;


/**
 * @author Zaithoon M
 *
 */
@RestController
@RequestMapping("api/v1/news")
@CrossOrigin(origins ="*")
public class NewsController {


	private Log log = LogFactory.getLog(getClass());
	@Autowired
	private NewsService newsService;
	
	@PostMapping(path="/{userId}")
	public ResponseEntity<?>saveNews(@RequestBody News news, @PathVariable String userId) throws NewsAlreadyExistsException {
		
		//final HttpServletRequest request
		
		ResponseEntity<?> responseEntity = null;
		log.info("saveNews: STARTED");
//		 Map<String, String> map = new HashMap<String, String>();
//
//	        Enumeration headerNames = request.getHeaderNames();
//	        while (headerNames.hasMoreElements()) {
//	            String key = (String) headerNames.nextElement();
//	            String value = request.getHeader(key);
//	            map.put(key, value);
//	          System.out.println(map);
//	        }
//		try {
//			System.out.println("req is"+request);
//			final String userid = (String) request.getAttribute("subject");
//			log.info("Userid as subject from req"+userid);
//			final String authHeader = request.getHeader("authorization");
//			log.info("Authorization from header:"+authHeader);
//			final String token = authHeader.substring(7);
//			log.info("Token from header:"+token);
//			String userId=Jwts.parser().setSigningKey("ustkey").parseClaimsJws(token).getBody().getSubject();
//			log.info("Authorization userId from header:"+userId);
//			news.setUserId(userId);
			try {
			log.info("saveNews - newsobj from req body :"+news);
			news.setUserId(userId);
			System.out.println("Userid from path"+userId);
			boolean saveStatus = newsService.saveNews(news);
			System.out.println("News added to fav status :"+saveStatus);
			responseEntity = new ResponseEntity<News>(news, HttpStatus.CREATED);
				}
			catch(NewsAlreadyExistsException e) {
				
				responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
				
				}
//			}catch (Exception e) {
//				
//				responseEntity= new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//				}
		log.info("saveNews: ENDED");
		return responseEntity;
	}


//	@PostMapping
//	public ResponseEntity<?>saveNews(@RequestBody News news, HttpServletRequest request) throws NewsAlreadyExistsException {
//		ResponseEntity<?> responseEntity = null;
//		
//		try {
//			newsService.saveNews(news);
//		}
//		catch(NewsAlreadyExistsException e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
//
//		}
//			responseEntity = new ResponseEntity<News>(news, HttpStatus.CREATED);
//			return responseEntity;
//	}

	/**
	 * API to delete news by  id
	 * @param id
	 * @return responseEntity
	 */
	@DeleteMapping(path = "delete/{newsId}/{userId}")
	//final HttpServletRequest request
	public ResponseEntity<?> deleteNews(@PathVariable("newsId") int id, @PathVariable("userId") String userId) {
		ResponseEntity<?> responseEntity = null;
//		try {
//			final String authHeader = request.getHeader("authorization");
//			final String token = authHeader.substring(7);
//			String userId=Jwts.parser().setSigningKey("ustkey").parseClaimsJws(token).getBody().getSubject();
			try {
				//String userId="ashy10";
				System.out.println("User id from path :"+userId);
				boolean deleteStatus = newsService.deleteNewsById(id,userId);
				responseEntity = new ResponseEntity<Boolean>(deleteStatus, HttpStatus.OK);
			} catch (NewsNotFoundException exception) {
				responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
						HttpStatus.NOT_FOUND);
			}
		
//		
//		}catch(Exception e) {
//			responseEntity=  new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//		}
		return responseEntity;
	}
	
	
	
	/**
	 * API to get all watchlist news
	 * @return responseEntity
	 */
	@GetMapping(path="/watchnews/{userId}")
	//final HttpServletRequest request
	public ResponseEntity<?> getAllWatchlistNews(@PathVariable String userId) {
	
	ResponseEntity<?> responseEntity = null;
//		System.out.println("Request:"+request);
//		
//		
//		final String header = request.getHeader("authorization");
//		final String tokenout = header.substring(7);
//		System.out.println(tokenout);
//		System.out.println();
//		try {
//			final String authHeader = request.getHeader("authorization");
//			final String token = authHeader.substring(7);
//			String userId=Jwts.parser().setSigningKey("ustkey").parseClaimsJws(token).getBody().getSubject();	
//			
//			System.out.println("getAllWatchlistNews - Controller -userid from token :"+userId);
		System.out.println("inwatch news");
			try {
				//String userId="ashy10";
				System.out.println("Accessing watchlist news" +userId);
			List<News> news = newsService.getAllWatchlistNews(userId);
			responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
			System.out.println("watchlist news success");
			}
			catch(NewsNotFoundException e) {
				responseEntity = new ResponseEntity<String>("No news added to watch list",HttpStatus.NOT_FOUND);
			}
			
//		}
//		catch(Exception e) {
//			System.out.println("watchlistnews exception");
//			responseEntity=  new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//		}
		return responseEntity;
	}
	
//	//common api
//	public ResponseEntity<?> deleteNews(@PathVariable("newsId") int id, HttpServletRequest request) {
//		ResponseEntity<?> responseEntity = null;
//		
//			
//		try {
//			boolean deleteStatus = newsService.deleteNewsById(id);
//			responseEntity = new ResponseEntity<Boolean>(deleteStatus, HttpStatus.OK);
//		} catch (NewsNotFoundException exception) {
//			responseEntity = new ResponseEntity<String>("{ message :" + exception.getMessage() + "}",
//					HttpStatus.NOT_FOUND);
//		}
//	
//		
//		return responseEntity;
//	}
	
	/**
	 * API to get all news
	 * @return responseEntity
	 */
	@GetMapping

	public ResponseEntity<?> getAllNews(final HttpServletRequest request) {
		ResponseEntity<?> responseEntity = null;
//		try {
//			final String authHeader = request.getHeader("authorization");
//			final String token = authHeader.substring(7);
//			String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
			
			List<News> news = newsService.getAllAppNews();
			responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);
//		}
//		catch(Exception e ) {
//			responseEntity=  new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//		}
		return responseEntity;
		}

	//common api
	/**
	 * @param request
	 * @param searchText
	 * @return
	 */
	@GetMapping(path="/search/{searchText}")
	public ResponseEntity<?> searchNews(final HttpServletRequest request,@PathVariable("searchText")String searchText) {
		ResponseEntity<?> responseEntity = null;
//		try {
//			final String authHeader = request.getHeader("authorization");
//			final String token = authHeader.substring(7);
//			String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

		List<News> news = newsService.searchByTitle(searchText);
		if(news.isEmpty()) {
			return new ResponseEntity<String>("News with entered keyword is not available",HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<List<News>>(news, HttpStatus.OK);

//		}
//		catch(Exception e) {
//			responseEntity=  new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//		}
		return responseEntity;
		
	}
	

}
