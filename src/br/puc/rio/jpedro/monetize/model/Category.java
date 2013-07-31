package br.puc.rio.jpedro.monetize.model;

import java.io.Serializable;

public class Category implements Serializable
{
    private String name;

    public Category () {}

    public String getName ()
    {
        return this.name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }
}