package com.example.services.binders;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalBoundService extends Service {
	public static final String TAG = "LocalBoundService";
	private IBinder localServiceBinder = new LocalServiceBinder();
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return localServiceBinder;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}
	public void stopLocalBoundService(){
		Log.d(TAG, "stopLocalBoundService");
		stopSelf();
	}
	public class LocalServiceBinder extends Binder{
		LocalBoundService getService(){
			return LocalBoundService.this;
		}
	}

}
