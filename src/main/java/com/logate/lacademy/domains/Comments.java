package com.logate.lacademy.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "comments")
public class Comments implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "article_id", nullable = false) 
	@JsonBackReference
	private Article article;
	
	@Column (name = "body")
	private String body;
	
	@Column (name = "published_at")
	private Date published;
	
	@Column
	private Integer likes;
	
	@Column
	private Integer dislikes;
	
	@ManyToOne
	@JoinColumn (name = "user_id", nullable = false)
	private User user;

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getLikes() {
		return likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", article=" + article + ", body=" + body
				+ ", published=" + published + ", likes=" + likes
				+ ", dislikes=" + dislikes + ", user=" + user + "]";
	}
	
	
	
}
