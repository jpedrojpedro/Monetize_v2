package br.puc.rio.jpedro.monetize.activity;

import android.content.Context;
import br.puc.rio.jpedro.monetize.model.Category;
import org.ini4j.Ini;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LoadCategories
{
    private static LoadCategories instance = null;

    protected LoadCategories() {}

    public static LoadCategories getInstance ()
    {
        if ( instance == null )
            instance = new LoadCategories();
        return instance;
    }

    public ArrayList<Category> loadCategory ( Context context )
    {
        ArrayList<Category> listCategories = new ArrayList<Category>();

        try
        {
            InputStream inputStream = context.getResources().openRawResource(R.raw.categories);
            Ini iniFile = new Ini();
            iniFile.load(inputStream);
            Ini.Section category = iniFile.get("category");
            String categories = category.get("categories");
            String[] splitCategories = categories.split(",");
            for ( int i=0; i<splitCategories.length; i++ )
            {
                Category c = new Category();
                c.setName(splitCategories[i]);
                listCategories.add(c);
            }
        }
        catch ( IOException ex1 )
        {}
        finally
        {
            return listCategories;
        }
    }
}