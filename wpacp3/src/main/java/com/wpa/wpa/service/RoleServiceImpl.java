/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.Role;
import com.wpa.wpa.dto.RoleDto;
import com.wpa.wpa.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class RoleServiceImpl extends AbstractDataAccessService implements RoleService{

    @Override
    public List<RoleDto> getAllRoles() { 
	List<Role> roles = genericDao.getAll(Role.class);
	List<RoleDto> roleDaos = new ArrayList<RoleDto>();
	for(Role r:roles) {
	    roleDaos.add(new RoleDto(r.getId(), r.getName(), DtoTransformerHelper.getIdentifiers(r.getUsers())));
	}

	return roleDaos;
    }
    
    @Override
    public RoleDto getRoleById(Long roleId) {
	Role r = genericDao.getById(roleId, Role.class);
	return new RoleDto(r.getId(), r.getName(), DtoTransformerHelper.getIdentifiers(r.getUsers()));
    }

    @Override
    public Long addRole(String name) {
	Role r = new Role();
	r.setName(name);
	return genericDao.saveOrUpdate(r).getId();
    }
    
}
