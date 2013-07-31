package br.puc.rio.jpedro.monetize.model;

import java.io.Serializable;
import java.util.Calendar;

public class Purchase implements Serializable
{
    private int smsId;
    private Calendar dtPurchase;
    private float price;
    private Card card;
    private Establishment establishment;

    public Purchase () {}

    public int getSmsId()
    {
        return smsId;
    }

    public void setSmsId(int smsId)
    {
        this.smsId = smsId;
    }

    public Calendar getDtPurchase()
    {
        return dtPurchase;
    }

    public void setDtPurchase(Calendar dtPurchase)
    {
        this.dtPurchase = dtPurchase;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public Establishment getEstablishment()
    {
        return establishment;
    }

    public void setEstablishment(Establishment establishment)
    {
        this.establishment = establishment;
    }
}