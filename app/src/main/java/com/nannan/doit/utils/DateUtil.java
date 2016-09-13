package com.nannan.doit.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author ljnjiannan
 * @since 16/9/4.
 */

public class DateUtil {

  public static long getCurrentTime(){
    Calendar c = new GregorianCalendar();
    return c.getTimeInMillis();
  }

}
