/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.StateDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Skalda
 */
@Transactional
public interface StateService {
    
    @Transactional(readOnly=true)
    public List<StateDto> getAllStates();
    
    @Transactional(readOnly=true)
    public StateDto getStateById(Long stateId);
    
    public Long addState(String name);
}
