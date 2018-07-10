package com.logate.lacademy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javassist.expr.NewArray;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.logate.lacademy.domains.Comments;
import com.logate.lacademy.repository.CommentsRepository;
import com.logate.lacademy.web.dto.CommentsDTO;
import com.logate.lacademy.web.dto.EmployeeDTO;

@Service
public class CommentsService {
	
	@Autowired
	CommentsRepository commentsRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//izlistava sve clanke
	public Page<Comments> findByPageable(Pageable pageable){
		return commentsRepository.findAll(pageable);
	}
	// postovanje komentara
	public Comments store(Comments comment, Integer articleId){
		return commentsRepository.save(comment);
	}
//azuriranje komentara
	public Comments update(Comments comment, Integer id){
		Optional<Comments> dbComment = commentsRepository.findById(id);
		if (dbComment.isPresent()){
			return commentsRepository.save(comment);
		}
		return null;
	}
//postavljanje komentara za odredjeni clanak
	public Comments postCommentsForArticle(Comments comment) {
		return commentsRepository.save(comment);
	}
	
	// updatovaanje komentara
		public Comments updateComment(Comments comment, Integer id) {
			Optional<Comments> 	commentOptional = commentsRepository.findById(id);
			if (commentOptional.isPresent()){
			return commentsRepository.save(comment);
			}
			return null;
	}

		//brisanje komentara po id-u
		public void deleteComment(Integer id) {
			commentsRepository.deleteById(id);
			
		}
	
		//prikaz svih komentara za odredjeni clanak
	public List<CommentsDTO> byArticle(Integer id){
		return commentsRepository.searchCommentsByArticle(id);
	}
}
