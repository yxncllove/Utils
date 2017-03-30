package com.huadin.sample;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.huadin.permission.PermissionListener;
import com.huadin.permission.PermissionManager;

import static com.huadin.util.NotNullUtils.checkNotNull;

public class MainActivity extends AppCompatActivity implements PermissionListener
{
  private PermissionManager mManager;
  private String[] mPermission = {Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.READ_PHONE_STATE};
  private static final String TAG = "MainActivity";
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    checkPermission();
  }



  private void checkPermission()
  {
    mManager = PermissionManager.with(this)
            .addRequestCode(99)
            .permissions(mPermission)
            .setPermissionListener(this)
            .request();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
  {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode)
    {
      case 99:
        mManager.onPermissionResult(permissions,grantResults);
        break;
    }
  }

  @Override
  public void onGranted()
  {
    Log.i(TAG, "onGranted: 授权成功");
  }

  @Override
  public void onShowRationale(String permissions)
  {
    Log.e(TAG, "onShowRationale: 授权失败");
  }

  String mMsg;
  public void test(String msg)
  {
    mMsg = checkNotNull(msg,"msg cannot be null");
  }
}
