package com.logate.lacademy.web.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer article_id;
	private String body;
	private Date published_at;
	
	public CommentsDTO(){
		
	}

	public CommentsDTO(Integer id, Integer article_id, String body, Date published_at) {
		this.id = id;
		this.article_id = article_id;
		this.body = body;
		this.published_at = published_at;
	}

	
	
	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getPublished_at() {
		return published_at;
	}

	public void setPublished_at(Date published_at) {
		this.published_at = published_at;
	}

	@Override
	public String toString() {
		return "CommentsDTO [id=" + id + ", article_id=" + article_id
				+ ", body=" + body + ", published_at=" + published_at + "]";
	}
	
}
