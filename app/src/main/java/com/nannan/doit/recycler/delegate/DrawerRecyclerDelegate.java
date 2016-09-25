package com.nannan.doit.recycler.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseItemDelegate;
import com.nannan.doit.base.BaseRecyclerViewHolder;
import com.nannan.doit.model.MissionCateModel;
import com.nannan.doit.utils.ResUtil;

/**
 * Created by Johan on 16/9/16.
 */

public class DrawerRecyclerDelegate extends BaseItemDelegate<MissionCateModel> {

  public DrawerRecyclerDelegate(Context context) {
    super(context, 0);
  }

  @Override
  public int getItemViewLayout() {
    return R.layout.item_drawer_list;
  }

  @Override
  public boolean isForViewType(@NonNull MissionCateModel item, int position) {
    return true;
  }

  @Override
  public void onBindViewHolder(@NonNull MissionCateModel item, int position, @NonNull BaseRecyclerViewHolder holder) {
    super.onBindViewHolder(item, position, holder);
    TextView title=holder.findView(R.id.tv_sliderlayout_item_title);
    title.setText(item.getTitle());
    if(item.isSelected()){
      title.setBackgroundColor(ResUtil.getColor(R.color.colorApp300));
      title.setTextColor(ResUtil.getColor(R.color.font_white));
    }else {
      title.setBackgroundColor(ResUtil.getColor(R.color.white));
      title.setTextColor(ResUtil.getColor(R.color.font_black));
    }
  }
}
