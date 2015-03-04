package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.IBinder;
import android.util.Log;
/*
 * Once the service is started, it is the application responsibility for 
 * stopping this service by calling stopService(Intent intent) or the 
 * service can stop by itself by calling stopSelf()
 */
public class StartStickyConstantTest extends Service {
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
		handleStart(startId);
		super.onStart(intent, startId);
	}
	/* This method is called every time the service is started by other 
	 * application components. This method will be called for every startService()
	 * or bindService() calls.Default super class implementation will return 
	 * START_STICKY or START_STICKY_COMPATIBILITY.if we return START_STICKY_COMPATIBILITY
	 * or START_STICKY, the android system will restart this service automatically
	 * (if the service is not stopped by selfStop() or stopService())without the need 
	 * of calling startService().By default every service runs in the same process 
	 * as the caller(activity or something) is running.
	 * If the user or android system kills that process, and the service is in 
	 * running or started state, then the START_STICKY or START_STICKY_COMPATIBILITY 
	 * will be taken into consideration and android system will restart this service 
	 * in a process, and onStartCommand() will called with a null intent.
	 * 
	 * Test:
	 * 1>statService(Intent)
	 * 2>onStartCommand() is called by android system with the following parameters.
	 *   Intent { cmp=com.example.services/.StartStickyConstantTest }:0:1
	 *   0= i think zero is passed to onstartCommand() whenever startService or
	 *      bindService is called or auto restarted by the android system with 
	 *      START_STICKY return type.
	 *   1= startId,A unique integer representing this specific request to start the service.
	 *      This startId is used in stopself(int) or stopSelfResult(int).
	 *      Test for stopSelf(int):
	 *      1>n no.of times started this service, for instance n=5;,then 5 times
	 *        this method is called with startId from 1 to 5.
	 *        if we try to stop the service for startId 3 using stopSelf(3),after 
	 *        another request to this service,that is startId = 4,the service won't stop.
	 *        I think android system maintains current startId only, so if we try to stop 
	 *        the service with stopself(3), the android system won't stop it,because it has starId value 4.
	 *        If we call stopSelf() android system doesn't verify the startId it simply stops the service.
	 *      
	 * 3>Application removed from recent apps.
	 * 4>Android system automatically calls this method with following parameters
	 *   null:0:3
	 * 
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, ""+intent+":"+flags+":"+startId);
		handleStart(startId);
		return START_STICKY;
	}
	
	/*
	 * use this model for backward compatibility
	 */
	private void handleStart(int startID){
		Thread thread = new Thread(new someUtility(startID));
		thread.start();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}
	class someUtility implements Runnable{
		private int startID;
		public someUtility(int startID){
			this.startID = startID;
		}
		@Override
		public void run() {
		   try{
			   Thread.sleep(10000);
			   Log.d(TAG,"stopSelf("+startID+") is going to be called");
			   stopSelf(startID);
		   }
		   catch(InterruptedException ie){
			   ie.printStackTrace();
		   }
		}
	}

}
