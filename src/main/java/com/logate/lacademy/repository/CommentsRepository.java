package com.logate.lacademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logate.lacademy.domains.Comments;
import com.logate.lacademy.web.dto.CommentsDTO;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

//	@Query(value = "SELECT c FROM Comments c WHERE article_id = ?1")
//	Page<Comments> findByArticleId(Integer id, Pageable pageable);
	
//	public List<Comments> findAllByArticleId(Integer id);
	
	@Query(value = "select new com.logate.lacademy.web.dto.CommentsDTO (c.id, a.id, c.body, c.published) " 
			+ "from Comments c " 
			+ "left join c.article a "
            + "where a.id = ?1")
	public List<CommentsDTO> searchCommentsByArticle(Integer id);
//	
//	public List<Comments> findByArticleId(Integer id);
}