package com.avancial.app.resources.utils;

import java.util.TimeZone;

public class TimeZoneOffSet {

   public static String getGMTDiff() {
      TimeZone tz = TimeZone.getDefault();
      int offset = tz.getRawOffset();
        
      String text = String.format("%s%02d%02d", offset >= 0 ? "+" : "-", offset / 3600000, (offset / 60000) % 60);
        
      return text ;
   }
}
