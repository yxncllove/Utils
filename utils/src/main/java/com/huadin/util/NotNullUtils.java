package com.huadin.util;

import android.support.annotation.Nullable;

public final class NotNullUtils
{

  public static <T> T checkNotNull(T reference)
  {
    if (reference == null)
    {
      throw new NullPointerException();
    }
    return reference;
  }

  public static <T> T checkNotNull(T reference, @Nullable Object errorMessage)
  {
    if (reference == null)
    {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }
}
