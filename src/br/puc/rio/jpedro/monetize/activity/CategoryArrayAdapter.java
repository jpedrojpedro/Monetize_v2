package br.puc.rio.jpedro.monetize.activity;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.puc.rio.jpedro.monetize.model.Category;

public class CategoryArrayAdapter extends ArrayAdapter
{
	private final Context context;
	private final List<Category> categories;
	
	public CategoryArrayAdapter ( Context context, List categories )
	{
		super ( context, R.layout.category_row, categories);
		this.context = context;
		this.categories = categories;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = 	(LayoutInflater) context.
									getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.category_row, parent, false);
		
		Category c = categories.get(position);

		TextView tvName = (TextView) rowView.findViewById(R.id.categoryName);

		tvName.setText(c.getName().toString());
		
		return rowView;
	}
}