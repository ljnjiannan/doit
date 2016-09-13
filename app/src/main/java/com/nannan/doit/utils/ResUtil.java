package com.nannan.doit.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.nannan.doit.DIApplication;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class ResUtil {

  /**
   * 获取资源字符串
   *
   * @param resId 资源ID
   */
  public static String getString(int resId) {
    return resources().getString(resId);
  }

  /**
   * 获取字符串资源
   */
  public static String getStringFormat(int resId, Object... args) {
    return resources().getString(resId, args);
  }

  /**
   * 获取资源颜色
   *
   * @param resId 资源ID
   */
  public static int getColor(int resId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return resources().getColor(resId, DIApplication.getContext().getTheme());
    } else {
      return resources().getColor(resId);
    }
  }

  /**
   * 获取资源drawable对象
   *
   * @param resId 资源ID
   */
  public static Drawable getDrawable(int resId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return resources().getDrawable(resId, DIApplication.getContext().getTheme());
    } else {
      return resources().getDrawable(resId);
    }
  }

  private static Resources resources() {
    return DIApplication.getContext().getResources();
  }

  public static final int drawable(String name) {
    return identifier(name, "drawable");
  }

  private static final int identifier(String name, String type) {
    Resources resources = resources();
    String packageName = DIApplication.getContext().getPackageName();
    if (resources != null) {
      return resources.getIdentifier(name, type, packageName);
    }
    return resources.getIdentifier(name, type, packageName);
  }
}
