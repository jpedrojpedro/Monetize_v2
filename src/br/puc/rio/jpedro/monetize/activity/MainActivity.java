package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Button btnCategory;
    private Button btnListPurchase;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.btnCategory = (Button) findViewById(R.id.btn_category);
        this.btnCategory.setOnClickListener(this);
        this.btnListPurchase = (Button) findViewById(R.id.btn_list_purchase);
        this.btnListPurchase.setOnClickListener(this);

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

    @Override
    public void onClick(View view)
    {
        if ( view.findViewById(view.getId()).equals(this.btnCategory) )
        {
            Intent i = new Intent (MainActivity.this, CategoryActivity.class);
            startActivity(i);
        }
        else if ( view.findViewById(view.getId()).equals(this.btnListPurchase) )
        {
            Intent i = new Intent (MainActivity.this, ListPurchaseActivity.class);
            startActivity(i);
        }
    }
}