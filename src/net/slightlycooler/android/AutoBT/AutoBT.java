package net.slightlycooler.android.AutoBT;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.view.MotionEvent;

public class AutoBT extends Activity {

	private static final String TAG = "AutoBT";
	private ImageView toggleButton;
	private SharedPreferences prefs;
	private SharedPreferences.Editor settings;
	private boolean activated;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		settings = prefs.edit();
		activated = prefs.getBoolean("activated", false);
		toggleButton = (ImageView) findViewById(R.id.ImageView01);
		try{
			if(!activated){
				toggleButton.setImageResource(R.drawable.android_grey);
			}else{
				toggleButton.setImageResource(R.drawable.android_blue);
			}
			settings.putBoolean("activated", activated);
			settings.commit();
		}catch(Exception e){
			Log.e(TAG, e.toString());
		}

        Intent intent = new Intent("net.slightlycooler.android.AutoBT.DOIT");
        sendBroadcast(intent);

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	long action = event.getAction();
    	boolean ret = true;
    	if(action == MotionEvent.ACTION_DOWN){
    		ret = toggleAutoBT();	
    	}
    	return ret;
    }
    
    public boolean toggleAutoBT(){
    	try{
    		activated = prefs.getBoolean("activated", false);

	    	if (activated){
	    		Log.i(TAG, "Toggling to "+!activated);
	    		settings.putBoolean("activated", false);
	    		toggleButton.setImageResource(R.drawable.android_grey);
	    	} else {
	    		Log.i(TAG, "Toggling to "+!activated);
	    		settings.putBoolean("activated", true);
	    		toggleButton.setImageResource(R.drawable.android_blue);
	    	}
	    	settings.commit();
	    	return true;
    	}catch(Exception e){
    		Log.e(TAG, e.toString());
    		return false;
    	}
    }
    

}