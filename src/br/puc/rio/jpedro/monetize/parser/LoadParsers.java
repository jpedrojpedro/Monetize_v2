package br.puc.rio.jpedro.monetize.parser;

import android.content.Context;
import br.puc.rio.jpedro.monetize.activity.R;
import org.ini4j.Ini;
import java.io.IOException;
import java.io.InputStream;

public class LoadParsers
{
    private static LoadParsers instance = null;

    protected LoadParsers () {}

    public static LoadParsers getInstance ()
    {
        if ( instance == null )
            instance = new LoadParsers();
        return instance;
    }

    public Parser loadParser ( Context context ) throws IOException
    {
        InputStream inputStream = context.getResources().openRawResource(R.raw.parsers);
        Ini iniFile = new Ini();
        iniFile.load(inputStream);
        Ini.Section itau = iniFile.get("itau");
        String regra = itau.get("regra");
        return new Parser("Itaú", regra);
    }
}