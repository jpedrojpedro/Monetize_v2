package br.puc.rio.jpedro.monetize.controller;

import android.content.Context;
import android.content.Intent;
import br.puc.rio.jpedro.monetize.activity.R;
import br.puc.rio.jpedro.monetize.activity.SummaryPurchase;
import br.puc.rio.jpedro.monetize.model.Bank;
import br.puc.rio.jpedro.monetize.model.Card;
import br.puc.rio.jpedro.monetize.model.Establishment;
import br.puc.rio.jpedro.monetize.model.Purchase;
import br.puc.rio.jpedro.monetize.parser.ParserResult;
import br.puc.rio.jpedro.monetize.persistence.BankService;
import br.puc.rio.jpedro.monetize.persistence.CardService;
import br.puc.rio.jpedro.monetize.persistence.EstablishmentService;
import br.puc.rio.jpedro.monetize.persistence.PurchaseService;
import br.puc.rio.jpedro.monetize.service.NewMatchesNotification;
import java.util.Calendar;

public class ParserController
{
    public ParserController ( ParserResult result, Context context )
    {
        Bank parsedBank = new Bank(result.getBank());
        Card parsedCard = new Card(Integer.valueOf(result.getCardNumber()), parsedBank);
        Establishment parsedEstablishment = new Establishment(result.getEstablishment());
        Purchase parsedPurchase = new Purchase();
        parsedPurchase.setCard(parsedCard);
        parsedPurchase.setEstablishment(parsedEstablishment);
        parsedPurchase.setPrice(Float.parseFloat(result.getPrice().replaceFirst(",",".")));
        String[] date = result.getDate().split("/");
        Calendar calDate = Calendar.getInstance();
        calDate.set(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
        parsedPurchase.setDtPurchase(calDate);
        // Storing elements
        BankService.saveBank(parsedBank, context);
        CardService.saveCard(parsedCard, context);
        EstablishmentService.saveEstablishment(parsedEstablishment, context);
        PurchaseService.savePurchase(parsedPurchase, context);
        // TODO :: Dispatch Pending Intent
        Intent i = new Intent(context, SummaryPurchase.class);
        i.putExtra("Bank", parsedBank);
        i.putExtra("Card", parsedCard);
        i.putExtra("Establishment", parsedEstablishment);
        i.putExtra("Purchase", parsedPurchase);
        new NewMatchesNotification(context, "Nova Compra Cadastrada", R.drawable.monetize_small, i).send();
    }
}