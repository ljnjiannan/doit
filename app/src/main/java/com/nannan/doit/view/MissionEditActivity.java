package com.nannan.doit.view;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseActivity;
import com.nannan.doit.data.database.DBFactory;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.utils.DateUtil;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ljnjiannan
 * @since 16/9/12.
 */

public class MissionEditActivity extends BaseActivity {

  @Bind(R.id.et_mission_title) EditText etMissionTitle;

  @Override
  protected String setActivityTitle() {
    return null;
  }

  @Override
  protected int setCOntentViewId() {
    return R.layout.activity_mission_edit;
  }

  @Override
  protected void initView() {

  }

  @Override
  protected void initData() {

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
      MissionModel model = new MissionModel();
      model.setTitle(missionName);
      model.setCateId(1);
      model.setAddTime(DateUtil.getCurrentTime());
      DBFactory.getMissionModelDao(this).rx()
          .insert(model)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(missionModel -> {
            this.finish();
          }, throwable -> {
          });

    }
    return super.onOptionsItemSelected(item);
  }

}
