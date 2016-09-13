package com.nannan.doit.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.base.BaseRecyclerAdapter;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.recycler.delegate.HomeRecyclerViewItemDelegate;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class HomeRecyclerViewAdapter extends BaseRecyclerAdapter<MissionModel> {


  public HomeRecyclerViewAdapter(RecyclerView recyclerView,
      Context context) {
    super(recyclerView, context, null);
    delegatesManager.addDelegate(new HomeRecyclerViewItemDelegate(context));
  }
}
