package com.huadin.permission;

public interface PermissionListener
{
  /**
   * 用户授权后调用
   */
  void onGranted();

//  /**
//   * 用户禁止后调用
//   */
//  void onDenied();

  /**
   * 是否显示阐述性说明
   * @param permissions 返回需要显示说明的权限数组
   */
  void onShowRationale(String permissions);

}
