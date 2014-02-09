/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.dto;

import java.util.List;


/**
 *
 * @author Skalda
 */
public class StateDto extends AbstractDto {
    private String name;
    private List<Long> articles;

    public StateDto() {
    }
    
    public StateDto(Long id, String name, List<Long> articles) {
	this.id = id;
	this.name = name;
	this.articles = articles;
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
     * @return the articles
     */
    public List<Long> getArticles() {
	return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Long> articles) {
	this.articles = articles;
    }
    
}
