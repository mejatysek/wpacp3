/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.webservices;

import com.wpa.wpa.dto.CorrectorsGroupDto;
import com.wpa.wpa.service.CorrectorsGroupService;
import com.wpa.wpa.service.CorrectorsGroupServiceImpl;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */

@Component
@Path("/article")
public class ArticleService {
    
    @Autowired
    protected CorrectorsGroupService groupService;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorrectorsGroupDto> getAllCorrectorsGroups() {
        return getGroupService().getAllCorrectorsGroup();
    }
    
    @GET
    @Path("/{param}/groups")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CorrectorsGroupDto> getCorrectorsGroups(@PathParam("param") Long id) {
        System.out.println("TEST" + id);
        System.out.println(getGroupService());
        List<CorrectorsGroupDto> s =getGroupService().getArticlesCorrectorsGroup(id);
        return s;
    }

    /**
     * @return the groupService
     */
    public CorrectorsGroupService getGroupService() {
        return groupService;
    }

    /**
     * @param groupService the groupService to set
     */
    public void setGroupService(CorrectorsGroupService groupService) {
        this.groupService = groupService;
    }
    
}
