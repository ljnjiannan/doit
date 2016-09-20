package com.nannan.doit.recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.base.BaseRecyclerAdapter;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.recycler.delegate.SliderLayoutRecyclerDelegate;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class SliderLayoutRecyclerAdapter extends BaseRecyclerAdapter<MissionCateModel>{


  public SliderLayoutRecyclerAdapter(RecyclerView recyclerView, Context context) {
    super(recyclerView, context, null);
    delegatesManager.addDelegate(new SliderLayoutRecyclerDelegate(context));
  }


}
