package com.nannan.doit.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseActivity;
import com.nannan.doit.data.DIConstants;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.vp.ipresenter.IMissionEditPresenter;
import com.nannan.doit.vp.iview.IMissionEditView;
import com.nannan.doit.vp.presenter.MissionEditPresenter;

import butterknife.Bind;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class MissionEditActivity extends BaseActivity implements IMissionEditView{

  @Bind(R.id.et_mission_title) EditText etMissionTitle;

  private long type;
  private long missionId;
  private IMissionEditPresenter presenter;

  @Override
  protected void getExtra(Bundle extra) {
    type=extra.getLong(DIConstants.IntentKey.INTENT_KEY_DEFAULT,0);
    missionId=extra.getLong(DIConstants.IntentKey.INTENT_KEY_MISSION_ID,-1);
  }

  @Override
  protected void initRxEvent() {

  }

  @Override
  protected String setActivityTitle() {
    return missionId==-1?"添加任务":"更改任务";
  }

  @Override
  protected int setContentViewId() {
    return R.layout.activity_mission_edit;
  }

  @Override
  protected void initView() {

  }

  @Override
  protected void initData() {
    presenter=new MissionEditPresenter(this);
    if(missionId!=-1) {
      presenter.loadData(this, missionId);
    }
  }

  @Override
  protected boolean isBindRxBus() {
    return false;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_activity_mission_edit, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    String missionName=etMissionTitle.getText().toString();
    if (!missionName.isEmpty()) {
      presenter.saveData(this,missionName,type);
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onLoadDataSuccess(MissionModel model) {
    etMissionTitle.setText(model.getTitle());
  }

  @Override
  public void onSaveSuccess() {
    finish();
  }
}
