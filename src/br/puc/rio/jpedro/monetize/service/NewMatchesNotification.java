package br.puc.rio.jpedro.monetize.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NewMatchesNotification
{
	private String message;
	private int icon;
	private Context context;
    private Intent intent;
	
	public NewMatchesNotification ( Context ctx, String message, int drawableIcon, Intent intent )
	{
		this.message = message;
		this.icon = drawableIcon;
		this.context = ctx;
        this.intent = intent;
	}
	
	public void send ()
	{
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager myManager = (NotificationManager) this.context.getSystemService(ns);
		long when = System.currentTimeMillis();
		Notification myNotification = new Notification(this.icon, this.message, when);
		PendingIntent contentIntent = PendingIntent.getActivity(this.context, 0, this.intent, 0);
		myNotification.setLatestEventInfo(this.context, "Monetize App", this.message, contentIntent);
        myNotification.flags = Notification.FLAG_AUTO_CANCEL;
		myManager.notify(1, myNotification);
	}
}