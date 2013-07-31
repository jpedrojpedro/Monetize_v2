package br.puc.rio.jpedro.monetize.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import br.puc.rio.jpedro.monetize.model.*;
import br.puc.rio.jpedro.monetize.persistence.EstablishmentService;
import br.puc.rio.jpedro.monetize.persistence.PurchaseService;

import java.util.ArrayList;
import java.util.List;

public class SummaryPurchase extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    private ArrayList<Category> categories;
    private EditText tvBank;
    private EditText tvCardNumber;
    private EditText tvEstablishment;
    private EditText tvPrice;
    private EditText tvDate;
    private EditText tvHour;
    private Spinner spinnerCategory;
    private Button btnSave;
    private String selectedValue = "Alimentação";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_purchase);
        this.tvBank = (EditText) findViewById(R.id.tv_bank);
        this.tvCardNumber = (EditText) findViewById(R.id.tv_card_number);
        this.tvEstablishment = (EditText) findViewById(R.id.tv_establishment);
        this.tvPrice = (EditText) findViewById(R.id.tv_price);
        this.tvDate = (EditText) findViewById(R.id.tv_date);
        this.tvHour = (EditText) findViewById(R.id.tv_hour);
        this.spinnerCategory = (Spinner) findViewById(R.id.spinner_categories);
        this.btnSave = (Button) findViewById(R.id.btn_save_category);
        this.btnSave.setOnClickListener(this);
        // TODO :: Get information from Intent Extras
        Bank b = (Bank) getIntent().getSerializableExtra("Bank");
        Card c = (Card) getIntent().getSerializableExtra("Card");
        Establishment e = (Establishment) getIntent().getSerializableExtra("Establishment");
        Purchase p = (Purchase) getIntent().getSerializableExtra("Purchase");
        this.tvBank.setText(b.getName().toString());
        this.tvCardNumber.setText(String.valueOf(c.getNumber()));
        this.tvEstablishment.setText(e.getName().toString());
        this.tvPrice.setText(String.valueOf(p.getPrice()));
        this.tvDate.setText(p.getDtPurchase().getTime().toString());
        this.tvHour.setText(p.getDtPurchase().getTime().toString());
        this.spinnerCategory.setOnItemSelectedListener(this);
        // TODO :: Get categories from INI file
        this.categories = LoadCategories.getInstance().loadCategory(getBaseContext());
        this.populateSpinner(this.categories);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        this.selectedValue = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onClick(View view)
    {
        Category ct = new Category();
        ct.setName(this.selectedValue);
        // TODO :: Get information from Intent Extras
        Establishment e = (Establishment) getIntent().getSerializableExtra("Establishment");
        e.setCategory(ct);
        Purchase p = (Purchase) getIntent().getSerializableExtra("Purchase");
        p.setEstablishment(e);
        // Updating Purchase
        PurchaseService.savePurchase(p, getBaseContext());
        Toast.makeText(getBaseContext(), "Compra salva com sucesso", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SummaryPurchase.this, MainActivity.class);
        startActivity(i);
    }

    private void populateSpinner(ArrayList<Category> categories)
    {
        if ( categories != null )
        {
            List<String> listCategories = new ArrayList<String>();
            for ( Category c : categories )
                listCategories.add(c.getName());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                                                                    listCategories);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.spinnerCategory.setAdapter(adapter);
        }
    }
}