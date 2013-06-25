package br.puc.rio.jpedro.monetize.parser;

import android.content.Context;
import br.puc.rio.jpedro.monetize.activity.R;
import org.ini4j.Ini;
import javax.xml.xpath.XPathExpressionException;
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

    public Parser loadParser ( Context context ) throws IOException,
                                                        XPathExpressionException
    {
        /*
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/xml/banco/regra/text()";
        InputSource inputSource =
                new InputSource(context.getResources().openRawResource(R.xml.parsers));
        String regra = (String) xPath.evaluate(expression, inputSource, XPathConstants.STRING);
        return new Parser("Itaú", regra);
        */
        InputStream inputStream = context.getResources().openRawResource(R.raw.parsers);
        Ini iniFile = new Ini();
        iniFile.load(inputStream);
        Ini.Section itau = iniFile.get("itau");
        String regra = itau.get("regra");
        return new Parser("Itaú", regra);
    }
}