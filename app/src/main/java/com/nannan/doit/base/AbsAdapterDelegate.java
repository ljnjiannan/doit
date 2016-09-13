package com.nannan.doit.base;

import com.nannan.doit.lib.recyclerview.AdapterDelegate;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public abstract class AbsAdapterDelegate<T> implements AdapterDelegate<T> {

  /**
   * The item view type
   */
  protected int viewType;

  public AbsAdapterDelegate(int viewType) {
    this.viewType = viewType;
  }

  @Override
  public int getItemViewType() {
    return viewType;
  }
}
