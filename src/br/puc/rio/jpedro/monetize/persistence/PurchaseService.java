package br.puc.rio.jpedro.monetize.persistence;

import android.content.Context;
import br.puc.rio.jpedro.monetize.model.Purchase;
import com.db4o.ObjectSet;
import java.util.List;

public class PurchaseService
{
    public static void savePurchase ( Purchase purchase, Context context )
    {
        DBManager dbManager = new DBManager(context);
        dbManager.db().store(purchase);
    }

    public static Purchase getPurchase ( Purchase purchase, Context context )
    {
        DBManager dbManager = new DBManager(context);
        ObjectSet result = dbManager.db().queryByExample(purchase);
        Purchase purchaseFound = (Purchase) result.next();
        return purchaseFound;
    }

    public static List<Purchase> getAllPurchases ( Context context )
    {
        DBManager dbManager = new DBManager(context);
        List<Purchase> purchases = dbManager.db().query(Purchase.class);
        return purchases;
    }
}