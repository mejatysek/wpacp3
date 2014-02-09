/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author mejty
 */
@Entity
public class CorrectorsGroup extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy="correctorsGroups")
    private List<User> correctors;
    @ManyToMany(mappedBy = "recomendedCorrectors")
    @OrderBy("deadline")
    private List<Article> waitingArticles;

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

    public void addCorrector(User corrector) {
        if (this.correctors == null) {
            correctors = new ArrayList<User>();
        }
        if (!this.correctors.contains(corrector)) {
            correctors.add(corrector);
        }
    }

    /**
     * @return the correctors
     */
    public List<User> getCorrectors() {
        return correctors;
    }

    /**
     * @param correctors the correctors to set
     */
    public void setCorrectors(List<User> correctors) {
        this.correctors = correctors;
    }
    
    public void addWaitingArticle(Article article){
        if(this.waitingArticles == null){
            waitingArticles = new ArrayList<Article>();
        }
        if(!this.waitingArticles.contains(article)){
            waitingArticles.add(article);
        }
    }
    /**
     * @return the waitingArticles
     */
    public List<Article> getWaitingArticles() {
        return waitingArticles;
    }

    /**
     * @param waitingArticles the waitingArticles to set
     */
    public void setWaitingArticles(List<Article> waitingArticles) {
        for (Article article : waitingArticles) {
            article.addRecomendedCorector(this);
        }
        this.waitingArticles = waitingArticles;
    }
}
