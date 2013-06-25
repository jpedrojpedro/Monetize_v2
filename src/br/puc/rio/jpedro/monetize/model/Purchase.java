package br.puc.rio.jpedro.monetize.model;

import java.util.Calendar;

public class Purchase
{
    private int smsId;
    private Calendar dtPurchase;
    private float price;

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
}