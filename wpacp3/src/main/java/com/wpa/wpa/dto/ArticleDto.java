/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Skalda
 */
public class ArticleDto extends AbstractDto {
    private String name;
    private Date deadline;
    private String articleFile;
    private String articleFileCorrected;
    private Long currentState;
    private Long author;
    private Long corrector;
    private List<Long> recomendedCorrectors;

    public ArticleDto() {
	
    }
    
    public ArticleDto(Long id, String name, Date deadline, String articleFile, String articleFileCorrected, Long currentState, Long author, Long corrector, List<Long> recomendedCorrectors) {
	this.id = id;
	this.name = name;
	this.deadline = deadline;
	this.articleFile = articleFile;
	this.articleFileCorrected = articleFileCorrected;
	this.currentState = currentState;
	this.author = author;
	this.corrector = corrector;
	this.recomendedCorrectors = recomendedCorrectors;
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
     * @return the articleFileCorrected
     */
    public String getArticleFileCorrected() {
	return articleFileCorrected;
    }

    /**
     * @param articleFileCorrected the articleFileCorrected to set
     */
    public void setArticleFileCorrected(String articleFileCorrected) {
	this.articleFileCorrected = articleFileCorrected;
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
}
