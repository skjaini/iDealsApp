package com.codepath.dealsapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.dealsapp.DealsAdapter;
import com.codepath.dealsapp.R;
import com.codepath.dealsapp.YelpClient;
import com.codepath.dealsapp.model.Deal;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DealsListFragment extends ListFragment {
	private ArrayList<Deal> deals = new ArrayList<Deal>();
	DealsAdapter dealsAdapter;
	ListView lvDeals;
	private int categoryID = 0;
	OnDealSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnDealSelectedListener {
        public void onDealSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDealSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
	
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        mCallback.onDealSelected(position);
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
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        
		ArrayList<Deal> tmpDeals = new ArrayList<Deal>();
		dealsAdapter = new DealsAdapter(getActivity(), tmpDeals, tf);
        
		// lvDeals = (ListView) view.findViewById(R.id.lvDeals);
		lvDeals = (ListView) view.findViewById(android.R.id.list);
		lvDeals.setAdapter(dealsAdapter);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		int categoryID = getArguments().getInt("categoryID", 0);
		loadDeals(categoryID);
	}
	
	public void loadDeals(int id) {
		String requestUrl;
		categoryID = id;
		if(categoryID < 1) {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip=94103&mileradius=5&limit=10&orderby=radius";
		} else {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip=94103&mileradius=5&limit=10&orderby=radius&categoryid="+categoryID;
		}

		Log.d("DEBUG", "list fragment requestUrl:"+requestUrl);
		fetchDeals(requestUrl);
		// doYelpSearch();
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
	
	public void fetchDeals(String requestUrl) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(requestUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
//				Log.d("DEBUG", "fetchDeals jsonArray:" + jsonArray.toString());

				deals.clear();
				deals = Deal.fromJSONArray(jsonArray);
				dealsAdapter.clear();
				dealsAdapter.addAll(deals);
			}
		});
	}

}
