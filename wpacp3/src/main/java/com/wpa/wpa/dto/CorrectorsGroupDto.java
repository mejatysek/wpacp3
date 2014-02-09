/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Skalda
 */
@XmlRootElement(name = "groups")
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorrectorsGroupDto extends AbstractDto implements Serializable{
    private String name;
    private List<Long> correctors;
    private List<Long> waitingArticles;

    public CorrectorsGroupDto() {
	
    }
    
    public CorrectorsGroupDto(Long id, String name, List<Long> correctors, List<Long> waitingArticles) {
	this.id = id;
	this.name = name;
	this.correctors = correctors;
	this.waitingArticles = waitingArticles;
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
     * @return the correctors
     */
    public List<Long> getCorrectors() {
	return correctors;
    }

    /**
     * @param correctors the correctors to set
     */
    public void setCorrectors(List<Long> correctors) {
	this.correctors = correctors;
    }

    /**
     * @return the waitingArticles
     */
    public List<Long> getWaitingArticles() {
	return waitingArticles;
    }

    /**
     * @param waitingArticles the waitingArticles to set
     */
    public void setWaitingArticles(List<Long> waitingArticles) {
	this.waitingArticles = waitingArticles;
    }
}
