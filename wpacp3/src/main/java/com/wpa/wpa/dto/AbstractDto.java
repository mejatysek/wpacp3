/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpa.wpa.dto;

import java.io.Serializable;

/**
 *
 * @author Skalda
 */
public class AbstractDto implements Serializable{
    protected Long id;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }
    
}
