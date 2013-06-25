package br.puc.rio.jpedro.monetize.parser;

import android.util.Log;
import com.google.code.regexp.Matcher;
import com.google.code.regexp.Pattern;

public class Parser
{
    // Variable TAG used for debugging reasons
    private static final String TAG = "ParserClass";

    private String nomeBanco;
    private String regra;

    public Parser ( String nomeBanco, String regra )
    {
        this.nomeBanco = nomeBanco;
        this.regra = regra;
    }

    public ParserResult doParse ( String message ) throws IllegalStateException, NumberFormatException
    {
        Matcher m = Pattern.compile(this.regra).matcher(message);
        m.find();
        ParserResult result =  new ParserResult();
        // TODO :: Code just for debugging
        Log.i(TAG, "After Match :: Bank : " + m.group("banco"));
        Log.i(TAG, "After Match :: CardNumber : " + m.group("numero"));
        Log.i(TAG, "After Match :: Establishment : " + m.group("estabelecimento"));
        Log.i(TAG, "After Match :: Currency : " + m.group("moeda"));
        Log.i(TAG, "After Match :: Price : " + m.group("preco"));
        Log.i(TAG, "After Match :: Data : " + m.group("data"));
        Log.i(TAG, "After Match :: Hora : " + m.group("hora"));
        Log.i(TAG, "After Match :: Minuto : " + m.group("minuto"));
        // End of debugging
        result.setBank(m.group("banco"));
        result.setCardNumber(m.group("numero"));
        result.setEstablishment(m.group("estabelecimento"));
        result.setCurrency(m.group("moeda"));
        result.setPrice(m.group("preco"));
        result.setDate(m.group("data"));
        result.setHour(m.group("hora"));
        result.setMinute(m.group("minuto"));
        return result;
    }

    public String getRegra()
    {
        return regra;
    }

    public void setRegra(String regra)
    {
        this.regra = regra;
    }

    public String getNomeBanco()
    {

        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco)
    {
        this.nomeBanco = nomeBanco;
    }
}