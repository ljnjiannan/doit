package com.nannan.doit.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nannan.doit.data.DIConstants;
import com.nannan.doit.view.MissionEditActivity;

/**
 * @author Johan
 * @since 16/9/16.
 */

public class IntentUtil {

  public static void openMissionEditActivity(Activity activity,long type){
    Intent intent=new Intent(activity, MissionEditActivity.class);
    Bundle bundle=new Bundle();
    bundle.putLong(DIConstants.IntentKey.INTENT_KEY_DEFAULT,type);
    intent.putExtras(bundle);
    activity.startActivity(intent);
  }

}
