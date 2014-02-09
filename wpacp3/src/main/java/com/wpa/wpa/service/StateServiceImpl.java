/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.State;
import com.wpa.wpa.dto.StateDto;
import com.wpa.wpa.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class StateServiceImpl extends AbstractDataAccessService implements StateService {

    @Override
    public List<StateDto> getAllStates() {
	List<State> states = genericDao.getAll(State.class);
	List<StateDto> stateDaos = new ArrayList<StateDto>();
	for(State r:states) {
	    stateDaos.add(new StateDto(r.getId(), r.getName(), DtoTransformerHelper.getIdentifiers(r.getArticles())));
	}

	return stateDaos;
    }
    
    @Override
    public StateDto getStateById(Long stateId) {
	State r = genericDao.getById(stateId, State.class);
	return new StateDto(r.getId(), r.getName(), DtoTransformerHelper.getIdentifiers(r.getArticles()));
    }

    @Override
    public Long addState(String name) {
	State s = new State();
	s.setName(name);
	return genericDao.saveOrUpdate(s).getId();
    }
    
}
