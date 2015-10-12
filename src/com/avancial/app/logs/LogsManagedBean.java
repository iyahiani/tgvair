package com.avancial.app.logs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named("logs")
@ViewScoped
public class LogsManagedBean {
   
   private List<LogsDataBean> listLogs  ; 
   
   @PostConstruct
   public void init() {
      this.listLogs = new LogsDAO().getAll() ;
   }
   public List<LogsDataBean> getListLogs() {
      return this.listLogs;
   }
   public void setListLogs(List<LogsDataBean> listLogs) {
      this.listLogs = listLogs;
   }
}
