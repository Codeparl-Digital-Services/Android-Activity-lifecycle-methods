package com.codeparl.stopwatch;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
private int seconds = 0;
private boolean running  = false;
private boolean wasRunning  = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState != null) {
			
		seconds =  savedInstanceState.getInt("seconds");
		running =  savedInstanceState.getBoolean("running");
		wasRunning =  savedInstanceState.getBoolean("wasRunning");
		}
		runTimer();
	}


	@Override
		protected void onResume() {
			super.onResume();
			if(wasRunning)
				 running =  true;
		}
	
	@Override
		protected void onPause() {
			super.onPause();
			wasRunning =  running;
			running =  false;
		}
	



	private void runTimer() {
		
		final Handler   handler = new  Handler();
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				int hours = seconds /3600  ;
				int second = (seconds % 3600) / 60;
				int minutes = (seconds % 3600);
				String time  =  String.format(
						Locale.getDefault(), 
						"%d:%02d:%02d", hours,minutes,second) ;
				TextView timerView =  (TextView) findViewById(R.id.displaytimer);
				timerView.setText(time);
				
				if(running) ++seconds;	
				handler.postDelayed(this, 1000);
			}
			
		});//end post

		
	}//end method 
	
	
	//this method allows us to save any variables 
	//that we can retrieve their values when the 
	//activity changes its state.
	@Override
	public  void onSaveInstanceState(Bundle  bundle) {
		bundle.putInt("second", this.seconds);
		bundle.putBoolean("running", this.running);
		bundle.putBoolean("wasRunning", this.wasRunning);
		
	}
	
	public void onClickStart(View view) {
		running = true;
		
	}

	public void onClickStop(View view) {
		running =  false;
	
	}

	public void onClickReset(View view) {
		running =  false;
		seconds = 0;
	}


}
