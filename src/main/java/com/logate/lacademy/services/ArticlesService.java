package com.logate.lacademy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.logate.lacademy.domains.Article;
import com.logate.lacademy.domains.Employee;
import com.logate.lacademy.repository.ArticleRepository;

@Description(value = "Employee Service")
@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	public Page<Article> getAllArticles(Pageable pageable){
		return articleRepository.findAll(pageable);
	}
	
	public Optional<Article> findById (Integer articleId){
		return articleRepository.findById(articleId);
	}
	
	public Article updateArticle(Article article, Integer Id){
		Optional<Article> articleOptional = articleRepository.findById(Id);
		if (articleOptional.isPresent()){
			return articleRepository.save(article);
		}
		return null;
	}

	public Optional<Article> getArticleById(Integer articleId) {
		
		return articleRepository.findById(articleId);
	}

	public Article store(Article article) {
		return articleRepository.save(article);
	}
	
	public void deleteArticle(Integer id) {
		articleRepository.deleteById(id);
	}
	
}
