package com.logate.lacademy.web.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logate.lacademy.domains.Article;
import com.logate.lacademy.domains.User;
import com.logate.lacademy.services.ArticlesService;

@RestController
@RequestMapping(value = "/api")
public class ArticleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private ArticlesService articleService;

	// novi Article
	@RequestMapping(value = "/articles", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> createNew(@RequestBody Article article)
	{
		Article dbArticle = articleService.store(article);
		return new ResponseEntity<>(dbArticle, HttpStatus.CREATED);
	}
	
	//update Article
	@RequestMapping(value = "/articles/{id}", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> updateArticle(@RequestBody Article article, @PathVariable(value = "id") Integer id){
		{
			if (article.getId() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Article updatedArticle = articleService.updateArticle(article, id);
			return Optional.ofNullable(updatedArticle)
					.map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
}
	
	//brisanje Article
	@RequestMapping(value = "/articles/{id}", 
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteArticle(@PathVariable Integer id){
		articleService.deleteArticle(id);
	}
	
	// lista svih Articles
	@RequestMapping(value = "/articles", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Article>> getAllArticles(Pageable pageable){
		Page<Article> articles = articleService.getAllArticles(pageable);
		return new ResponseEntity<List<Article>>(articles.getContent(), HttpStatus.OK);
	}
	
	// Vraca jedan Article
	@RequestMapping(
			value = "/articles/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Integer articleId)
	{
		Optional<Article> article = articleService.getArticleById(articleId);
		if (article.isPresent()) {
			return new ResponseEntity<>(article.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
}
