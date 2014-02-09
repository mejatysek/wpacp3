/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.Article;
import com.wpa.wpa.bo.CorrectorsGroup;
import com.wpa.wpa.bo.State;
import com.wpa.wpa.bo.User;
import com.wpa.wpa.dto.ArticleDto;
import com.wpa.wpa.helper.DtoTransformerHelper;
import com.wpa.wpa.helper.HibernateTools;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.primefaces.event.DateSelectEvent; 
/**
 *
 * @author Skalda
 */
@Component
public class ArticleServiceImpl extends AbstractDataAccessService implements ArticleService {

    @Override
    public List<ArticleDto> getAllArticles() {
	List<Article> articles = genericDao.getAllOrderedAsc("currentState", "deadline" ,Article.class);
	List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();
	
	for(Article a:articles) {
	    articleDtos.add(new ArticleDto(a.getId(), a.getName(), a.getDeadline(), a.getArticleFile(), a.getArticleFileCorrected(), HibernateTools.getIdentifier(a.getCurrentState()), HibernateTools.getIdentifier(a.getAuthor()), HibernateTools.getIdentifier(a.getCorrector()), DtoTransformerHelper.getIdentifiers(a.getRecomendedCorrectors())));
	}
	return articleDtos;
    }

    @Override
    public ArticleDto getArticleById(Long articleId) {
	Article a = genericDao.getById(articleId, Article.class);
	return new ArticleDto(a.getId(), a.getName(), a.getDeadline(), a.getArticleFile(), a.getArticleFileCorrected(), HibernateTools.getIdentifier(a.getCurrentState()), HibernateTools.getIdentifier(a.getAuthor()), HibernateTools.getIdentifier(a.getCorrector()), DtoTransformerHelper.getIdentifiers(a.getRecomendedCorrectors()));
    }

    @Override
    public void deleteArticle(Long articleId) {
	genericDao.removeById(articleId, Article.class);
    }

    

    @Override
    public Long editArticle(ArticleDto article) {
	Article a = new Article();
	a.setId(article.getId());
	a.setName(article.getName());
	a.setDeadline(article.getDeadline());
	a.setArticleFile(article.getArticleFile());
	a.setArticleFileCorrected(article.getArticleFileCorrected());
	a.setAuthor(genericDao.getById(article.getAuthor(), User.class));
        if(article.getCorrector() != null) {
            a.setCorrector(genericDao.getById(article.getCorrector(), User.class));
        }
	a.setCurrentState(genericDao.getById(article.getCurrentState(), State.class));
	for(Long groupId:article.getRecomendedCorrectors()) {
	    a.addRecomendedCorector(genericDao.getById(groupId, CorrectorsGroup.class));
	}
	
	return genericDao.saveOrUpdate(a).getId();
    }

    @Override
    public List<ArticleDto> getStatesArticles(Long stateId) {
	State st = genericDao.getById(stateId, State.class);
	System.out.println("ttttt" + stateId + " " + st.getArticles());
	List<Article> articles = st.getArticles();
        List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();
        if(articles != null) {
        for(Article a:articles) {
	    articleDtos.add(new ArticleDto(a.getId(), a.getName(), a.getDeadline(), a.getArticleFile(), a.getArticleFileCorrected(), HibernateTools.getIdentifier(a.getCurrentState()), HibernateTools.getIdentifier(a.getAuthor()), HibernateTools.getIdentifier(a.getCorrector()), DtoTransformerHelper.getIdentifiers(a.getRecomendedCorrectors())));
	}}
	return articleDtos;   
    }

    @Override
    public Long addArticle(String name, Date deadline, String articleFile, Long currentState, Long author, Long corrector, List<Long> recomendedCorrectors) {
	       System.out.println(author + " AGGGA " + corrector + " " + currentState);
        Article a = new Article();
	a.setName(name);
	a.setDeadline(deadline);
	a.setArticleFile(articleFile);
	a.setCurrentState(genericDao.getById(currentState, State.class));
	a.setAuthor(genericDao.getById(author, User.class));
        if(corrector != null) {
            a.setCorrector(genericDao.getById(corrector, User.class));
        }
	List<CorrectorsGroup> correct = new ArrayList<CorrectorsGroup>();
	if(recomendedCorrectors != null) {
	for(Long groupId:recomendedCorrectors) {
	    correct.add(genericDao.getById(groupId, CorrectorsGroup.class));
	}}
	a.setRecomendedCorrectors(correct);
	
	return genericDao.saveOrUpdate(a).getId();
    }

    @Override
    public List<ArticleDto> getCorrectorsArticles(Long cor) {
        User u = genericDao.getById(cor, User.class);
        List<Article> articles = u.getCorrectingArticles();
        List<ArticleDto> articleDtos = new ArrayList<ArticleDto>();
        if(articles != null) {
        for(Article a:articles) {
	    articleDtos.add(new ArticleDto(a.getId(), a.getName(), a.getDeadline(), a.getArticleFile(), a.getArticleFileCorrected(), HibernateTools.getIdentifier(a.getCurrentState()), HibernateTools.getIdentifier(a.getAuthor()), HibernateTools.getIdentifier(a.getCorrector()), DtoTransformerHelper.getIdentifiers(a.getRecomendedCorrectors())));
	}}
	return articleDtos; 
    }
    
}
