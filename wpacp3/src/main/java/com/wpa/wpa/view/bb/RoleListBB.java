/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.view.bb;

import com.wpa.wpa.dto.RoleDto;
import com.wpa.wpa.service.RoleService;
import com.wpa.wpa.view.helper.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("roleListBB")
public class RoleListBB {
    @Autowired
    private RoleService roleService;
    
    public List<RoleDto> getAllRoles(){
        return roleService.getAllRoles();
    }
    
    
}
