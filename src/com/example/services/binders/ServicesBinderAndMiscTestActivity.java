package com.example.services.binders;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.services.R;
import com.example.services.binders.LocalBoundService.LocalServiceBinder;

public class ServicesBinderAndMiscTestActivity extends ActionBarActivity {
	public static final String TAG = "ServicesBinderAndMiscTestActivity";
    private ServiceConnection localBoundServiceConnection;
    private Intent boundServiceIntent;
    private Intent startServiceIntent;
    private LocalBoundService boundService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services_binder_and_misc_test);
	}
	/*
	*Calls onCreate(),onBind(),onServiceConnected().
	*/
    public void startBoundServiceUsingBindMethod(View view){
    	boundServiceIntent = new Intent(this,LocalBoundService.class);
    	boolean b = bindService(boundServiceIntent, localBoundServiceConnection=new LocalBoundServiceConnection(),Context.BIND_AUTO_CREATE);
    	Log.d(TAG,""+b);
    }
    /*
     * Stopping a bound service using binders public methods,i.e selfStop(),does 
     * not call onDestroy()(i think it does not stop a bound service).
     */
    public void stopBoundServiceUsingBindersPublicMethods(View view){
    	if(boundService!=null){
    		boundService.stopLocalBoundService();
    	}
    }
    public void stopBoundServiceUsingUnBindService(View view){
    	if(localBoundServiceConnection!=null)
    	unbindService(localBoundServiceConnection);
    }
    /*
     * We can't stop a bounded service using stopService().If we try to stop the bound service using 
     * stopService(), it does nothing.
     */
    public void stopBoundServiceUsingStopMethod(View view){
    	stopService(boundServiceIntent);
    }
    
    public void startServiceUsingStartMethod(View view){
    	startServiceIntent = new Intent(this, LocalBoundService.class);
    	startService(startServiceIntent);
    }
    public void stopServiceUsingStopMethod(View view){
    	stopService(startServiceIntent);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.services_binder_and_misc_test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	class LocalBoundServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
		    Log.d(TAG,"onServiceConnected");
		    boundService = ((LocalServiceBinder)service).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG,"onServiceDisconnected");
		}
		
	}
}
