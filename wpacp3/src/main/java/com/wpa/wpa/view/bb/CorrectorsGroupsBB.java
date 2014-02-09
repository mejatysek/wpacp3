package com.wpa.wpa.view.bb;

import com.wpa.wpa.dto.CorrectorsGroupDto;
import com.wpa.wpa.service.CorrectorsGroupService;
import com.wpa.wpa.view.helper.FacesUtil;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
/**
 *
 * @author Skalda
 */
@Component
@Scope(value="request")
public class CorrectorsGroupsBB {
    
    private String name;
    
    @Autowired
    protected CorrectorsGroupService groupService;
    
    @PreAuthorize("hasRole('ROLE_admin')")
    public void storeGroup() {
        groupService.addCorrectorsGroup(getName());
        FacesUtil.addMessage("Correctors group was sucessfully added");
    
    }
    
    @PreAuthorize("hasRole('ROLE_admin')")
    public void deleteGroup(Long id) {
        groupService.deleteGroup(id);
        FacesUtil.addMessage("Correctors group was sucessfully deleted");
    }
    
    
    public List<CorrectorsGroupDto> getAllCorrectors() {
        return groupService.getAllCorrectorsGroup();
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
    