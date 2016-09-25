package com.nannan.doit.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.base.BaseRecyclerAdapter;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.recycler.delegate.DrawerRecyclerDelegate;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class DrawerRecyclerAdapter extends BaseRecyclerAdapter<MissionCateModel>{


  public DrawerRecyclerAdapter(RecyclerView recyclerView, Context context) {
    super(recyclerView, context, null);
    delegatesManager.addDelegate(new DrawerRecyclerDelegate(context));
  }


}
