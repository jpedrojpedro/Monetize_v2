package br.puc.rio.jpedro.monetize.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import br.puc.rio.jpedro.monetize.receiver.IncomingMessageReceiver;

public class MessagingService extends Service
{
    // Variable TAG used for debugging reasons
    private static final String TAG = "MessagingService";
    private IntentFilter filter;
    // Responsible for Broadcast Receiver start
    private IncomingMessageReceiver receiver;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        Log.i(TAG, "Service was created");
        super.onCreate();
        this.receiver = new IncomingMessageReceiver();
        this.filter = new IntentFilter();
        this.filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(this.receiver, this.filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i(TAG, "Service is alive");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        Log.i(TAG, "Service was destroyed");
        super.onDestroy();
        unregisterReceiver(this.receiver);
    }
}