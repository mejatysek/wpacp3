
package com.wpa.wpa.service;

import com.wpa.wpa.dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mejty
 */
public class UserServiceImplTest extends AbstractServiceTest{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CorrectorsGroupService correctorsGroupService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StateService stateService;
    
    
    @Test
    public void testAddAndRetrieveUser() {
        
        String name = "name";
	String surname = "surname";
        String passwd = "passwd";
        String email = "email" + System.currentTimeMillis();
	Long role = addRole();
        String phone = "phone";
	List<Long> cor = new ArrayList<Long>();
	cor.add(addCorrectorsGroup());
	cor.add(addCorrectorsGroup());
	
        Long id = userService.addUser(name, surname, email, passwd, role, phone, cor);
        UserDto userDto = userService.getUserById(id);
        
	assertEquals(name, userDto.getName());
	assertEquals(surname, userDto.getSurname());
	assertEquals(email, userDto.getEmail());
	assertEquals(phone, userDto.getPhone());
	assertEquals(role, userDto.getRole());
	assertEquals(cor.size(), userDto.getCorrectorsGroups().size());
	assertEquals(cor, userDto.getCorrectorsGroups());
        
	List<UserDto> users = userService.getAllUsers();
	assertEquals(1, users.size());
	assertEquals(id, users.get(0).getId());        
    }
    
    @Test
    public void testEditUser() {
	Long id = addUser();
	UserDto dto = userService.getUserById(id);
	String newName = "name2";
	String newSurname = "surname2";
        String newEmail = "email2" + System.currentTimeMillis();
	List<Long> articles = new ArrayList<Long>();
        articles.add(addArticle(id));
	Long role = addRole();
        
	dto.setName(newName);
	dto.setSurname(newSurname);
	dto.setEmail(newEmail);
	dto.setRole(role);
	dto.setWrittenAricles(articles);
	dto.setCorrectingArticles(articles);

        Long newid = userService.editUser(dto);
	assertEquals(id, newid);
	
	dto = userService.getUserById(newid);
	assertEquals(dto.getName(), newName);
	assertEquals(dto.getSurname(), newSurname);
	assertEquals(dto.getEmail(), newEmail);
	assertEquals(role, dto.getRole());
	
    }
    @Test
    public void testAddAndDeleteUser() {
	Long userId = addUser();
     
        List<UserDto> a = userService.getAllUsers();
        assertEquals(1, a.size());
	
        userService.deleteUser(userId);
	a = userService.getAllUsers();
	assertEquals(0, a.size());
    }
    
    @Test
    public void testCorrectorsGroupsUser() {
	Long role= addRole();
	Long cor1 = addCorrectorsGroup();
	Long cor2 = addCorrectorsGroup();
	Long cor3 = addCorrectorsGroup();
        Long cor4 = addCorrectorsGroup();
	List<Long> cors1 = new ArrayList();
	cors1.add(cor1);
	cors1.add(cor2);
	List<Long> cors2 = new ArrayList();
	cors2.add(cor2);
	cors2.add(cor3);
	List<Long> cors3 = new ArrayList();
	cors3.add(cor2);
	cors3.add(cor3);
	Long user1 = addUser(role, cors1);
	Long user2 = addUser(role, cors2);
	Long user3 = addUser(role, cors3);
	
        List<UserDto> c1user = userService.getCorrectorsGroupsUser(cor1);
	assertEquals(1, c1user.size());
        
        List<UserDto> c2user = userService.getCorrectorsGroupsUser(cor2);
	assertEquals(3, c2user.size());
        
        List<UserDto> c3user = userService.getCorrectorsGroupsUser(cor3);
        assertEquals(2, c3user.size());
        
        List<UserDto> c4user = userService.getCorrectorsGroupsUser(cor4);
	assertEquals(0, c4user.size());
    }
    
    
    
    private Long addUser() {
	return addUser(addRole());
    }
    
    private Long addUser(Long role) {
	return addUser(role, new ArrayList<Long>());
    }
    
    private Long addUser(Long role, List<Long> g) {
	String name = "name";
	String surname = "surname";
        String passwd = "passwd";
        String email = "email" + System.currentTimeMillis();
        return userService.addUser(name, surname, email, passwd, role, "212", g);
    }
    
    private Long addCorrectorsGroup() {
	String name = "Name" + System.currentTimeMillis();
	return correctorsGroupService.addCorrectorsGroup(name);
    }
    
    private Long addRole() {
	String name = "Name" + System.currentTimeMillis();
	return roleService.addRole(name);
    }
    
    private Long addArticle(Long userId) {
	String name = "Name" + System.currentTimeMillis();
	return articleService.addArticle(name, new Date(), "TEST", stateService.addState("test"), userId, userId, null);
    }
}
