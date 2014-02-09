/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Skalda
 */
@Entity
public class Article extends AbstractBusinessObject{
    @Column(nullable = false)
    private String name;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deadline;
    @Column(nullable = false)
    private String articleFile;
    @Column
    private String articleFileCorrected;
    @ManyToOne
    private State currentState;
    @ManyToOne
    private User author;
    @ManyToOne
    private User corrector;
    @ManyToMany
    /*@JoinTable(
      name = "ARTICLE_CORECTORS",
    joinColumns = {
        @JoinColumn(name = "ART_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "CORECTORSGROUP_ID", referencedColumnName = "ID")})*/
    private List<CorrectorsGroup> recomendedCorrectors;
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDeadline() {
	return deadline;
    }
    
    public void setDeadline(Date deadline) {
	this.deadline = deadline;
    }
    
    public String getArticleFile() {
        return articleFile;
    }

    public void setArticleFile(String articleFile) {
        this.articleFile = articleFile;
    }
    
    public String getArticleFileCorrected() {
        return articleFileCorrected;
    }

    public void setArticleFileCorrected(String articleFileCorrected) {
        this.articleFileCorrected = articleFileCorrected;
    }
    
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        currentState.addArticles(this);
    }

    /**
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
        author.addWrittenArticle(this);
    }

    /**
     * @return the corrector
     */
    public User getCorrector() {
        return corrector;
    }

    /**
     * @param corrector the corrector to set
     */
    public void setCorrector(User corrector) {
        this.corrector = corrector;
        corrector.addCorrectingArticle(this);
    }
    public void addRecomendedCorector(CorrectorsGroup group){
        if(this.recomendedCorrectors == null){
            recomendedCorrectors = new ArrayList<CorrectorsGroup>();
        }
        if(!this.recomendedCorrectors.contains(group)){
            recomendedCorrectors.add(group);
        }
    }
    /**
     * @return the recomendedCorrectors
     */
    public List<CorrectorsGroup> getRecomendedCorrectors() {
        return recomendedCorrectors;
    }

    /**
     * @param recomendedCorrectors the recomendedCorrectors to set
     */
    public void setRecomendedCorrectors(List<CorrectorsGroup> recomendedCorrectors) {
        for (CorrectorsGroup correctorsGroup : recomendedCorrectors) {
            correctorsGroup.addWaitingArticle(this);
        }
        this.recomendedCorrectors = recomendedCorrectors;
    }
    

}
