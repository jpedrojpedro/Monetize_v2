package br.puc.rio.jpedro.monetize.persistence;

import br.puc.rio.jpedro.monetize.model.Bank;
import android.content.Context;
import com.db4o.ObjectSet;
import java.util.List;

public class BankService
{
    public static void saveBank ( Bank bank, Context context )
    {
        DBManager dbManager = new DBManager(context);
        dbManager.db().store(bank);
    }

    public static Bank getBank ( Bank bank, Context context )
    {
        DBManager dbManager = new DBManager(context);
        ObjectSet result = dbManager.db().queryByExample(bank);
        Bank bankFound = (Bank) result.next();
        return bankFound;
    }

    public static List<Bank> getAllBanks ( Context context )
    {
        DBManager dbManager = new DBManager(context);
        List<Bank> banks = dbManager.db().query(Bank.class);
        return banks;
    }
}