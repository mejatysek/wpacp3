/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author mejty
 */
@Entity
public class Role extends AbstractBusinessObject{
    @Column
    private String name;
    @OneToMany(mappedBy="role")
    private List<User> users; 

    public void addUser(User user){
         if(this.users == null){
            users = new ArrayList<User>();
        }
        if(!this.users.contains(user)){
            users.add(user);
        }
    }
    
    /**
     * @return the jmeno
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
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        for (User user : users) {
            user.setRole(this);
        }
        this.users = users;
    }

}
