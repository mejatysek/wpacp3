/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.service;

import com.wpa.wpa.bo.Role;
import com.wpa.wpa.dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Authentication provider pro Flexiblu 2
 * @author Pavel Micka
 */
//Configuration in applicationContext-security.xml
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);
    private GenericDao genericDAO;
    private TransactionTemplate transactionTemplate;

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * Krome specifikace z nadtridy prida v pripade uspesneho prihlaseni do sessionHolderu pod klic "user" daneho uzivatele
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) { 
                try {
                    UserDetails ud = null;
                    com.wpa.wpa.bo.User u = genericDAO.getByPropertyUnique("email", username, com.wpa.wpa.bo.User.class);
                    
                    String password = (String) upat.getCredentials();
                    
                    System.out.println("PASS " + password);
                    if (u == null || !u.hasPassword(password)) {
                        System.out.println("NE");
                        AuthenticationException e = new BadCredentialsException("Neplatne uzivatelske udaje");
                       // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, e);
                        throw e;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        auths.add(new GrantedAuthorityImpl("ROLE_USER"));
                        Role role = u.getRole();
                        auths.add(new GrantedAuthorityImpl("ROLE_" + role.getName()));
                        ud = new User(u.getEmail(), u.getPassword(), auths);
                    }
                    return ud;
                } catch(EmptyResultDataAccessException e) {
                        AuthenticationException eb = new BadCredentialsException("Uživatel neexistuje");
                       // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, e);
                        throw eb;
                } catch(AuthenticationException e){
                    status.setRollbackOnly();
                    throw e;
                }catch (Exception e) {
                    LOG.error("Error occured during retrieveUser call", e);
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    
}
