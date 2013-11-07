package com.codepath.dealsapp;

/*
 Example code based on code from Nicholas Smith at http://imnes.blogspot.com/2011/01/how-to-use-yelp-v2-from-java-including.html
 For a more complete example (how to integrate with GSON, etc) see the blog post above.
 */

import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Example for accessing the Yelp API.
 */
public class YelpClient extends OAuthBaseClient {

	public static Class<? extends Api> REST_API_CLASS = YelpApi2.class;
	public static final String REST_URL = "http://api.yelp.com/v2";
	protected static final String TAG = "YELP";
	private static final String CONSUMER_KEY = "1OzYit9k1vhdZ1LLVR5tVA";
	private static final String CONSUMER_SECRET = "RRt7l7O0-nGlVZlXvPAgozTSWUE";
	private static final String TOKEN = "qx7wGumsmoKUiXVYj1VnNJRTmeYJFWq6";
	private static final String TOKEN_SECRET = "GNEdIwKy-D_shg1O5rJzgMPAHcw";
	private static final String REST_CALLBACK_URL = "oauth://wheretoeat";
	OAuthService service;
	Token accessToken;

	public YelpClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, CONSUMER_KEY, CONSUMER_SECRET, REST_CALLBACK_URL);
		client.setAccessToken(new Token(TOKEN, TOKEN_SECRET));
	}

	// Setting up the search endpoint
    public void search(String term, String location, AsyncHttpResponseHandler handler) {
    	// http://api.yelp.com/v2/search?term=food&location=San+Francisco
    	String apiUrl = getApiUrl("search");
        RequestParams params = new RequestParams();
        params.put("term", term);
        params.put("location", location);
        client.get(apiUrl, params, handler);
    }
}