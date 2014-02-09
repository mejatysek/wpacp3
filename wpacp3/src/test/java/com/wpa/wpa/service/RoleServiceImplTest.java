/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.RoleDto;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mejty
 */
public class RoleServiceImplTest extends AbstractServiceTest{
    
    @Autowired
    private RoleService instance;
    @Autowired
    private UserService userService;
    
    @Test
    public void testAddAndRetrieveRole() {
        String name = "Hotovo";
        Long result = instance.addRole(name);
        
        RoleDto roleDto = instance.getRoleById(result);
        assertEquals(name, roleDto.getName());
	
        List<RoleDto> roles = instance.getAllRoles();
	assertEquals(1, roles.size());
    }
   
    @Test
    public void testRoleGetUsers() {
        String name = "Hotovo";
        Long result = instance.addRole(name);
        
        RoleDto roleDto = instance.getRoleById(result);
        assertEquals(null, roleDto.getUsers().size());
	
        addUser(result);
        addUser(result);
        
        roleDto = instance.getRoleById(result);
        assertEquals(2, roleDto.getUsers().size());
    }
    
   private Long addUser(Long role) {
	String name = "name";
	String surname = "surname";
        String passwd = "passwd";
        String email = "email" + System.currentTimeMillis();
	
        return userService.addUser(name, surname, email, passwd, role, "212", null);
    }
    
}
