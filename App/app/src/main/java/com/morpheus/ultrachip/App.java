package com.morpheus.ultrachip;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class App extends Application
{
    private static final String WIFI_STATE_CHANGE_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";

    @Override
    public void onCreate()
    {
        super.onCreate();
        registerForNetworkChangeEvents(this);
    }

    public static void registerForNetworkChangeEvents(Context context)
    {
        BroadcastNetwork broadcastNetwork = new BroadcastNetwork();
        context.registerReceiver(broadcastNetwork, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        context.registerReceiver(broadcastNetwork, new IntentFilter(WIFI_STATE_CHANGE_ACTION));
    }
}
