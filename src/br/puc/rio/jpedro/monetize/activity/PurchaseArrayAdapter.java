package br.puc.rio.jpedro.monetize.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.puc.rio.jpedro.monetize.model.Purchase;
import java.util.List;

public class PurchaseArrayAdapter extends ArrayAdapter
{
	private final Context context;
	private final List<Purchase> purchases;

	public PurchaseArrayAdapter(Context context, List purchases)
	{
		super ( context, R.layout.purchase_row, purchases);
		this.context = context;
		this.purchases = purchases;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = 	(LayoutInflater) context.
									getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.purchase_row, parent, false);
		
		Purchase p = purchases.get(position);

		TextView tvDtPurchase = (TextView) rowView.findViewById(R.id.dt_purchase);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.price);
        TextView tvCard = (TextView) rowView.findViewById(R.id.card);
        TextView tvCategory = (TextView) rowView.findViewById(R.id.category);

		tvDtPurchase.setText(p.getDtPurchase().getTime().toString());
        tvPrice.setText(String.valueOf(p.getPrice()));
        tvCard.setText(String.valueOf(p.getCard().getNumber()));
        if ( p.getEstablishment().getCategory() == null )
            tvCategory.setText("N/D");
        else
            tvCategory.setText(p.getEstablishment().getCategory().getName().toString());
		
		return rowView;
	}
}