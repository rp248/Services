package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartFlagRetryTest extends Service {
	public static final String TAG = "StartFlagRetryTest";
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate()");
		super.onCreate();
	}
	/*
	*START_FLAG_RETRY(2) flag will be passed to onStartCommand() when the previous service start request hasn't 
	*come out of this method.
	*Test_1:
	*   1>start the service using startService()
	*   2>Android system will call the onStartCommand() with the following parameters.
	*     Intent { cmp=com.example.services/.StartFlagRetryTest }:0:1
	*     0==refer StartStickyConstantTest
	*   2>in onStartCommand(), we do some blocking operation.
	*   3>remove the app from the recent apps or stop the process from DDMS or using device setting app running option.
	*   4>Again open the app , service will be started automatically and blocks the ui thread.
	*     Android system will call the onStartCommand() with the following parameters.
	*     Intent { cmp=com.example.services/.StartFlagRetryTest }:2:1
	*     2= START_FLAG_RETRY
	*     After coming out from this method ui will be rendered.
	*     Stop the apps process using DDMS tool
	*     Android system will create the service and calls this method with the following parameters.
	*     null:0:2
	* Test_2:
	*   1>start this service in background by defining android:process=":background"
    */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, ""+intent+":"+flags+":"+startId);
		try{
			Thread.sleep(15000);
			Log.d(TAG, "sleep completed and going out");
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
     	return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	

}
