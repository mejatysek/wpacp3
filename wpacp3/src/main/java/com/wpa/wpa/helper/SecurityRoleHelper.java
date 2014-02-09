/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.helper;

import com.wpa.wpa.service.UserService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Skalda
 */
@Component
public class SecurityRoleHelper {

    
    public final boolean hasRole(String role) {
        boolean hasRole = false;
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            if (isRolePresent(authorities, role)) {
                hasRole = true;
            }
        }
        return hasRole;
    }

    /**
     * Get info about currently logged in user
     *
     * @return UserDetails if found in the context, null otherwise
     */
    public UserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails;
    }
    
    /**
     * Check if a role is present in the authorities of current user
     *
     * @param authorities all authorities assigned to current user
     * @param role required authority
     * @return true if role is present in list of authorities assigned to
     * current user, false otherwise
     */
    private boolean isRolePresent(Collection<? extends GrantedAuthority> authorities, String role) {
        boolean isRolePresent = false;
        for (GrantedAuthority grantedAuthority : authorities) {
            isRolePresent = grantedAuthority.getAuthority().equals(role);
            if (isRolePresent) {
                break;
            }
        }
        return isRolePresent;
    }
}
