package br.puc.rio.jpedro.monetize.persistence;

import android.content.Context;
import br.puc.rio.jpedro.monetize.model.Bank;
import br.puc.rio.jpedro.monetize.model.Category;
import com.db4o.ObjectSet;
import java.util.List;

public class CategoryService
{
    public static void saveCategory ( Category category, Context context )
    {
        DBManager dbManager = new DBManager(context);
        dbManager.db().store(category);
    }

    public static Bank getCategory ( Category category, Context context )
    {
        DBManager dbManager = new DBManager(context);
        ObjectSet result = dbManager.db().queryByExample(category);
        Bank categoryFound = (Bank) result.next();
        return categoryFound;
    }

    public static List<Category> getAllCategories ( Context context )
    {
        DBManager dbManager = new DBManager(context);
        List<Category> categories = dbManager.db().query(Category.class);
        return categories;
    }
}