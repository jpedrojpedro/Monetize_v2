package br.puc.rio.jpedro.monetize.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import br.puc.rio.jpedro.monetize.parser.LoadParsers;
import br.puc.rio.jpedro.monetize.parser.Parser;
import br.puc.rio.jpedro.monetize.parser.ParserResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class IncomingMessageReceiver extends BroadcastReceiver
{
    // Variable TAG used for debugging reasons
    private static final String TAG = "IncomingMessageReceiver";

	@Override
    public void onReceive(Context context, Intent intent)
	{
        Bundle bundle = intent.getExtras();
        if ( bundle == null )
            return;
        /*
         * A PDU means "Protocol Description Unit",
         * which is the industry format for an SMS message.
         * As SMSMessage class reads/writes them you, shouldn't
         * need to split them. A large message might be
         * broken into many. This is the reason why it is
         * an array of objects.
         */
        Object[] pdus = (Object[]) bundle.get("pdus");
        String message = "";
        String phoneNumber = null;
        for (int i = 0; i < pdus.length; i++)
        {
            SmsMessage currentMessage =
                    SmsMessage.createFromPdu((byte[]) pdus[i]);
            if ( phoneNumber == null )
                phoneNumber = currentMessage.getDisplayOriginatingAddress();
            message +=
                    currentMessage.getDisplayMessageBody();
        }
        Log.i ( TAG, "Message received: " + phoneNumber + " " + message );
        try
        {
            Parser p = LoadParsers.getInstance().loadParser(context);
            ParserResult result = p.doParse(message);
            // TODO :: Code just for debugging
            Log.i ( TAG, "Result Obj :: Bank : " + result.getBank() );
            Log.i ( TAG, "Result Obj :: Establishment : " + result.getEstablishment() );
            Log.i ( TAG, "Result Obj :: CardNumber : " + result.getCardNumber() );
            Log.i ( TAG, "Result Obj :: Currency : " + result.getCurrency() );
            Log.i ( TAG, "Result Obj :: Price : " + result.getPrice() );
            Log.i ( TAG, "Result Obj :: Date : " + result.getDate() );
            Log.i ( TAG, "Result Obj :: Hour : " + result.getHour() );
            Log.i ( TAG, "Result Obj :: Minute : " + result.getMinute() );
            // End of debugging
        }
        catch (IOException ex1)
        {
            Log.e( TAG, "Problems to load INI file" );
        }
        catch (NullPointerException ex2)
        {
            Log.e( TAG, ex2.getMessage() );
        }
        catch (XPathExpressionException ex3)
        {
            Log.e( TAG, "Problems to parse XML file" );
        }
        catch (IllegalStateException ex4)
        {
            Log.e( TAG, ex4.getMessage() );
        }
        catch (NumberFormatException ex5)
        {
            Log.e( TAG, "Problems with numeric parsing" );
        }
    }	
}