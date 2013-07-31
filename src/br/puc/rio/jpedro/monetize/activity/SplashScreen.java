package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity implements Runnable
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Thread(this).start();
    }

    @Override
    public void run()
    {
        try
        {
            synchronized (this)
            {
                wait(5000);
            }
        }
        catch (InterruptedException e)
        {}
        finally
        {
            finish();
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);
        }
    }
}