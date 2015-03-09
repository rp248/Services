package com.example.services.binders;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalBoundService extends Service {
	public static final String TAG = "LocalBoundService";
	private IBinder localServiceBinder = new LocalServiceBinder();
	/*
	 * Called this method whenever other component starts the service using
	 * bindService().
	 * If the this is already bind to a component and that component again tries to bind to 
	 * this service,in that case onBind() will not be called, only onServiceConnected() callback
	 * method will be called.
	 * Test_1:
	 *      1>First time a component tries to bind with this service
	 *        onBind() will be called and also onServiceConnected() will be called
	 *      2>Again the same component tries to bind with this service,which is already bind 
	 *        with this service,then only onServiceConnected() will be called.
	 */
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
