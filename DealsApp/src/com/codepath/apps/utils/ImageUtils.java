package com.codepath.apps.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageUtils {

	public static Bitmap downloadImage(String url) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(url);
			if(in != null) {
				bitmap = BitmapFactory.decodeStream(in);
				in.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bitmap;

	}


	private static InputStream OpenHttpConnection(String urlString) throws IOException {

		Log.d("HTTP URL","URL: " + urlString);//key will be here


		InputStream in = null;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(false);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			int status = httpConn.getResponseCode();

			Log.d("HTTP CODE","HTTP CODE: " + status);//key will be here

			if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER)
			{
				String redirectUrl = httpConn.getHeaderField("location");
				Log.d("HTTP REDIRECT","Redirect to: " + redirectUrl);//key will be here
				httpConn.disconnect();
				return OpenHttpConnection(redirectUrl);
			}



			if (status == HttpURLConnection.HTTP_OK) {
				Log.d("HTTP SUCCESS","Redirect to: " + urlString);//key will be here
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}

	public static String getReconnectUrl(String urlString) throws IOException {

		Log.d("HTTP URL","URL: " + urlString);//key will be here


		InputStream in = null;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(false);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			int status = httpConn.getResponseCode();

			Log.d("HTTP CODE","HTTP CODE: " + status);//key will be here

			if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER)
			{
				String redirectUrl = httpConn.getHeaderField("location");
				Log.d("HTTP REDIRECT","Redirect to: " + redirectUrl);//key will be here
				httpConn.disconnect();
				return redirectUrl;
			}

		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return null;
	}

}
