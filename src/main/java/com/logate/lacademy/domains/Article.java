package com.logate.lacademy.domains;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Description(value = "Article Entity Representation.")
@Entity
@Table(name = "articles")
public class Article implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonBackReference
	private Employee employee;
	
	@Column(name = "published_at", nullable = false)
	private Date publishedAt;
	
	@OneToOne
	@JoinColumn(name = "document_id")
	private Document document;
	
	@OneToMany(mappedBy = "article", fetch = FetchType.EAGER)   //mappedBy = "article" - ovo stavljamo kada imamo bidirekcionalnu vezu
	@JsonManagedReference
	private Set<Comments> comments = new HashSet<>();

	public Set<Comments> getComments() {
		return comments;
	}


	

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", employee="
				+ employee + ", publishedAt=" + publishedAt + ", document="
				+ document + ", comments=" + comments + "]";
	}

	
}
