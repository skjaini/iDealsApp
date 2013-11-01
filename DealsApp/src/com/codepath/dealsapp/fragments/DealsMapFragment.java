package com.codepath.dealsapp.fragments;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.dealsapp.R;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DealsMapFragment extends Fragment {
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	Location mCurrentLocation;
	LocationClient mLocationClient;
	LocationManager locationManager;

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
	        
	        // Getting LocationManager object from System Service LOCATION_SERVICE
	        LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

	        // Creating a criteria object to retrieve provider
	        Criteria criteria = new Criteria();

	        // Getting the name of the best provider
	        String provider = locationManager.getBestProvider(criteria, true);

	        // Getting Current Location
	        Location location = locationManager.getLastKnownLocation(provider);
	        
	        LocationListener locationListener = new LocationListener() {
	        	public void onLocationChanged(Location location) {
					// redraw the marker when get location update.
					drawMarker(location);

					// Getting latitude of the current location
					double latitude = location.getLatitude();

					// Getting longitude of the current location
					double longitude = location.getLongitude();

					// Creating a LatLng object for the current location
					LatLng latLng = new LatLng(latitude, longitude);

					// Showing the current location in Google Map
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
	            }

				@Override
				public void onProviderDisabled(String provider) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onProviderEnabled(String provider) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {
					// TODO Auto-generated method stub
					
				}
	        };
	        
	        locationManager.requestLocationUpdates(provider, 20000, 0, locationListener);
		}
	}

	private void drawMarker(Location location) {
		map.clear();
		LatLng currentPosition = new LatLng(location.getLatitude(),
				location.getLongitude());
		map.addMarker(new MarkerOptions().anchor(0.0f, 1.0f).position(currentPosition).snippet(
				"Lat:" + location.getLatitude() + "Lng:"
						+ location.getLongitude()));
	}

}