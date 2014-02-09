/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.bo;

import com.wpa.wpa.provider.HashProvider;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author mihalmat
 */
@Entity
@Table(name = "Users")
@Configurable(preConstruction=true)
public class User extends AbstractBusinessObject {
    
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Article> writtenArticles;
    @OneToMany(mappedBy = "corrector")
    @OrderBy("currentState.id, deadline")
    private List<Article> correctingArticles;
    @ManyToOne
    private Role role;
    @ManyToMany
    @JoinTable(
      name = "CORGROUP_COR",
    joinColumns = {
        @JoinColumn(name = "COR_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {
        @JoinColumn(name = "GRUP_ID", referencedColumnName = "ID")})
    private List<CorrectorsGroup> correctorsGroups;

    
    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted
    

    public void addCorrectorsGroup(CorrectorsGroup correctorsGroup) {
        if (this.correctorsGroups == null) {
            correctorsGroups = new ArrayList<CorrectorsGroup>();
        }
        if (!this.correctorsGroups.contains(correctorsGroup)) {
            correctorsGroups.add(correctorsGroup);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = hashProvider.computeHash(System.nanoTime() + "");
        this.password = hashProvider.computeHash(salt + password + salt);
    }

    public boolean hasPassword(String password){
        System.out.println("PASSS: " + password);
        System.out.println("SALT: " + salt);
        System.out.println("PASS+SALT: " + salt + password + salt);
        System.out.println("THIS.PASS: " + this.password);
        System.out.println("comp pass: " + hashProvider.computeHash(salt + password + salt));
        if(hashProvider.computeHash(salt + password + salt).equals(this.password)){
            return true;
        }
        return false;
    }

    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
    public Role getRole() {
        return role;
    }

    /**
     * @param roles the roles to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the corectorsGroup
     */
    public List<CorrectorsGroup> getCorrectorsGroups() {
        return correctorsGroups;
    }

    /**
     * @param corectorsGroup the corectorsGroup to set
     */
    public void setCorrectorsGroups(List<CorrectorsGroup> correctorsGroup) {
        for (CorrectorsGroup correctorsGroup1 : correctorsGroup) {
            correctorsGroup1.addCorrector(this);
        }
        this.correctorsGroups = correctorsGroup;
    }

    public void addWrittenArticle(Article article) {
        if (this.writtenArticles == null) {
            this.writtenArticles = new ArrayList<Article>();
        }
        if (!this.writtenArticles.contains(article)) {
            writtenArticles.add(article);
        }
    }

    /**
     * @return the writtenArcticles
     */
    public List<Article> getWrittenArticles() {
        return writtenArticles;
    }

    /**
     * @param writtenArcticles the writtenArcticles to set
     */
    public void setWrittenArticles(List<Article> writtenArticles) {
        this.writtenArticles = writtenArticles;
    }

    public void addCorrectingArticle(Article article) {
        if (this.correctingArticles == null) {
            this.correctingArticles = new ArrayList<Article>();
        }
        if (!this.correctingArticles.contains(article)) {
            correctingArticles.add(article);
        }
    }

    /**
     * @return the correctingArticles
     */
    public List<Article> getCorrectingArticles() {
        return correctingArticles;
    }

    /**
     * @param correctingArticles the correctingArticles to set
     */
    public void setCorrectingArticles(List<Article> correctingArticles) {
        this.correctingArticles = correctingArticles;
    }
}
