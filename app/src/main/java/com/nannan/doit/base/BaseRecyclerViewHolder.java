package com.nannan.doit.base;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
  private final SparseArray<View> mViews = new SparseArray<>();

  public BaseRecyclerViewHolder(View itemView) {
    super(itemView);
  }

  public SparseArray<View> getAllView() {
    return mViews;
  }

  public <T extends View> T findView(int resId) {
    View childView = mViews.get(resId);
    if (null == childView) {
      childView = itemView.findViewById(resId);
      mViews.put(resId, childView);
    }
    return (T) childView;
  }

  /**
   * 设置textview的内容
   */
  public BaseRecyclerViewHolder setText(int resId, String text) {
    TextView textView = findView(resId);
    textView.setText(text);
    return this;
  }

  /**
   * 设置图片资源
   */
  public BaseRecyclerViewHolder setImageResource(int resId, int drawableId) {
    ImageView imageView = findView(resId);
    imageView.setImageResource(drawableId);
    return this;
  }

  /**
   * 设置图片资源
   */
  public BaseRecyclerViewHolder setImageBitmap(int resId, Bitmap bitmap) {
    ImageView imageView = findView(resId);
    imageView.setImageBitmap(bitmap);
    return this;
  }
}
