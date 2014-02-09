/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.Article;
import com.wpa.wpa.bo.CorrectorsGroup;
import com.wpa.wpa.bo.User;
import com.wpa.wpa.dto.CorrectorsGroupDto;
import com.wpa.wpa.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class CorrectorsGroupServiceImpl extends AbstractDataAccessService implements CorrectorsGroupService{


    @Override
    public List<CorrectorsGroupDto> getAllCorrectorsGroup() {
	List<CorrectorsGroup> cors = genericDao.getAll(CorrectorsGroup.class);
	List<CorrectorsGroupDto> corDaos = new ArrayList<CorrectorsGroupDto>();
	for(CorrectorsGroup c:cors) {
	    corDaos.add(new CorrectorsGroupDto(c.getId(), c.getName(), DtoTransformerHelper.getIdentifiers(c.getCorrectors()), DtoTransformerHelper.getIdentifiers(c.getWaitingArticles())));
	}

	return corDaos;
    }
    
    @Override
    public CorrectorsGroupDto getCorrectorsGroupById(Long correctorsGroupId) {
	CorrectorsGroup c = genericDao.getById(correctorsGroupId, CorrectorsGroup.class);
	return new CorrectorsGroupDto(c.getId(), c.getName(), DtoTransformerHelper.getIdentifiers(c.getCorrectors()), DtoTransformerHelper.getIdentifiers(c.getWaitingArticles()));
    }

    @Override
    public List<CorrectorsGroupDto> getArticlesCorrectorsGroup(Long articleId) {
        Article ar = genericDao.getById(articleId, Article.class);
        List<CorrectorsGroup> l = ar.getRecomendedCorrectors();
        List<CorrectorsGroupDto> corDaos = new ArrayList<CorrectorsGroupDto>();
        if(l!= null) {
        for(CorrectorsGroup k : l){
            corDaos.add(new CorrectorsGroupDto(k.getId(), k.getName(), DtoTransformerHelper.getIdentifiers(k.getCorrectors()), DtoTransformerHelper.getIdentifiers(k.getWaitingArticles())));   
        }
        }
	return corDaos;
    }

    @Override
    public List<CorrectorsGroupDto> getUsersCorrectorsGroup(Long userId) {
	User us = genericDao.loadById(userId, User.class);
	List<CorrectorsGroup> cors = us.getCorrectorsGroups();
	List<CorrectorsGroupDto> corDaos = new ArrayList<CorrectorsGroupDto>();
	if(cors!=null){
	for(CorrectorsGroup c:cors) {
	    corDaos.add(new CorrectorsGroupDto(c.getId(), c.getName(), DtoTransformerHelper.getIdentifiers(c.getCorrectors()), DtoTransformerHelper.getIdentifiers(c.getWaitingArticles())));
	}}
	return corDaos;
    }

    @Override
    public Long addCorrectorsGroup(String name) {
	CorrectorsGroup c = new CorrectorsGroup();
	c.setName(name);
	return genericDao.saveOrUpdate(c).getId();
    }
    
    @Override
    public void deleteGroup(Long groupId) {
	genericDao.removeById(groupId, CorrectorsGroup.class);
    }

    @Override
    public Long editGroup(CorrectorsGroupDto dto) {
        CorrectorsGroup u = genericDao.getById(dto.getId(), CorrectorsGroup.class);
	u.setName(dto.getName());
	return genericDao.saveOrUpdate(u).getId();
    }
    
}
