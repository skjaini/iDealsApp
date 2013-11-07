package com.codepath.dealsapp.fragments;

import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.codepath.dealsapp.DealDetailActivity;
import com.codepath.dealsapp.DealsAdapter;
import com.codepath.dealsapp.R;
import com.codepath.dealsapp.factories.DealsComparatorFactory;
import com.codepath.dealsapp.YelpClient;
import com.codepath.dealsapp.model.Deal;
import com.codepath.dealsapp.model.DealManager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DealsListFragment extends ListFragment {
	DealsAdapter dealsAdapter;
	Spinner sortSpinner;
	ListView lvDeals;
	private int categoryID = 0;
	private String zipCode = "94103";
	DealManager dealManager;

    // Container Activity must implement this interface
    public interface OnDealSelectedListener {
        public void onDealSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
	
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Intent i = new Intent(getView().getContext(), DealDetailActivity.class);
		i.putExtra("position", position);
		startActivity(i);
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_deals_list, container, false);
		
		String fontPath = "fonts/HelveticaNeueMedium.ttf";
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        
        dealManager = DealManager.getInstance();
        dealsAdapter = new DealsAdapter(getActivity(), dealManager.getDeals(), typeFace);
		
        dealManager.getDeals().clear();
        
		lvDeals = (ListView) view.findViewById(android.R.id.list);
		lvDeals.setAdapter(dealsAdapter);

		setListAdapter(dealsAdapter);

		sortSpinner = (Spinner) view.findViewById(R.id.sortSpinner);
		sortSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Get the correct sort descriptor and sort it
				String comparatorString = (String)sortSpinner.getItemAtPosition(arg2);
				Comparator<Deal> compartor = DealsComparatorFactory.getDealsComparator(comparatorString);
				dealsAdapter.sort(compartor);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		
		// return view;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setListShown(false);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		int id = getArguments().getInt("categoryID", 0);
		String zip = getArguments().getString("zip", "94103");
		
		if(categoryID == id && zipCode == zip && dealManager.getSize() > 0) {
			dealsAdapter.notifyDataSetChanged();
		} else {
			categoryID = id;
			zipCode = zip;
			loadDeals(id, zip);
		}
	}
	
	public void loadDeals(int id, String zip) {
		String requestUrl;
		categoryID = id;
		if(categoryID < 1) {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip="+zip+"&mileradius=5&limit=10&orderby=radius";
		} else {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip="+zip+"&mileradius=5&limit=10&orderby=radius&categoryid="+categoryID;
		}

		Log.d("DEBUG", "list fragment requestUrl:"+requestUrl);
		fetchDeals(requestUrl);
	}

	public void fetchDeals(String requestUrl) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(requestUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				/*
				deals.clear();
				deals = Deal.fromJSONArray(jsonArray);
				dealsAdapter.clear();
				dealsAdapter.addAll(deals);
				*/
				Log.d("DEBUG", "size of response json:"+jsonArray.length());
				dealManager.setCategoryID(categoryID);
				dealManager.getDeals().clear();
				dealManager.addDeals(Deal.fromJSONArray(jsonArray));
				dealsAdapter.notifyDataSetChanged();
				setListShown(true);
			}
		});
	}
	
	public void doYelpSearch() {
		YelpClient client = new YelpClient(getActivity());
		client.search("food", "san francisco", new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int code, JSONObject body) {
				try {
					JSONArray businessesJson = body.getJSONArray("businesses");
		                        // Here we now have the json array of businesses!
					Log.d("DEBUG", businessesJson.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Log.d("DEBUG", "failed yelp search call");
			}
		});
	}
	

}
