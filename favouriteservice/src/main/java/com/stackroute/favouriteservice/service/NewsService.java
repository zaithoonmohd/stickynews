package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.entity.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;

/**
 * @author Zaithoon M
 *
 */
public interface NewsService {
	
	public boolean saveNews(News news) throws NewsAlreadyExistsException;
	public boolean deleteNewsById(int newsId, String userId) throws NewsNotFoundException;
	public List<News> getAllAppNews();
	public List<News> searchByTitle(String searchText);
	
	public List<News> getAllWatchlistNews(String userId) throws NewsNotFoundException;
	

	
	
//=======
//	public boolean deleteNewsById(int newsId) throws NewsNotFoundException;
//	public List<News> getAllNews();
//	public List<News> searchByTitle(String searchText);
//	
//
//>>>>>>> 131613b5554fac8a1dab56efd303c937e73967a7
}
