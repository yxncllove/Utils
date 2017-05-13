package com.huadin.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.huadin.permission.PermissionListener;
import com.huadin.permission.PermissionManager;
import com.huadin.util.CountDownTimer;

import static com.huadin.util.NotNullUtils.checkNotNull;

public class MainActivity extends AppCompatActivity implements PermissionListener
{
  private PermissionManager mManager;
  private String[] mPermission = {Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.READ_PHONE_STATE};
  private static final String TAG = "MainActivity";
  CountDownTimer mTimer = new CountDownTimer(59000,1000)
  {
    @Override
    protected void onTick(long millisUntilFinished)
    {
      mTextView.setText(String.valueOf(millisUntilFinished/1000));
    }

    @Override
    protected void onFinish()
    {
      mTextView.setText("开始");
    }
  };

  TextView mTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    checkPermission();
    mTextView = (TextView) findViewById(R.id.textview);
    mTextView.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        mTimer.start();
      }
    });
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
