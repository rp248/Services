package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartRedeliveryIntentConstantTest extends Service {
	public static final String TAG = "StartRedeliveryIntentConstantTest";
	/*
	 * If we return START_REDELIVER_INTENT,android system passes 
	 * the same intent again to this method,which started this service.
	 * Android system will pass START_FLAG_REDELIVERY as flag.
	 * Simply if we return START_REDELIVER_INTENT, on auto-restart android
	 * system will pass START_FLAG_REDELIVERY.
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, ""+intent+":"+flags+":"+startId);
		return START_REDELIVER_INTENT;
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
