package com.avancial.app.model.managedbean;

import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.DefaultScheduleModel;

public class CustomerSchedule implements Serializable {

	private static final long serialVersionUID = 1L;
	private DefaultScheduleModel schedule;
	private Date initialDate;
	private String name;
	
	public CustomerSchedule(Date initialDate, String name) throws Exception {
		super();
		this.schedule = new DefaultScheduleModel();	
		this.initialDate = initialDate;
		this.name = name;
	}	
	
	public DefaultScheduleModel getSchedule() {
		return this.schedule;
	}
	
	public void setSchedule(DefaultScheduleModel schedule) {
		this.schedule = schedule;
	}
	public Date getInitialDate() {
		return this.initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
