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
public class UserDto extends AbstractDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Long role;
    private List<Long> writtenAricles;
    private List<Long> correctingArticles;
    private List<Long> correctorsGroups;

    public UserDto() {
    }
    
    public UserDto(Long id, String name, String surname, String phone, String email, Long role, List<Long> writtenArticles, List<Long> correctingArticles, List<Long> correctorsGroups) {
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.phone = phone;
	this.email = email;
	this.role = role;
	this.writtenAricles = writtenArticles;
	this.correctingArticles = correctingArticles;
	this.correctorsGroups = correctorsGroups;
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
     * @return the surname
     */
    public String getSurname() {
	return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
	return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
	this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * @return the roles
     */
    public Long getRole() {
	return role;
    }

    /**
     * @param roles the roles to set
     */
    public void setRole(Long role) {
	this.role = role;
    }

    /**
     * @return the writtenAricles
     */
    public List<Long> getWrittenAricles() {
	return writtenAricles;
    }

    /**
     * @param writtenAricles the writtenAricles to set
     */
    public void setWrittenAricles(List<Long> writtenAricles) {
	this.writtenAricles = writtenAricles;
    }

    /**
     * @return the correctingArticles
     */
    public List<Long> getCorrectingArticles() {
	return correctingArticles;
    }

    /**
     * @param correctingArticles the correctingArticles to set
     */
    public void setCorrectingArticles(List<Long> correctingArticles) {
	this.correctingArticles = correctingArticles;
    }

    /**
     * @return the correctorsGroups
     */
    public List<Long> getCorrectorsGroups() {
	return correctorsGroups;
    }

    /**
     * @param correctorsGroups the correctorsGroups to set
     */
    public void setCorrectorsGroups(List<Long> correctorsGroups) {
	this.correctorsGroups = correctorsGroups;
    }
}
