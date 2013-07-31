package br.puc.rio.jpedro.monetize.persistence;

import android.content.Context;
import br.puc.rio.jpedro.monetize.model.Establishment;
import com.db4o.ObjectSet;
import java.util.List;

public class EstablishmentService
{
    public static void saveEstablishment ( Establishment establishment, Context context )
    {
        DBManager dbManager = new DBManager(context);
        dbManager.db().store(establishment);
    }

    public static Establishment getEstablishment ( Establishment establishment, Context context )
    {
        DBManager dbManager = new DBManager(context);
        ObjectSet result = dbManager.db().queryByExample(establishment);
        Establishment establishmentFound = (Establishment) result.next();
        return establishmentFound;
    }

    public static List<Establishment> getAllEstablishments ( Context context )
    {
        DBManager dbManager = new DBManager(context);
        List<Establishment> establishments = dbManager.db().query(Establishment.class);
        return establishments;
    }
}