/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.Article;
import com.wpa.wpa.bo.CorrectorsGroup;
import com.wpa.wpa.bo.Role;
import com.wpa.wpa.bo.User;
import com.wpa.wpa.dto.UserDto;
import com.wpa.wpa.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class UserServiceImpl extends AbstractDataAccessService implements UserService{

    @Override
    public Long addUser(String name, String surname, String email, String password, Long role) {
	return addUser(name, surname, email, password, role, null);
    }

    @Override
    public Long addUser(String name, String surname, String email, String password, Long role, String phone) {
	return addUser(name, surname, email, password, role, phone, null);
    }

    @Override
    public Long addUser(String name, String surname, String email, String password, Long role, String phone, List<Long> correctorsGroups) {
	User u = new User();
	u.setName(name);
	u.setSurname(surname);
	u.setEmail(email);
	u.setPassword(password);
	u.setRole(genericDao.getById(role, Role.class));
	u.setPhone(phone);
	List<CorrectorsGroup> g = new ArrayList<CorrectorsGroup>();
	if(correctorsGroups != null) {
	    for(Long group:correctorsGroups) {
		g.add(genericDao.getById(group, CorrectorsGroup.class));
	    }
	}
	u.setCorrectorsGroups(g);
	return genericDao.saveOrUpdate(u).getId();
    }

    @Override
    public List<UserDto> getAllUsers() {
	List<User> users = genericDao.getAll(User.class);
	List<UserDto> userDtos = new ArrayList<UserDto>();
	if(users!=null) {
	for(User u:users) {
	    userDtos.add(new UserDto(u.getId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(), u.getRole().getId(), DtoTransformerHelper.getIdentifiers(u.getWrittenArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectingArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectorsGroups())));
	}}
	return userDtos;
    }

    @Override
    public UserDto getUserById(Long userId) {
	User u = genericDao.getById(userId, User.class);
        if(u!=null) {
            return new UserDto(u.getId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(), u.getRole().getId(), DtoTransformerHelper.getIdentifiers(u.getWrittenArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectingArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectorsGroups()));
        }
        return new UserDto();
    }

    @Override
    public Long editUser(UserDto dto) {
	User u = genericDao.getById(dto.getId(), User.class);
	u.setName(dto.getName());
	u.setSurname(dto.getSurname());
	u.setPhone(dto.getPhone());
	u.setEmail(dto.getEmail());
	u.setRole(genericDao.getById(dto.getRole(), Role.class));
	List<Article> w= new ArrayList<Article>();
	if(dto.getWrittenAricles()!= null){
	for(Long written:dto.getWrittenAricles()) {
	    w.add(genericDao.getById(written, Article.class));
	}}
	u.setWrittenArticles(w);
	
	List<Article> a= new ArrayList<Article>();
	if(dto.getCorrectingArticles()!=null) {
	for(Long correcting:dto.getCorrectingArticles()) {
	    a.add(genericDao.getById(correcting, Article.class));
	}}
	u.setCorrectingArticles(a);
	List<CorrectorsGroup> g= new ArrayList<CorrectorsGroup>();
	if(dto.getCorrectorsGroups()!=null) {
	for(Long group:dto.getCorrectorsGroups()) {
	    g.add(genericDao.getById(group, CorrectorsGroup.class));
	}}
	u.setCorrectorsGroups(g);
	return genericDao.saveOrUpdate(u).getId();
    }

    @Override
    public List<UserDto> getRolesUser(Long roleId) {
	Role r = genericDao.getById(roleId, Role.class);
	List<User> users = r.getUsers();
	List<UserDto> userDtos = new ArrayList<UserDto>();
	if(users!=null) {
	for(User u:users) {
	    userDtos.add(new UserDto(u.getId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(), u.getRole().getId(), DtoTransformerHelper.getIdentifiers(u.getWrittenArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectingArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectorsGroups())));
	}}
	return userDtos;
    }

    @Override
    public List<UserDto> getCorrectorsGroupsUser(Long correctorsGroupId) {
	CorrectorsGroup cor = genericDao.getById(correctorsGroupId, CorrectorsGroup.class);
	List<User> users = cor.getCorrectors();
	List<UserDto> userDtos = new ArrayList<UserDto>();
	if(users != null) {
	for(User u:users) {
	    userDtos.add(new UserDto(u.getId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(), u.getRole().getId(), DtoTransformerHelper.getIdentifiers(u.getWrittenArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectingArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectorsGroups())));
	}}
	return userDtos;
    }
    
    @Override
    public void deleteUser(Long id) {
        genericDao.removeById(id, User.class);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        User u = genericDao.getByPropertyUnique("email", email, com.wpa.wpa.bo.User.class);
        return u.getId();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User u = genericDao.getByPropertyUnique("email", email, User.class);
	return new UserDto(u.getId(), u.getName(), u.getSurname(), u.getPhone(), u.getEmail(), u.getRole().getId(), DtoTransformerHelper.getIdentifiers(u.getWrittenArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectingArticles()), DtoTransformerHelper.getIdentifiers(u.getCorrectorsGroups()));
    
    }
}
