package net.slightlycooler.android.AutoBT.listener;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AutoBTListener extends PhoneStateListener{
	
	private static final String TAG = "AutoBTListener";
    private BluetoothAdapter btAdapter;
	private SharedPreferences prefs;
	private boolean active;
	private Context mContext;
	
	public void setContext(Context context){
		mContext = context;
	}
	
    @Override
	public void onCallStateChanged(int state, String incomingNumber){
		super.onCallStateChanged(state, incomingNumber);
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		active = prefs.getBoolean("activated", false);
		if (active){
			switch(state){
				case TelephonyManager.CALL_STATE_RINGING:
					Log.i(TAG, "Phone is ringing.");
					if(!btAdapter.isEnabled()){
						btAdapter.enable();
					}
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					Log.i(TAG, "Phone call ended.");
					if(btAdapter.isEnabled()){
						btAdapter.disable();
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Log.i(TAG, "Placing phone call.");
					if(!btAdapter.isEnabled()){
						btAdapter.enable();
					}
					break;
				default:
					break;
			}
		}
	}
}
