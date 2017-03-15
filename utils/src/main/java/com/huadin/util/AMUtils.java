package com.huadin.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AMUtils
{
  /*Phone number regular expression*/
  private static final String MOBILE_PHONE_PATTERN = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[7])|(17[0678]))\\d{8}$";
  private static final String FIXED_TELEPHONE = "[0-9]{7,12}";
  /*Letters plus numbers*/
//  private static final String USER_NAME = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{6,20}";
  private static final String USER_NAME = "[A-Za-z0-9_\\-]{6,20}";

  private static final String STRING_FILTER = "[^A-Za-z0-9_\\-]";

  /**
   * Check the phone number is in line with the format
   *
   * @param phone phone
   * @return yes - true
   */
  public static boolean isMobile(String phone)
  {
    Pattern patter = Pattern.compile(MOBILE_PHONE_PATTERN);
    Matcher m = patter.matcher(phone);
    return m.matches();
  }

  /**
   * Check for landline number
   *
   * @param telephone Landline
   * @return Landline- true
   */
  public static boolean isTelephone(String telephone)
  {
    Pattern p = Pattern.compile(FIXED_TELEPHONE);
    Matcher m = p.matcher(telephone);
    return m.matches();
  }

  /**
   * Hide soft keyboard
   *
   * @param context context
   * @param e       editText
   */
  public static void onInactive(@NonNull Context context, EditText e)
  {
    if (e == null) return;

    InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    manager.hideSoftInputFromWindow(e.getWindowToken(), 0);
  }

  /**
   * Show soft keyboard
   *
   * @param context context
   * @param et      editText
   */
  public static void onActive(@NonNull Context context, EditText et)
  {
    if (et == null) return;

    InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    manager.showSoftInput(et, 0);
  }

  /**
   * Whether it is empty
   *
   * @param str String
   * @return boolean
   */
  public static boolean isEmpty(String str)
  {
    return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("");
  }

  public static boolean validatePassword(String str)
  {
    return str == null || str.length() < 6 || str.length() > 20;
  }

  public static boolean isUserName(String loginName)
  {
    Pattern patter = Pattern.compile(USER_NAME);
    Matcher m = patter.matcher(loginName);
    return m.matches();
  }

  /**
   * Filtering Chinese characters
   *
   * @param s input String
   * @return "" Enter the Chinese character filter ""
   */
  public static String stringFilter(String s)
  {
    Pattern pattern = Pattern.compile(STRING_FILTER);
    Matcher m = pattern.matcher(s);
    return m.replaceAll("").trim();
  }

  /**
   * android deviceId
   *
   * @param context Context
   * @return String
   */
  public static String getDeviceId(@NonNull Context context)
  {
    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    return tm.getDeviceId();
  }

}
