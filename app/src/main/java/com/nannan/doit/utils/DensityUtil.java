package com.nannan.doit.utils;

import android.content.res.Resources;

/**
 * @author ljnjiannan
 * @since 16/9/3.
 */

public class DensityUtil {

  public static int dpToPx(int dp) {
    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
  }
}
