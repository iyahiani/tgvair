package com.avancial.app.data.controller.dao;


/**
 * 
 * @author ismael.yahiani
 *  cette interface d�crit le contrat des DataBeans   
 */
public interface CrudDAO {

	public Object create(Object o ) ; 
	public void add(Object o ) ; 
	public void upDate (Object o)  ; 
	public void delete (Object o ) ;
 }
