/*
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
    private RoleService role;
    
    @Test
    public void testAddAndRetrieveRole() {
        String name = "Hotovo";
        Long result = role.addRole(name);
        RoleDto expResult = role.getRoleById(result);
        assertEquals(name, expResult.getName());
	List<RoleDto> roles = role.getAllRoles();
	assertEquals(1, roles.size());
    }
 
}
