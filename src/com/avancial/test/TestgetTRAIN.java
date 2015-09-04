package com.avancial.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.quartz.SchedulerException;

import com.avancial.app.resources.utils.StringToDate;
import com.mysql.jdbc.Connection;

public class TestgetTRAIN {

   public static void main(String[] args) {
      
      Date d =new Date() ;
      Calendar c = Calendar.getInstance();
      d = c.getTime(); 
      System.out.println(d);
      System.out.println(Integer.valueOf(StringToDate.toFormatedString(d)));
   }

}
