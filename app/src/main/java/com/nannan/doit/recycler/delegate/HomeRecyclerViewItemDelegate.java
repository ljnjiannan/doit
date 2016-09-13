package com.nannan.doit.recycler.delegate;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseItemDelegate;
import com.nannan.doit.base.BaseRecyclerViewHolder;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.utils.ResUtil;

/**
 * @author ljnjiannan
 * @since 16/9/11.
 */

public class HomeRecyclerViewItemDelegate extends BaseItemDelegate<MissionModel> {

  public HomeRecyclerViewItemDelegate(Context context) {
    super(context, 0);
  }

  @Override
  public int getItemViewLayout() {
    return R.layout.item_home_mission_list;
  }

  @Override
  public boolean isForViewType(@NonNull MissionModel item, int position) {
    return true;
  }

  @Override
  public void onBindViewHolder(@NonNull MissionModel item, int position,
      @NonNull BaseRecyclerViewHolder holder) {
    super.onBindViewHolder(item, position, holder);
    TextView title=holder.findView(R.id.tv_home_mission_list);
    title.setText(item.getTitle());
    if(item.isDone()){
      title.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
      title.setTextColor(ResUtil.getColor(R.color.font_gray));
    }else {
      title.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
      title.setTextColor(ResUtil.getColor(R.color.font_black));
    }

  }
}
