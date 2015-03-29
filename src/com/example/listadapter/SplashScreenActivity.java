package com.example.listadapter;

import com.example.telkomsel.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SplashScreenActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGTH = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("RADITYA GUMAY", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
	
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
				SplashScreenActivity.this.startActivity(intent);
				SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
	}
}
