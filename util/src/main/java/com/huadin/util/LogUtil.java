package com.huadin.util;


import android.util.Log;

public class LogUtil
{
  private static boolean isTrue;

  public static void i(String tag, String msg)
  {
    if (!isTrue)
    {
      Log.i(tag, msg);
    }
  }

  public static void e(String tag, String msg)
  {
    if (!isTrue)
    {
      Log.e(tag, msg);
    }
  }

  public static void d(String tag, String msg)
  {
    if (!isTrue)
    {
      Log.d(tag, msg);
    }
  }

  public static void w(String tag, String msg)
  {
    if (!isTrue)
    {
      Log.w(tag, msg);
    }
  }
}
