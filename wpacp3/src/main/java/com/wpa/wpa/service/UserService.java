/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.UserDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Skalda
 */
@Transactional
public interface UserService {
    public Long addUser(String name, String surname, String email, String password, Long role);
    public Long addUser(String name, String surname, String email, String password, Long role, String phone);
    public Long addUser(String name, String surname, String email, String password, Long role, String phone, List<Long> correctorsGroups);
    
    @Transactional(readOnly=true)
    public List<UserDto> getAllUsers();
    
    @Transactional(readOnly=true)
    public UserDto getUserById(Long userId);
    
    @Transactional(readOnly=true)
    public Long getUserIdByEmail(String email);
   
    @Transactional(readOnly=true)
    public UserDto getUserByEmail(String email);
    
    public Long editUser(UserDto dto);
    
    @Transactional(readOnly=true)
    public List<UserDto> getRolesUser(Long roleId);
    
    @Transactional(readOnly=true)
    public List<UserDto> getCorrectorsGroupsUser(Long correctorsGroupId); 
    
    public void deleteUser(Long id);
}
