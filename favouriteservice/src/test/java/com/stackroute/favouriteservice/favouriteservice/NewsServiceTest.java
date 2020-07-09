package com.stackroute.favouriteservice.favouriteservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.stackroute.favouriteservice.entity.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.repository.NewsRepo;
import com.stackroute.favouriteservice.service.NewsServiceImpl;


import junit.framework.Assert;


public class NewsServiceTest {


	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private transient NewsRepo newsRepo;
	
	@InjectMocks
	private NewsServiceImpl newsServiceImpl;
	transient Optional<News> options;

	private News news;
	
	@Before
	public void init() throws Exception
	{
		 news=new News();
		 news.setNewsId(1);
		 news.setUserId("userid");
		 news.setTitle("title");
		 news.setAuthor("author");
		 news.setDescription("descrption");
		 news.setPublishedAt("publishedat");
		 news.setUrlToImage ("url");
		
		
	}
	
	@Test
	public void saveNewsTest_success() throws NewsAlreadyExistsException {
		
		newsServiceImpl.saveNews(news);
		when(newsRepo.save(news)).thenReturn(news);
		final boolean flag = newsServiceImpl.saveNews(news);
		assertTrue(flag);
	

	}

	@Test(expected=NewsAlreadyExistsException.class)
	public void saveNewsTest_failure() throws NewsAlreadyExistsException {
		
		when(newsRepo.findByNewsIdAndUserId(1,"userid")).thenReturn(news);
		newsServiceImpl.saveNews(news);
	}
	
	@Test
	public void deleteNewsByIdTest_success() throws NewsNotFoundException {
		
		when(newsRepo.findByNewsIdAndUserId(1,"userid")).thenReturn(news);
		doNothing().when(newsRepo).delete(news);
		final boolean flag = newsServiceImpl.deleteNewsById(1,"userid" );
		assertTrue(flag);
	}
	
	@Test(expected=NewsNotFoundException.class)
	public void deleteNewsByIdTest_failure() throws NewsNotFoundException {
		
		when(newsRepo.findByNewsIdAndUserId(1,"userid")).thenReturn(null);
		doNothing().when(newsRepo).delete(news);
		newsServiceImpl.deleteNewsById(1,"userid");
		
	}
}
