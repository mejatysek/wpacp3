/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.ArticleDto;
import com.wpa.wpa.dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mejty
 */
public class ArticleServiceImplTest extends AbstractServiceTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CorrectorsGroupService correctorsGroupService;
    @Autowired
    private StateService stateService;
    
    @Test
    public void testAddAndRetrieveArticle() {
	String name = "Name" + System.currentTimeMillis();
	Date deadline = new Date();
	String articleFile = "file";
	Long currentState = addState();
	Long author = addUser();
	Long cor = addUser();
	List<Long> corGroup = new ArrayList<Long>();
	corGroup.add(addCorrectorsGroup());
	corGroup.add(addCorrectorsGroup());
	corGroup.add(addCorrectorsGroup());
	Long articleId = articleService.addArticle(name, deadline, articleFile, currentState, author, cor, corGroup);
	ArticleDto art = articleService.getArticleById(articleId);
	
	assertEquals(articleId, art.getId());
	assertEquals(articleFile, art.getArticleFile());
	assertEquals(name, art.getName());
	assertEquals(author, art.getAuthor());
	assertEquals(cor, art.getCorrector());
	assertEquals(corGroup, art.getRecomendedCorrectors());

        List<ArticleDto> arts = articleService.getAllArticles();
	assertEquals(1, arts.size());
	assertEquals(articleId, arts.get(0).getId());
	
    }
    
    @Test
    public void testEditArticle() {
	Long articleId = addArticle();
	ArticleDto artDto = articleService.getArticleById(articleId);
	String name = "Name" + System.currentTimeMillis();
	Date deadline = new Date();
	String articleFile = "file2";
	String articleCorrectedFile = "file22";
	Long currentState = addState();
	Long author = addUser();
	Long cor = addUser();
        
	artDto.setName(name);
	artDto.setDeadline(deadline);
	artDto.setArticleFile(articleFile);
	artDto.setArticleFileCorrected(articleCorrectedFile);
	artDto.setCurrentState(currentState);
	artDto.setAuthor(author);
	artDto.setCorrector(cor);
        
	Long newArticleId = articleService.editArticle(artDto);
	
        assertEquals(newArticleId, articleId);
	
        artDto = articleService.getArticleById(newArticleId);
	
	assertEquals(articleId, artDto.getId());
	assertEquals(articleFile, artDto.getArticleFile());
	assertEquals(name, artDto.getName());
	assertEquals(author, artDto.getAuthor());
	assertEquals(articleCorrectedFile, artDto.getArticleFileCorrected());
	assertEquals(cor, artDto.getCorrector());
	
    }
    @Test
    public void testDeleteArticle() {
	Long articleId = addArticle();
        List<ArticleDto> a = articleService.getAllArticles();
        assertEquals(1, a.size());
	articleService.deleteArticle(articleId);
	a = articleService.getAllArticles();
	assertEquals(0, a.size());
    }
    
    @Test
    public void testStatesArticles() {
	Long state1 = addState();
	Long state2 = addState();
	for (int i = 0; i < 3; i++) {
            addArticle(state1);
        }
        addArticle(state2);
	List<ArticleDto> articles = articleService.getStatesArticles(state1);
	assertEquals(3, articles.size());
	List<ArticleDto> articles2 = articleService.getStatesArticles(state2);
	assertEquals(1, articles2.size());
    }
    
    @Test
    public void testCorrestorsArticles() {
	Long state1 = addState();
        Long corr1 = addUser();
        Long corr2 = addUser();
        Long corr3 = addUser();
	for (int i = 0; i < 3; i++) {
            addArticle(state1,corr1);
        }
        addArticle(state1,corr2);
	List<ArticleDto> articles = articleService.getCorrectorsArticles(corr1);
	assertEquals(3, articles.size());
	List<ArticleDto> articles2 = articleService.getCorrectorsArticles(corr2);
	assertEquals(1, articles2.size());
        List<ArticleDto> articles3 = articleService.getCorrectorsArticles(corr3);
	assertEquals(0, articles2.size());
    }
    
    private Long addArticle() {
	return addArticle(addState());
    }
    
    private Long addArticle(Long state) {
	return addArticle(state,addUser());
	
    }
        private Long addArticle(Long state, Long corr) {
	String name = "Name" + System.currentTimeMillis();
	Date deadline = new Date();
	String articleFile = "file";
	Long author = addUser();
	Long cor = corr;
	List<Long> corGroup = new ArrayList<Long>();
	corGroup.add(addCorrectorsGroup());
	corGroup.add(addCorrectorsGroup());
	corGroup.add(addCorrectorsGroup());
	return articleService.addArticle(name, deadline, articleFile, state, author, cor, corGroup);
	
    }
    private Long addCorrectorsGroup() {
	String name = "Name" + System.currentTimeMillis();
	return correctorsGroupService.addCorrectorsGroup(name);
    }
    
    private Long addState() {
	return stateService.addState("test");
    }
    
    private Long addUser() {
	return userService.addUser("a", "b", "abc" + System.currentTimeMillis(), "a", addRole());
    }
    
    private Long addRole() {
	String name = "Name" + System.currentTimeMillis();
	return roleService.addRole(name);
    }
}
