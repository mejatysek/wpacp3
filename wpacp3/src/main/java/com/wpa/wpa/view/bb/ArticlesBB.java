/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.view.bb;

import com.wpa.wpa.dto.ArticleDto;
import com.wpa.wpa.dto.UserDto;
import com.wpa.wpa.helper.SecurityRoleHelper;
import com.wpa.wpa.service.ArticleService;
import com.wpa.wpa.service.UserService;
import com.wpa.wpa.view.helper.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
/**
 *
 * @author Skalda
 */
@Component
@Scope(value="session")
public class ArticlesBB {
    
    private String name;
    private Date deadline;
    private String articleFile;
    private Long currentState = 1l;
    private Long author;
    private Long corrector;
    private List<Long> recomendedCorrectors = new ArrayList<Long>();
    private ArticleDto update = new ArticleDto();
    
    @Autowired
    protected ArticleService articleService;
    
    @Autowired
    protected UserService userService;
    
    protected SecurityRoleHelper roleHelper = new SecurityRoleHelper();
    
    
    @PreAuthorize("hasAnyRole('ROLE_author,ROLE_admin')")
    public void storeArticle() {
        UserDetails u = roleHelper.getUserDetails();
        author =  userService.getUserIdByEmail(u.getUsername());
        
        articleService.addArticle(getName(), getDeadline(), getArticleFile(), (getCurrentState() == 0)?1:getCurrentState(), author, getCorrector(), getRecomendedCorrectors());
        FacesUtil.addMessage("Article was sucessfully added");
        setName(null);setDeadline(null);setArticleFile(null);setCurrentState(1l);setAuthor(null);setCorrector(null);setRecomendedCorrectors(new ArrayList<Long>());
    }
    
    public void loadArticle(Long id) {
        this.setUpdate(articleService.getArticleById(id));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_author,ROLE_admin')")
    public void updateArticle() {
        if(update.getId() != null) {
            articleService.editArticle(update);
            FacesUtil.addMessage("Article was sucessfully updated");
        }
        setUpdate(new ArticleDto());
    }

    @PreAuthorize("hasAnyRole('ROLE_corrector,ROLE_admin')")
    public void correctArticle() {
        if(update.getId() != null) {
            update.setCurrentState(3l);
            articleService.editArticle(update);
            FacesUtil.addMessage("Article was sucessfully corrected");
        }
    }
    
     public List<ArticleDto> getAllArticles(){
        List<ArticleDto> art = new ArrayList<ArticleDto>();
        List<ArticleDto> articles = articleService.getAllArticles();
        if(roleHelper.hasRole("ROLE_admin")) {
            art = articles;
        }
        if(roleHelper.hasRole("ROLE_corrector")) {
            UserDetails u = roleHelper.getUserDetails();
            UserDto user =  userService.getUserByEmail(u.getUsername());
            List<Long> groups = user.getCorrectorsGroups();
            boolean is = false;
            for(ArticleDto a:articles) {
                is = false;
                for(Long g:groups) {
                    if(a.getRecomendedCorrectors().contains(g)) {
                        is = true;
                    }
                }
                if(a.getCurrentState() == 1 && is) {
                    art.add(a);
                }
            }
        }
        if(roleHelper.hasRole("ROLE_author")) {
            UserDetails u = roleHelper.getUserDetails();
            UserDto user =  userService.getUserByEmail(u.getUsername());
            for(ArticleDto a:articles) {
                if(a.getAuthor() == user.getId()) {
                    art.add(a);
                }
            }
        }
        return art;
    }
    
    @PreAuthorize("hasRole('ROLE_corrector')")
    public List<ArticleDto> getCorrectorsArticles() {
        UserDetails user = roleHelper.getUserDetails();
        Long authorId = userService.getUserIdByEmail(user.getUsername());
        return articleService.getCorrectorsArticles(authorId);
    } 
     
    @PreAuthorize("hasAnyRole('ROLE_author,ROLE_admin')")
    public void deleteArticle(Long articleId) {
        articleService.deleteArticle(articleId);
    }
    
    @PreAuthorize("hasRole('ROLE_corrector')")
    public void takeArticle(Long articleId) {
        ArticleDto art = articleService.getArticleById(articleId);
        UserDetails user = roleHelper.getUserDetails();
        Long userId = userService.getUserIdByEmail(user.getUsername());
        art.setCorrector(userId);
        art.setCurrentState(2l);
        articleService.editArticle(art);
        FacesUtil.addMessage("Article was succesfully flaged");
    }
    
    public boolean isAllowToDelete(Long author) {
        UserDetails user = roleHelper.getUserDetails();
        Long userId = userService.getUserIdByEmail(user.getUsername());
        return roleHelper.hasRole("ROLE_admin") || (roleHelper.hasRole("ROLE_author") && userId == author);
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the articleFile
     */
    public String getArticleFile() {
        return articleFile;
    }

    /**
     * @param articleFile the articleFile to set
     */
    public void setArticleFile(String articleFile) {
        this.articleFile = articleFile;
    }

    /**
     * @return the currentState
     */
    public Long getCurrentState() {
        return currentState;
    }

    /**
     * @param currentState the currentState to set
     */
    public void setCurrentState(Long currentState) {
        this.currentState = currentState;
    }

    /**
     * @return the author
     */
    public Long getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Long author) {
        this.author = author;
    }

    public UserDto getAuthorDto(Long author) {
        return userService.getUserById(author);
    }
    
    /**
     * @return the corrector
     */
    public Long getCorrector() {
        return corrector;
    }

    /**
     * @param corrector the corrector to set
     */
    public void setCorrector(Long corrector) {
        this.corrector = corrector;
    }

    /**
     * @return the recomendedCorrectors
     */
    public List<Long> getRecomendedCorrectors() {
        return recomendedCorrectors;
    }

    /**
     * @param recomendedCorrectors the recomendedCorrectors to set
     */
    public void setRecomendedCorrectors(List<Long> recomendedCorrectors) {
        this.recomendedCorrectors = recomendedCorrectors;
    }

    /**
     * @return the update
     */
    public ArticleDto getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(ArticleDto update) {
        this.update = update;
    }


}
