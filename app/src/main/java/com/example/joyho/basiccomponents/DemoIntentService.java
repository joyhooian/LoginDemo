package com.example.joyho.basiccomponents;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoIntentService extends GTIntentService {

    public DemoIntentService(){
    }

    @Override
    public void onReceiveServicePid(Context context, int pid){
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.d(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
        String [] data = msg.getContent().split(" ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String strDate = formatter.format(curDate);
        AlarmDataManager alarmDataManager = new AlarmDataManager(DemoIntentService.this);
        AlarmData alarmData = new AlarmData(strDate, data[0], data[1]);
        alarmDataManager.addAlarmData(alarmData);
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
    }
}
