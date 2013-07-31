package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import br.puc.rio.jpedro.monetize.model.Purchase;
import br.puc.rio.jpedro.monetize.persistence.PurchaseService;
import java.util.ArrayList;
import java.util.List;

public class ListPurchaseActivity extends Activity
{
    private ListView listPurchase;
    private ArrayList<Purchase> purchases;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase);
        this.listPurchase = (ListView) findViewById(R.id.list_view_purchase);
        // TODO :: Get purchases from Db4o
        List<Purchase> listPurchase = PurchaseService.getAllPurchases(getBaseContext());
        this.purchases = new ArrayList<Purchase>();
        for ( Purchase p : listPurchase )
            this.purchases.add(p);
        this.populateListView(this.purchases);
    }

    private void populateListView(ArrayList<Purchase> purchases)
    {
        if ( purchases != null )
        {
            PurchaseArrayAdapter purchaseAdapter = new PurchaseArrayAdapter(this, purchases);
            this.listPurchase.setAdapter(purchaseAdapter);
        }
    }
}