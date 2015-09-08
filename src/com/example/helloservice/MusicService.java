package com.example.helloservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {

	// 为日志设置标签
	String tag = "MusicService";

	// 定义音乐播放器变量
	MediaPlayer mPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "MusicService onBind()", Toast.LENGTH_SHORT)
				.show();
		Log.i(tag, "MusicService onBind()");
		mPlayer.start();
		return null;
	}

	// 其他对象通过unbindService方法通知该Service时该方法会被调用
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "MusicService onUnbind()", Toast.LENGTH_SHORT)
				.show();
		Log.i(tag, "MusicService onUnbind()");
		mPlayer.stop();
		return false;
	}

	// 该服务不存在需要被创建时被调用，不管startService()还是bindService()都会在启动时调用该方法
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "MusicService onCreate()", Toast.LENGTH_SHORT)
				.show();
		mPlayer = MediaPlayer.create(getApplicationContext(),
				R.raw.zhangguorong);
		mPlayer.setLooping(true);
		Log.d(tag, "MusicService onCreate()");
	}

	// 用startService方法调用该服务时，在onCreate()方法调用之后，会调用改方法
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "MusicService onStart", Toast.LENGTH_LONG).show();
		Log.i(tag, "MusicService onStart()");
		mPlayer.start();
		return START_STICKY;
	}

	// 该服务被销毁时调用该方法
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "MusicService onDestroy", Toast.LENGTH_LONG)
				.show();
		mPlayer.stop();
		Log.i(tag, "MusicService onDestory()");
	}

}
