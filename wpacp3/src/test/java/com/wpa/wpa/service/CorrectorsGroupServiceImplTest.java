/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.CorrectorsGroupDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mejty
 */
public class CorrectorsGroupServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CorrectorsGroupService instance;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StateService stateService;
    @Autowired
    private RoleService roleService;

    @Test
    public void testAddAndReviveCorrectorsGroup() {
        String name = "Histori";

        Long result = instance.addCorrectorsGroup(name);
        CorrectorsGroupDto expResult = instance.getCorrectorsGroupById(result);
        assertEquals(name, expResult.getName());
	List<CorrectorsGroupDto> correctors = instance.getAllCorrectorsGroup();
	assertEquals(1, correctors.size());
	assertEquals(result, correctors.get(0).getId());
    }
    
    @Test
    public void testGetArticlesCorrectorsGroup() {
        String name = "Correctors";
	Long user = userService.addUser("a", "b", "abc", "a", addRole());
        Long result = instance.addCorrectorsGroup(name);
        Long result2 = instance.addCorrectorsGroup(name+"2");
	instance.addCorrectorsGroup(name+"3");
	List<Long> cor = new ArrayList<Long>();
	cor.add(result);
	cor.add(result2);
	Long article = articleService.addArticle("test", new Date(), "file", stateService.addState("test"), user, user, cor);
	List<CorrectorsGroupDto> artCorGroup = instance.getArticlesCorrectorsGroup(article);
	assertEquals(2, artCorGroup.size());
    }
    
    @Test
    public void testGetUsersCorrectorsGroup() {
        
        instance.addCorrectorsGroup("abc");
        List<Long> groups= new ArrayList<Long>();
        groups.add(instance.addCorrectorsGroup("abc"));
        groups.add(instance.addCorrectorsGroup("abc2"));
        Long userId=userService.addUser("a", "b", "abc", "a", addRole(), "605", groups);
        List<CorrectorsGroupDto> result = instance.getUsersCorrectorsGroup(userId);
        assertEquals(groups.size(), result.size());
    }
    
    @Test
    public void testAddAndDeleteCorrectorsGroup() {
        int count=instance.getAllCorrectorsGroup().size();
        Long id=instance.addCorrectorsGroup("abc");
        List<CorrectorsGroupDto> groups;
        groups= instance.getAllCorrectorsGroup();
        assertEquals(count+1, groups.size());
        instance.deleteGroup(id);
        groups= instance.getAllCorrectorsGroup();
        assertEquals(count, groups.size());
    }
    
    private Long addRole() {
	String name = "Name" + System.currentTimeMillis();
	return roleService.addRole(name);
    }
}
