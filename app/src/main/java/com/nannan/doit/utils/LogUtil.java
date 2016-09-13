package com.nannan.doit.utils;

import android.util.Log;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class LogUtil {

  private static final String LOG_PREFIX = "4399_";
  private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
  private static final int MAX_LOG_TAG_LENGTH = 23;

  private static boolean debug = true;

  public static void openDebug() {
    debug = true;
  }

  public static String makeLogTag(String str) {
    if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
      return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
    }
    return LOG_PREFIX + str;
  }

  public static String makeLogTag(Class cls) {
    return makeLogTag(cls.getSimpleName());
  }

  public static void println(String message) {

    if (debug && isNotNull(message)) {
      System.out.println(message);
    }
  }

  public static void info(String tag, String message) {
    if (debug && isNotNull(message)) {
      Log.i(tag, message);
    }
  }

  public static void info(String tag, String message, Throwable cause) {
    if (debug && isNotNull(message)) {
      Log.i(tag, message, cause);
    }
  }

  public static void debug(String tag, String message) {
    if (debug && isNotNull(message)) {
      Log.d(tag, message);
    }
  }

  public static void debug(String tag, String message, Throwable cause) {
    if (debug && isNotNull(message)) {
      Log.d(tag, message, cause);
    }
  }

  public static void error(String tag, String message) {
    if (debug && isNotNull(message)) {
      Log.e(tag, message);
    }
  }

  public static void log(Class tag, String message) {
    if (debug && isNotNull(message)) {
      Log.d(tag.getSimpleName(), message);
    }
  }

  public static void error(String tag, String message, Throwable cause) {
    if (debug && isNotNull(message)) {
      Log.e(tag, message, cause);
    }
  }

  private static boolean isNotNull(String message) {
    if (null == message) {
      return false;
    } else {
      return true;
    }
  }
}
