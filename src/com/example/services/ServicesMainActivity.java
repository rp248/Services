package com.example.services;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ServicesMainActivity extends ActionBarActivity {
   private Intent serviceCallbackTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services_main);
	}

	public void startServiceCallbacksTest(View view) {
        startService(serviceCallbackTest = new Intent(this,ServiceCallBackTest.class));
	}

	
}
