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
    */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, ""+intent+":"+flags+":"+startId);
		
			try{
				Thread.sleep(18000);
			}
			catch(InterruptedException ie){
				ie.printStackTrace();
			}
	
		Log.d(TAG, "For loop completed");
     	return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	

}
