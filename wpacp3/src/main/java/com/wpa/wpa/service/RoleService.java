/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.RoleDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Skalda
 */
@Transactional
public interface RoleService {
    
    @Transactional(readOnly=true)
    public List<RoleDto> getAllRoles();
    @Transactional(readOnly=true)
    public RoleDto getRoleById(Long id);
    public Long addRole(String name);
}
