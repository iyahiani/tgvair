package com.avancial.app.resources.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeZoneOffSet {

   public static String getGMTDiff() {
      TimeZone tz = TimeZone.getDefault();  
      Calendar cal = GregorianCalendar.getInstance(tz);
      int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

      String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
     
      offset = (offsetInMillis >= 0 ? "+" : "-") + offset;
     String temp = offset.substring(0, 3).concat(offset.substring(4, 6)) ;
        return temp;
     
   }
}
