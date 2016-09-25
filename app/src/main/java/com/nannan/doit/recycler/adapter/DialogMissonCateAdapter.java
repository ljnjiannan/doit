package com.nannan.doit.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nannan.doit.R;
import com.nannan.doit.model.MissionCateModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Johan
 * @since 16/9/22.
 */

public class DialogMissonCateAdapter extends BaseAdapter {

  protected LayoutInflater mInflater;
  protected Context mContext;
  protected List<MissionCateModel> data;

  public DialogMissonCateAdapter(Context mContext,
      List<MissionCateModel> data) {
    mInflater = LayoutInflater.from(mContext);
    this.mContext = mContext;
    this.data = data;
  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public MissionCateModel getItem(int position) {
    return data.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (null == convertView) {
      convertView = mInflater.inflate(R.layout.item_dialog_mission_cate, null);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.mTittle.setText(data.get(position).getTitle());

    return convertView;
  }

  static class ViewHolder {
    //@Bind(R.id.img_item_offerwall_icon)
    //ImageView mIconImage;
    @Bind(R.id.tv_dialog_move_mission_titls)
    TextView mTittle;

    public ViewHolder(View convertView) {
      ButterKnife.bind(this,convertView);
    }

  }
}
