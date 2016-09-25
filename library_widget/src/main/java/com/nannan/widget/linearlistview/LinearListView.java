package com.nannan.widget.linearlistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * @author Johan
 * @since 16/9/22.
 */

public class LinearListView extends LinearLayout {

  /**
   * Invalid flag for the resources
   */
  private static final int INVALID = -1;
  /**
   * Layout inflater to create views
   */
  private final LayoutInflater layoutInflater;
  /**
   * Adapter that stores all row items
   */
  private BaseAdapter adapter;
  /**
   * Observer for the data changes
   */
  private DataSetObserver dataSetObserver;
  /**
   * It is used to separate items if it is set
   */
  private int dividerViewResourceId = INVALID;

  /**
   * Determines if the header and footer view should be added
   */
  private View headerView;
  private View footerView;

  /**
   * Special item click listener in order to allow to user to take an action
   */
  private OnItemClickListener itemClickListener;

  public LinearListView(Context context) {
    this(context, null);
  }

  public LinearListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    layoutInflater = LayoutInflater.from(getContext());
    setOrientation(VERTICAL);
  }

  public void setDividerView(int resourceId) {
    if (resourceId < 0) {
      throw new IllegalStateException("Resource Id cannot be negative");
    }
    dividerViewResourceId = resourceId;
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    this.itemClickListener = listener;
  }

  public void setHeaderView(View view) {
    headerView = view;
  }

  public void setHeaderView(int resourceId) {
    headerView = layoutInflater.inflate(resourceId, this, false);
  }

  public void setFooterView(View view) {
    footerView = view;
  }

  public void setFooterView(int resourceId) {
    footerView = layoutInflater.inflate(resourceId, this, false);
  }

  public void setAdapter(BaseAdapter adapter) {
    if (adapter == null) {
      throw new NullPointerException("Adapter may not be null");
    }
    if (this.adapter != null && this.dataSetObserver != null) {
      this.adapter.unregisterDataSetObserver(dataSetObserver);
    }
    this.adapter = adapter;
    this.dataSetObserver = new AdapterDataSetObserver();
    this.adapter.registerDataSetObserver(dataSetObserver);
    resetList();
    refreshList();
  }

  /**
   * It is called when the notifyDataSetChanged or first initialize
   */
  private void refreshList() {
    if (headerView != null) {
      addView(headerView);
    }
    int count = adapter.getCount();
    for (int i = 0; i < count; i++) {
      final View view = adapter.getView(i, null, this);
      final int position = i;
      view.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if (itemClickListener != null) {
            itemClickListener.onItemClick(adapter.getItem(position), view, position);
          }
        }
      });
      addView(view);
      if (dividerViewResourceId != INVALID && i != count - 1) {
        addView(layoutInflater.inflate(dividerViewResourceId, this, false));
      }
    }
    if (footerView != null) {
      addView(footerView);
    }
  }

  /**
   * Clears everything
   */
  private void resetList() {
    this.removeAllViews();
    invalidate();
  }

  public interface OnItemClickListener {

    void onItemClick(Object item, View view, int position);
  }

  /**
   * observe data set changes, when the adapter notifyDataSetChanged method called, onChanged
   * method will be called and view will be refreshed.
   */
  class AdapterDataSetObserver extends DataSetObserver {
    @Override
    public void onChanged() {
      super.onChanged();
      resetList();
      refreshList();
    }
  }
}