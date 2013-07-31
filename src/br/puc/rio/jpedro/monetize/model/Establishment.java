package br.puc.rio.jpedro.monetize.model;

import java.io.Serializable;

public class Establishment implements Serializable
{
    private String name;
    private Category category;

    public Establishment () {}

    public Establishment ( String name )
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

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}