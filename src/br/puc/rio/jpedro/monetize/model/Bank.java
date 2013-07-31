package br.puc.rio.jpedro.monetize.model;

import java.io.Serializable;

public class Bank implements Serializable
{
    private String name;

    public Bank () {}

    public Bank ( String name )
    {
        this.name = name;
    }

    public String getName ()
    {
        return this.name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }
}