package com.codepath.dealsapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.dealsapp.SimpleGestureFilter.SimpleGestureListener;
import com.codepath.dealsapp.model.Deal;
import com.codepath.dealsapp.model.DealManager;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DealDetailActivity extends Activity implements SimpleGestureListener{

	private SimpleGestureFilter detector;
	private int position;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal_detail);

		detector = new SimpleGestureFilter(this,this);
		position = (int) getIntent().getIntExtra("position",0);
		refresh();
	}


	public static String getFinalRedirectedUrl(String url)
	{
	    HttpURLConnection connection = null;
	    String finalUrl = url;
	    try {
			do {
			        try {
						connection = (HttpURLConnection) new URL(finalUrl).openConnection();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        connection.setInstanceFollowRedirects(false);
			        connection.setUseCaches(false);
			        try {
						connection.setRequestMethod("GET");
					} catch (ProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						connection.connect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        int responseCode = 0;
					try {
						responseCode = connection.getResponseCode();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        if (responseCode >=300 && responseCode <400)
			        {
			            String redirectedUrl = connection.getHeaderField("Location");
			            if(null== redirectedUrl)
			                break;
			            finalUrl =redirectedUrl;
			                                System.out.println( "redirected url: " + finalUrl);
			        }
			        else
			            break;
			    }while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    connection.disconnect();
	    return finalUrl;
	}
	
	private void refresh() {
		Deal deal = DealManager.getInstance().getDeal(position);

		String finalImageUrl = getFinalRedirectedUrl(deal.getShowImageStandardSmall());
        
		ImageView imageView = (ImageView) findViewById(R.id.sivImage);
		imageView.setImageResource(0);
        ImageLoader.getInstance().displayImage(finalImageUrl, imageView);
		
		TextView tvTitle = (TextView) findViewById(R.id.dealTitleTV);
		tvTitle.setText(deal.getName());
		
		TextView tvExpires = (TextView) findViewById(R.id.expiresTV);
		tvExpires.setText("Expires "+deal.getExpirationDate());
		
		TextView tvDealValue = (TextView) findViewById(R.id.dealValueTV);
		
		String dealValue = "";
		
		if(deal.getDealDiscountPercent() > 0) {
			dealValue = deal.getDealDiscountPercent() + "% OFF";
		} else if(deal.getDealSavings()>0)  {
			dealValue="Savings: $"+ deal.getDealSavings();
		} 
		
		tvDealValue.setText(dealValue);
		
		TextView tvDetail = (TextView) findViewById(R.id.detailsTV);
		tvDetail.setText(deal.getDealTitle());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deal_detail, menu);
		return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me){
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}
	
	@Override
	public void onSwipe(int direction) {
		switch (direction) {
		case SimpleGestureFilter.SWIPE_RIGHT : 
		case SimpleGestureFilter.SWIPE_DOWN : 
			if(position>1) {
				position--;
				refresh();
			}
			break;
		case SimpleGestureFilter.SWIPE_LEFT : 
		case SimpleGestureFilter.SWIPE_UP : 
			if(position < DealManager.getInstance().getSize() -1) {
				position++;
				refresh();
			}
			break;
		}
	}

	@Override
	public void onDoubleTap() {
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}


}
