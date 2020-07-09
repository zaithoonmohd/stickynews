package com.stackroute.favouriteservice.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.entity.News;

import junit.framework.Assert;


/**
 * @author Zaithoon M
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class NewsRepositoryTest {

	@Autowired
	private NewsRepo repo;
	
	private News news1;
	private News news2;
	
	 @Before
	 public void setup() throws Exception
	 {
		 news1=new News();
		 news1.setNewsId(1);
		 news1.setUserId("userid");
		 news1.setTitle("title");
		 news1.setAuthor("author");
		 news1.setDescription("descrption");
		 news1.setPublishedAt("publishedat");
		 news1.setUrlToImage ("url");
		 
		 news2=new News();
		 news2.setNewsId(2);
		 news2.setUserId("userid2");
		 news2.setTitle("title2");
		 news2.setAuthor("author2");
		 news2.setDescription("descrption2");
		 news2.setPublishedAt("publishedat2");
		 news2.setUrlToImage ("url2");
		 
	 }
	 
	 @After
	 public void tearDown() throws Exception {
		 repo.deleteAll();
	 }

	 @Test
	 public void saveNewsTest() throws Exception
	 {
		 repo.save(news1);
		 News newstest=repo.findByNewsId(news1.getNewsId());
		 Assert.assertEquals(newstest.getNewsId(), news1.getNewsId());
	 }
	 
	 @Test
		public void getAllFavNewsTest() throws Exception{
			repo.save(news1);
			repo.save(news2);
			List<News> newsList=(List<News>)repo.findAll();
			assertEquals(true,newsList.size()>=2);
		}
	 
	 @Test
		public void deleteFavNewsTest() throws Exception{

			repo.save(news1);
			List<News> retrivedNews = repo.findAllByUserId("userid");
			repo.delete(news1);
			retrivedNews = repo.findAllByUserId("userid");
			assertEquals(0,retrivedNews.size());
		}
	 
}
