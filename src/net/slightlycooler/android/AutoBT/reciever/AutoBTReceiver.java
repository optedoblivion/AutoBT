package net.slightlycooler.android.AutoBT.reciever;

import net.slightlycooler.android.AutoBT.listener.AutoBTListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AutoBTReceiver extends BroadcastReceiver{
	
	private static final String TAG = " AutoBTReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Caught intent.");
		TelephonyManager tm = 
(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		AutoBTListener abtListener = new AutoBTListener();
		abtListener.setContext(context);
		tm.listen(abtListener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	

}
