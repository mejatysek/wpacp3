/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.dto.CorrectorsGroupDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Skalda
 */
@Transactional
public interface CorrectorsGroupService {
    
    public Long addCorrectorsGroup(String name);
    
    @Transactional(readOnly=true)
    public List<CorrectorsGroupDto> getAllCorrectorsGroup();
    
    @Transactional(readOnly=true)
    public CorrectorsGroupDto getCorrectorsGroupById(Long correctorsGroupId);
    
    @Transactional(readOnly=true)
    public List<CorrectorsGroupDto> getArticlesCorrectorsGroup(Long articleId);
    
    @Transactional(readOnly=true)
    public List<CorrectorsGroupDto> getUsersCorrectorsGroup(Long userId);
    
    public Long editGroup(CorrectorsGroupDto dto);
    
    public void deleteGroup(Long groupId);
}
