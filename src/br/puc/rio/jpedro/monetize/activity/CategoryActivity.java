package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import br.puc.rio.jpedro.monetize.model.Category;
import java.util.ArrayList;

public class CategoryActivity extends Activity
{
    private ListView showCategories;
    private ArrayList<Category> categories;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        this.showCategories = (ListView) findViewById(R.id.listViewCategories);
        // TODO :: Get categories from INI file
        this.categories = LoadCategories.getInstance().loadCategory(getBaseContext());
        this.populateListView(this.categories);
    }

    private void populateListView(ArrayList<Category> categories)
    {
        if ( categories != null )
        {
            CategoryArrayAdapter categoryAdapter = new CategoryArrayAdapter(this, categories);
            this.showCategories.setAdapter(categoryAdapter);
        }
    }
}