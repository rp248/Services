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
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
	}
	/*
	 * Called this method whenever other component starts the service using
	 * bindService().
	 * If the this is already bind to a component and that component again tries to bind to 
	 * this service,in that case onBind() will not be called, only onServiceConnected() callback
	 * method will be called.
	 * Test_1:
	 *      1>First time a component tries to bind with this service
	 *        onBind() will be called and also onServiceConnected() will be called
	 *      2>Again the same component tries to bind with this service using bindService(),
	 *        which is already bind with this service,then only onServiceConnected() will be called.
	 *  Test_2:
	 *      Killed Bound Service process using DDMS or app manager in device settings.
	 *      1>Bound Service is auto started(because we pass BIND_AUTO_CREATE constant to bindService())
	 *      2>onServiceDisconnected() called for calling component.
	 *      3>onCreate() and onBind() called.
	 *      4>onServiceConnected() called for calling component. 
	 *  
	 */
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return localServiceBinder;
	}
	/* 
	 * Called if a client component unbound this service and again bounds to it.
	 * This happens only when we return true in onBind(). 
	*/
	@Override
	public void onRebind(Intent intent) {
		Log.d(TAG, "onRebind");
		super.onRebind(intent);
	}
	/*
	* Called whenever a client calls unBindService() or the caller(another app) component process 
	* is killed by DDMS or android system or apps manager.
	* Test_1:
	*     1>start services app and start the service using bindService.
	*       kill the services process using DDMS, onUnbind will not be called.
	* Test_2:
	*     1>start services app and start the service using bindService.
	*     2>press the device back button(component goes to pause state), onUnbind() will be called.    
	* Test_3:
	*     1>start services app and start the service using bindService.
	*       onCreate(),onBind(),onServiceConnected()(caller's) is called.
	*     2>goto home screen by pressing home button
	*     3>start otherAPP and start the service using bindService.
	*       onBind(),onServiceConnected()(caller's) is called.
	*     4>press the device back button(component goes to pause state), onUnbind() will be called,but onServiceDisConnected()
	*       will not called.
	*       todo 
	*       1.again bring the app to foreground and call bindService
	*       2.again bring the app to foreground and call unBindService.//null pointer exception;
	*         Your application may contain non null intent object, but android framework does not 
	*         have a intent service object which you have passed to unBindService(),because that 
	*         service is already unbound and android framework removes that object from the app's
	*         context.  
	*       Note:onServiceDisConnected() will be called only the service hosting process is killed or stopped unexpectedly.
	* Test_4:
	*     1>start otherAPP and start the service using bindService.
	*       onCreate(),onBind(),onServiceConnected()(caller's) is called.
	*     2>Kill the bound service's process using DDMS.
	*       onServiceDisConnected() is called.
	*       onUnBind() will not be called because services process is stopped,all java objects associated with that process are GC
	*       Service is auto created because BIND_AUTO_CREATE constant is used in bindService() and onCreate(),onBind() is called.
	* Test_5:
	*     1>start otherAPP and start the service using bindService.
	*      onBind(),onServiceConnected()(caller's) is called.
	*     2>stop the service using unBindService().
	*       onUnbind(),onDestroy() is called.
	*       onServiceDisConnected() is not called( i think this method only called the when service process is stopped by the system). 
	*/    
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "onUnbind");
		return true;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}
	/*
	 * For Bound Services onDestroy() is called when there is no client components are bound to it.
	 * For Started Services onDestroy() is called when selfStop() or stopService() is called. 
	 */
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
