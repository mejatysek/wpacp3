/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.view.bb;

import com.wpa.wpa.dto.UserDto;
import com.wpa.wpa.service.UserService;
import com.wpa.wpa.view.helper.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
/**
 *
 * @author Skalda
 */
@Component
@Scope(value="session")
@PreAuthorize("hasRole('ROLE_admin')")
public class UserBB {
    
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long role;
    private String phone;
    private List<Long> correctorsGroups = new ArrayList<Long>();
    private UserDto update = new UserDto();
    
    @Autowired
    protected UserService userService;
    
    public void storeUser() {
        userService.addUser(getName(), getSurname(), getEmail(), getPassword(), getRole(), getPhone(), getCorrectorsGroups());
        FacesUtil.addMessage("User was sucessfully added");
        setName(null);setSurname(null);setEmail(null);setPassword(null);setRole(null);setPhone(null);setCorrectorsGroups(new ArrayList<Long>());
    }

    public void loadUser(Long id) {
        this.setUpdate(userService.getUserById(id));
    }
    
    public void updateUser() {
        
        if(getUpdate().getId() != null) {
            userService.editUser(getUpdate());
            FacesUtil.addMessage("User was sucessfully updated");
        }
        this.setUpdate(new UserDto());
    }
    
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the correctorsGroups
     */
    public List<Long> getCorrectorsGroups() {
        return correctorsGroups;
    }

    /**
     * @param correctorsGroups the correctorsGroups to set
     */
    public void setCorrectorsGroups(List<Long> correctorsGroups) {
        System.out.println("aaa");
        this.correctorsGroups = correctorsGroups;
    }

    /**
     * @return the update
     */
    public UserDto getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(UserDto update) {
        this.update = update;
    }
    
    
}
