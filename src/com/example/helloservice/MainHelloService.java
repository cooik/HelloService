package com.example.helloservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.helloservice.R;

public class MainHelloService extends Activity {

	String tag = "MainHelloService";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_hello_service);
		Toast.makeText(MainHelloService.this, "MusicHelloService onCreate",
				Toast.LENGTH_SHORT).show();
		Log.i(tag, "MusicHelloService onCreate");

		Button b1 = (Button) findViewById(R.id.start);
		Button b2 = (Button) findViewById(R.id.stop);
		Button b3 = (Button) findViewById(R.id.bind);
		Button b4 = (Button) findViewById(R.id.unbind);
		// 定义服务链接对象
		final ServiceConnection conn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Toast.makeText(MainHelloService.this,
						"ServiceConnection onServiceConnected",
						Toast.LENGTH_SHORT).show();
				Log.i(tag, "ServiceConnection onServiceConnected");
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Toast.makeText(MainHelloService.this,
						"ServiceConnection onServiceDisconnected",
						Toast.LENGTH_SHORT).show();
				Log.i(tag, "ServiceConnection onServiceDisconnected");
			}
		};

		OnClickListener ocl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 显示指定intent所指的对象是个Service
				Intent intent = new Intent(MainHelloService.this,
						MusicService.class);
				switch (v.getId()) {
				case R.id.start:
					Log.i(tag, "start");
					startService(intent);
					break;
				case R.id.stop:
					Log.i(tag, "stop");
					stopService(intent);
					break;
				case R.id.bind:
					Log.i(tag, "bind");
					bindService(intent, conn, Context.BIND_AUTO_CREATE);
					break;
				case R.id.unbind:
					Log.i(tag, "unbind");
					unbindService(conn);
					break;
				default:
					break;
				}
			}
		};
		b1.setOnClickListener(ocl);
		b2.setOnClickListener(ocl);
		b3.setOnClickListener(ocl);
		b4.setOnClickListener(ocl);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(MainHelloService.this, "MainHelloService onDestroy",
				Toast.LENGTH_SHORT).show();
		Log.i(tag, "MainHelloService onDestroy");
	}
}
