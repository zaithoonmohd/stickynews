package com.stackroute.favouriteservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Zaithoon M
 * News class to store news data
 *
 */

@Document
public class News {
	@Id
	private int newsId;
	//private String id;
	private String userId;

	private String title;
	private String author;
	private String description;
	private String publishedAt;
	private String urlToImage;
	
public News() {
	
}



public News(int newsId, String userId, String title, String author, String description, String publishedAt,
		String urlToImage) {
	super();
	this.newsId = newsId;
	this.userId = userId;
	this.title = title;
	this.author = author;
	this.description = description;
	this.publishedAt = publishedAt;
	this.urlToImage = urlToImage;
}



public int getNewsId() {
	return newsId;
}

public void setNewsId(int newsId) {
	this.newsId = newsId;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getPublishedAt() {
	return publishedAt;
}

public void setPublishedAt(String publishedAt) {
	this.publishedAt = publishedAt;
}

public String getUrlToImage() {
	return urlToImage;
}

public void setUrlToImage(String urlToImage) {
	this.urlToImage = urlToImage;
}

@Override
public String toString() {
	return "News [newsId=" + newsId + ", userId=" + userId + ", title=" + title + ", author=" + author
			+ ", description=" + description + ", publishedAt=" + publishedAt + ", urlToImage=" + urlToImage + "]";
}


}
