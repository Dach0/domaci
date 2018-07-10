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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logate.lacademy.domains.Article;
import com.logate.lacademy.domains.Comments;
import com.logate.lacademy.domains.Employee;
import com.logate.lacademy.services.CommentsService;
import com.logate.lacademy.web.dto.CommentsDTO;

@Controller
@RequestMapping("/api")
public class CommentsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	CommentsService commentsService;

	//vraca sve komentare
	@RequestMapping(
			value = "/articles/comments",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comments>> getAllCommentsPageable(Pageable pageable){
		Page<Comments> commentsPage = commentsService.findByPageable(pageable);
		return new ResponseEntity<List<Comments>>(commentsPage.getContent(), HttpStatus.OK);
	}
	
	//vraca sve komentare za odredjeni artikal
	@RequestMapping(value = "/articles/{id}/comments", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentsDTO> byArticle(@PathVariable (value="id") Integer id){
		return commentsService.byArticle(id);
	}
	
//	@RequestMapping(value = "/articles/{id}/comments", 
//			method = RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public Page<Comments> getCommentsForParticularArticle(@PathVariable(value = "id") Integer id, Pageable page)
//	{
//		return commentsService.findByArticleId(id, page);
//	}
	
// dodavanje novog komnetara za neki clanak
	@RequestMapping(value = "/articles/{id}/comments", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comments> postComment(@PathVariable(value = "id")Integer id, @RequestBody Comments comment)
	{
		Comments dbComments = commentsService.postCommentsForArticle(comment);
		return new ResponseEntity<Comments>(dbComments, HttpStatus.CREATED);
	}
	
//updateovnjae komentara za neki clanak
	@RequestMapping(
			value = "/articles/{articleId}/comments/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comments> updateComment(@RequestBody Comments comment, @PathVariable Integer articleId, @PathVariable Integer id){
		if (comment.getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Comments updatedComment = commentsService.updateComment(comment, id);
		return Optional.ofNullable(updatedComment)
				.map(cmt -> new ResponseEntity<>(cmt, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	//brisanje komentara po identifikatoru
	
	@RequestMapping(value = "/comments/{id}", 
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteComment(@PathVariable Integer id){
		commentsService.deleteComment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
