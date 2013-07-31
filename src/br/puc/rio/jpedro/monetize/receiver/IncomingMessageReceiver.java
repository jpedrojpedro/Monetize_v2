package br.puc.rio.jpedro.monetize.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import br.puc.rio.jpedro.monetize.controller.ParserController;
import br.puc.rio.jpedro.monetize.email.SendMail;
import br.puc.rio.jpedro.monetize.parser.LoadParsers;
import br.puc.rio.jpedro.monetize.parser.Parser;
import br.puc.rio.jpedro.monetize.parser.ParserResult;
import java.io.IOException;

public class IncomingMessageReceiver extends BroadcastReceiver implements Runnable
{
    // Variable TAG used for debugging reasons
    private static final String TAG = "IncomingMessageReceiver";
    private String message = "";
    private String phoneNumber = null;

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
        for (int i = 0; i < pdus.length; i++)
        {
            SmsMessage currentMessage =
                    SmsMessage.createFromPdu((byte[]) pdus[i]);
            if ( this.phoneNumber == null )
                this.phoneNumber = currentMessage.getDisplayOriginatingAddress();
            this.message +=
                    currentMessage.getDisplayMessageBody();
        }
        Log.i ( TAG, "Message received: " + this.phoneNumber + " " + this.message );
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
            new ParserController(result, context);
        }
        catch (IOException ex1)
        {
            Log.e( TAG, "Problems to load INI file" );
        }
        catch (NullPointerException ex2)
        {
            Log.e( TAG, ex2.getMessage() );
            // TODO :: Dispatch email with unsuccessful matching
            new Thread(this).start();
        }
        catch (IllegalStateException ex3)
        {
            Log.e( TAG, "No successful match so far" );
            // TODO :: Dispatch email with unsuccessful matching
            new Thread(this).start();
        }
        catch (NumberFormatException ex4)
        {
            Log.e( TAG, "Problems with numeric parsing" );
            // TODO :: Dispatch email with unsuccessful matching
            new Thread(this).start();
        }
    }

    @Override
    public void run()
    {
        synchronized (this)
        {
            SendMail.sendEmail(this.phoneNumber, this.message);
        }
    }
}