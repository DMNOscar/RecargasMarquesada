package com.morpheus.ultrachip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class BroadcastNetwork extends BroadcastReceiver
{
    public static final String NETWORK_AVAILABLE_ACTION = "com.morpheus.ultrachip.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
    }

    private boolean isConnectedToInternet(Context context)
    {
        try
        {
            if(context != null)
            {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
