package com.stackroute.favouriteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.entity.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.repository.NewsRepo;

/**
 * @author Zaithoon M
 *
 */
@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsRepo newsRepo;
	
	@Override
	public boolean saveNews(News news) throws NewsAlreadyExistsException {
		final News newsobj = newsRepo.findByNewsIdAndUserId(news.getNewsId(),news.getUserId());
		//final News newsobj = newsRepo.findById(news.getNewsId()).orElse(null);
		System.out.println("newobj"+news);
		if(newsobj== null) {
			newsRepo.save(news);
			return true;
		}
		else {
			throw new NewsAlreadyExistsException("Cannot save news, already exists in database");
		}
	}

	@Override
	public boolean deleteNewsById(int newsId,String userId) throws NewsNotFoundException {
		
		final News newsobj = newsRepo.findByNewsIdAndUserId(newsId,userId);

//		final News newsobj = newsRepo.findById(news.getNewsId()).orElse(null);
//		if(newsobj  != null) {
//			throw new NewsAlreadyExistsException("Cannot save news, already exists in database");
//		}
//		newsRepo.save(news);
//		return true;
//	}
//
//	@Override
//	public boolean deleteNewsById(int newsId) throws NewsNotFoundException {
//		
//		final News newsobj = newsRepo.findById(newsId).orElse(null);
//>>>>>>> 131613b5554fac8a1dab56efd303c937e73967a7
		if(newsobj==null) {
			throw new NewsNotFoundException("News not found!");
		}
		newsRepo.delete(newsobj);
		return true;
		
	}

	
	@Override
	public List<News> getAllWatchlistNews(String userId) throws NewsNotFoundException {
		
		List <News> watchlist = newsRepo.findAllByUserId(userId);
		System.out.println("watchlist news in getAllWatchlistNews"+watchlist);
		 if(watchlist == null) {
				throw new NewsNotFoundException("No WatchList News found!");
		 }
		 return watchlist;
	}

	//common operation
	@Override
	public List<News> getAllAppNews() {
		return (List<News>) newsRepo.findAll();
	}
	//common operation
//=======
//
//	@Override
//	public List<News> getAllNews() {
//		return (List<News>) newsRepo.findAll();
//	}
//
//>>>>>>> 131613b5554fac8a1dab56efd303c937e73967a7
	@Override
	public List<News> searchByTitle(String searchText) {
		return (List<News>)newsRepo.findByTitleIgnoreCase(searchText);
	}

}
