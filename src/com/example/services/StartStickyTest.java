package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/*
 * Once the service is started, it is the application responsibility for 
 * stopping this service by calling stopService(Intent intent) or the 
 * service can stop by itself by calling stopSelf()
 */
public class StartStickyTest extends Service {
     public static final String TAG = "ServiceCallBackTest";
	/* If this service is not started by the application, then android system
	 * will call this method.If this service is already started or it is existed
	 * in the memory, then android system will not call this method. 
	 * (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		handleStart();
		super.onStart(intent, startId);
	}
	/* This method is called every time the service is started by other 
	 * application components. This method will be called for every startService()
	 * or bindService() calls.Default super class implementation will return 
	 * START_STICKY or START_STICKY_COMPATIBILITY.if we return START_STICKY_COMPATIBILITY
	 * or START_STICKY, the android system will restart this service automatically
	 * without the need of calling startService().By default every service runs
	 * in the same process as the caller(activity or something) is running.
	 * If the user or android system kills that process, and the service is in 
	 * running or started state, then the START_STICKY or START_STICKY_COMPATIBILITY 
	 * will be taken into consideration and android system will restart this service 
	 * in a process, and onStartCommand() will called with a null intent.
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG,String.valueOf("Flags:"+flags+"-StartID:"+startId));
		handleStart();
		return START_STICKY;
	}
	
	/*
	 * use this model for backward compatability
	 */
	private void handleStart(){
		
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
