package br.puc.rio.jpedro.monetize.model;

import java.io.Serializable;

public class Card implements Serializable
{
    private int number;
    private Bank bank;

    public Card () {}

    public Card ( int number, Bank bank )
    {
        this.number = number;
        this.bank = bank;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Bank getBank()
    {
        return bank;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }
}