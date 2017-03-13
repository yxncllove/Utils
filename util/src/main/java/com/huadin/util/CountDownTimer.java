package com.huadin.util;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;


/**
 * SMS verification countdown
 */

public abstract class CountDownTimer
{

  /**
   * Millis since epoch when alarm should stop.
   * Total execution time
   */
  private final long mMillisInFuture;

  /**
   * The interval in millis that the user receives callbacks
   * time interval
   */
  private final long mCountdownInterval;

  /**
   * Stop time
   */
  private long mStopTimeInFuture;

  /**
   * @param millisInFuture    The number of millis in the future from the call
   *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
   *                          is called.
   * @param countDownInterval The interval along the way to receive
   *                          {@link #onTick(long)} callbacks.
   */
  public CountDownTimer(long millisInFuture, long countDownInterval)
  {
    mMillisInFuture = millisInFuture;
    mCountdownInterval = countDownInterval;
  }

  /**
   * Cancel the countdown.
   */
  public final void cancel()
  {
    mHandler.removeMessages(MSG);
  }

  /**
   * Start the countdown.
   */
  public synchronized final CountDownTimer start()
  {
    if (mMillisInFuture <= 0)
    {
      onFinish();
      return this;
    }
    // stop time = System start-up time + Total time
    mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
    mHandler.sendMessage(mHandler.obtainMessage(MSG));
    return this;
  }


  /**
   * Callback fired on regular interval.
   *
   * @param millisUntilFinished The amount of time until finished.
   */
  protected abstract void onTick(long millisUntilFinished);

  /**
   * Callback fired when the time is up.
   */
  protected abstract void onFinish();


  private static final int MSG = 1;

  private Handler mHandler = new Handler()
  {

    @Override
    public void handleMessage(Message msg)
    {

      synchronized (CountDownTimer.this)
      {
        final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

        if (millisLeft <= 0)
        {
          onFinish();
        } else if (millisLeft < mCountdownInterval)
        {
          // no tick, just delay until done
          sendMessageDelayed(obtainMessage(MSG), millisLeft);
        } else
        {
          long lastTickStart = SystemClock.elapsedRealtime();
          onTick(millisLeft);

          // take into account user's onTick taking time to execute
          long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

          // special case: user's onTick took more than interval to
          // complete, skip to next interval
          while (delay < 0) delay += mCountdownInterval;

          sendMessageDelayed(obtainMessage(MSG), delay);
        }
      }
    }
  };
}
