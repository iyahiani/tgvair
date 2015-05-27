/**
 * 
 */
package com.avancial.socle.model.managedbean;

import com.avancial.socle.exceptions.ASocleException;

/**
 * @author bruno.legloahec
 *
 */
public interface IManagedBean {

   public String add() throws ASocleException;

   public String update() throws ASocleException;

   public String delete() throws ASocleException;

}
