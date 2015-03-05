package com.example.services;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class ServicesMainActivity extends ActionBarActivity {
   private Intent startStickyConstantTest;
   private Intent startRedeliveryIntentConstantTest;
   private Intent startFlagRetryTest;
   Button button;
   TextView view;
   RadioButton button2;
   Activity a;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services_main);
	}
    
	public void startStickyConstantTest(View view) {
	      startService(startStickyConstantTest = new Intent(this,StartStickyConstantTest.class));
	}
	public void stopStartStickyConstantTest(View view){
		  stopService(startStickyConstantTest);
	}
	public void startRedeliveryIntentConstantTest(View view){
		startService(startRedeliveryIntentConstantTest = new Intent(this,StartRedeliveryIntentConstantTest.class));
	}
    public void startFlagRetryTest(View view){
//    	Intent intent = new Intent("com.example.services.START_FLAG_RETRY_TEST");
//    	PendingIntent pendingIntent = PendingIntent.getService(this,448,intent,PendingIntent.FLAG_ONE_SHOT);
//    	
//    	Intent otherApp = new Intent("com.example.pendingintents.HANDLE_OTHER_PENDING_INTENTS");
//    	otherApp.setComponent(new ComponentName("com.example.pendingintents","com.example.pendingintents.PendingIntentsActivity"));
//    	otherApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    	otherApp.putExtra("Services_START_FLAG_RETRY_PENDING_INTENT", pendingIntent);
//    	startActivity(otherApp);
    	startService(startFlagRetryTest = new Intent(this,StartFlagRetryTest.class));
    }

	
}
