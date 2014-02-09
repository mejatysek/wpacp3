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
public class RoleDto extends AbstractDto {
    private String name;
    private List<Long> users;
    
    public RoleDto(){
	
    }
    
    public RoleDto(Long id, String name, List<Long> users) {
	this.id = id;
	this.name = name;
	this.users = users;
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
     * @return the users
     */
    public List<Long> getUsers() {
	return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<Long> users) {
	this.users = users;
    }
}
