package com.codepath.dealsapp;
import android.content.Context;

public class YelpClientApp extends com.activeandroid.app.Application {

	public static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		YelpClientApp.context = this;
	}

	public static YelpClient getRestClient() {
		return (YelpClient) YelpClient.getInstance(YelpClient.class, YelpClientApp.context);
	}
	
}