/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Skalda
 */
@Entity
public class State extends AbstractBusinessObject{
    @Column(nullable = false)
    private String name;
    
    @OneToMany(mappedBy="currentState")
    private List<Article> articles;
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
     public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    public void addArticles(Article article){
        if(this.articles == null){
            this.articles = new ArrayList<Article>();
        }
        if(!this.articles.contains(article)){
            articles.add(article);
        }
    }

}
