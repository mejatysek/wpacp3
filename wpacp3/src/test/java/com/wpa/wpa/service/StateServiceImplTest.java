/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.StateDto;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mejty
 */
public class StateServiceImplTest extends AbstractServiceTest{
    
    @Autowired
    private StateService instance;
    @Test
    public void testAddAndRetrieveState() {
        String name = "Hotovo";
        Long result = instance.addState(name);
        StateDto expResult = instance.getStateById(result);
        assertEquals(name, expResult.getName());
	List<StateDto> results = instance.getAllStates();
	assertEquals(1, results.size());
    }
}
