package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if ( !this.isMyServiceRunning() )
            startService( new Intent(MainActivity.this,
                    br.puc.rio.jpedro.monetize.service.MessagingService.class) );
    }

    private boolean isMyServiceRunning()
    {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if ("br.puc.rio.jpedro.monetize.service.MessagingService".equals(service.service.getClassName()))
                return true;
        }
        return false;
    }
}