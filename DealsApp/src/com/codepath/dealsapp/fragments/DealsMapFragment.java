package com.codepath.dealsapp.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.dealsapp.DealsAdapter;
import com.codepath.dealsapp.R;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DealsMapFragment extends Fragment {
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	Location mCurrentLocation;
	LocationClient mLocationClient;
	LocationManager locationManager;
	DealsAdapter dealsAdapter;
	ListView lvDeals;
	private int categoryID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		View v =  inf.inflate(R.layout.fragment_deals_map, parent, false);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FragmentManager fm = getChildFragmentManager();
		mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
		
		if (mapFragment == null) {
			mapFragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map, mapFragment).commit();
			// fm.executePendingTransactions();
		}
		
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = mapFragment.getMap();
	        map.setMyLocationEnabled(true);
			
			LatLng latLng = new LatLng(37.7709, -122.404);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
		}

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

		Log.d("DEBUG", "map fragment requestUrl:"+requestUrl);
		fetchDeals(requestUrl);
	}
	
	public void fetchDeals(String requestUrl) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(requestUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
//				Log.d("DEBUG", "fetchDeals jsonArray:" + jsonArray.toString());

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject dealJson = null;
					
					try {
						dealJson = jsonArray.getJSONObject(i);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}

					double latitude = 0;
					double longitude = 0;
					String dealTitle = null;
					try {
						latitude = dealJson.getDouble("lat");
						longitude = dealJson.getDouble("lon");
						dealTitle = dealJson.getString("dealTitle");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Log.d("DEBUG", "lat:"+latitude+" lon:"+longitude+" title:"+dealTitle);
					drawMarker(latitude, longitude, dealTitle);
				}
				
				
			}
		});
	}
	
	private void drawMarker(double latitude, double longitude, String title) {
		map.clear();
		LatLng position = new LatLng(latitude, longitude);
		
		map.addMarker(new MarkerOptions().anchor(0.0f, 1.0f).position(position)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
				.title(title));
	}

}