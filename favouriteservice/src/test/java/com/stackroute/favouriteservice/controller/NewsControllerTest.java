package com.stackroute.favouriteservice.controller;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.entity.News;
import com.stackroute.favouriteservice.exception.NewsAlreadyExistsException;
import com.stackroute.favouriteservice.exception.NewsNotFoundException;
import com.stackroute.favouriteservice.service.NewsService;




/**
 * @author Zaithoon M
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest

public class NewsControllerTest {

	@Autowired
	private  MockMvc mvc;
	
	@MockBean
	private  NewsService service;
	
	private  News news1;
	
	private  List<News> newsList;

	@InjectMocks
	NewsController newsController;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(newsController).build();
		news1 = new News();
		news1.setAuthor("authorE");
		news1.setDescription("descriptionE");
		news1.setTitle("titleE");
		news1.setUserId("idE");
		news1.setNewsId(1);
		News news2 = new News();
		news2.setAuthor("authorF");
		news2.setDescription("descriptionF");
		news2.setTitle("titleF");
		news2.setUserId("idF");
	
		newsList = new ArrayList<>();
		newsList.add(news1);
		newsList.add(news2);
	}

	@Test
	public void saveNewsSuccessTest() throws Exception {
	
		when(service.saveNews(news1)).thenReturn(true);
		mvc.perform(post("/api/v1/news/news1.getUserId()")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
		verify(service, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void saveNewsFailureTest() throws Exception {
	
		when(service.saveNews(Mockito.any())).thenThrow(NewsAlreadyExistsException.class);
		mvc.perform(post("/api/v1/news/news1.getUserId()")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(news1))).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		verify(service, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void deleteByIdSuccessTest() throws Exception {
	
		when(service.deleteNewsById(1, "idE")).thenReturn(true);
		mvc.perform(delete("/api/v1/news/delete/1/idE"))
				.andExpect(status().isOk());
		verify(service, times(1)).deleteNewsById(1,"idE");
		verifyNoMoreInteractions(service);
	}

	@Test
	public void deleteByIdFailureTest() throws Exception {
	
		when(service.deleteNewsById(Mockito.anyInt(), Mockito.anyString())).thenThrow(NewsNotFoundException.class);
		mvc.perform(delete("/api/v1/news/delete/1/idE"))
				.andExpect(status().isNotFound());
		verify(service, times(1)).deleteNewsById(1,"idE");
		verifyNoMoreInteractions(service);
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
	

	


