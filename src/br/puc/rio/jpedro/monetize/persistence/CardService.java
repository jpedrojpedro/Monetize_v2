package br.puc.rio.jpedro.monetize.persistence;

import android.content.Context;
import br.puc.rio.jpedro.monetize.model.Card;
import com.db4o.ObjectSet;

public class CardService
{
    public static void saveCard ( Card card, Context context )
    {
        DBManager dbManager = new DBManager(context);
        dbManager.db().store(card);
    }

    public static Card getCard ( Card card, Context context )
    {
        DBManager dbManager = new DBManager(context);
        ObjectSet result = dbManager.db().queryByExample(card);
        Card cardFound = (Card) result.next();
        return cardFound;
    }
}