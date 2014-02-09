/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.ArticleDto;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Skalda
 */
@Transactional
public interface ArticleService {
    
    @Transactional(readOnly=true)
    public List<ArticleDto> getAllArticles();
    
    @Transactional(readOnly=true)
    public ArticleDto getArticleById(Long articleId);
    
    @Transactional(readOnly=true)
    public List<ArticleDto> getCorrectorsArticles(Long cor);
    
    public void deleteArticle(Long articleId);
    
    public Long addArticle(String name, Date deadline, String articleFile, Long currentState, Long author, Long corrector, List<Long> recomendedCorrectors);
    
    
    public Long editArticle(ArticleDto article);
    
    @Transactional(readOnly=true)
    public List<ArticleDto> getStatesArticles(Long stateId);
   
}
