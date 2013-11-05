package com.codepath.dealsapp.fragments;

import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.codepath.dealsapp.DealsAdapter;
import com.codepath.dealsapp.R;
import com.codepath.dealsapp.factories.DealsComparatorFactory;
import com.codepath.dealsapp.model.Deal;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DealsListFragment extends Fragment {
	OnDealSelectedListener mListener;
	private ArrayList<Deal> deals = new ArrayList<Deal>();
	DealsAdapter dealsAdapter;
	Spinner sortSpinner;
	ListView lvDeals;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_deals_list, container, false);

		dealsAdapter = new DealsAdapter(getActivity(), deals);

		lvDeals = (ListView) view.findViewById(R.id.lvDeals);
		lvDeals.setAdapter(dealsAdapter);

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

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		String requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip=94103&mileradius=5&limit=5&orderby=radius";
		fetchDeals(requestUrl);
	}

	public void fetchDeals(String requestUrl) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(requestUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				Log.d("DEBUG", "fetchDeals jsonArray:" + jsonArray.toString());

				deals.clear();
				deals = Deal.fromJSONArray(jsonArray);
				dealsAdapter.addAll(deals);
			}
		});
	}

	public interface OnDealSelectedListener {
		public void onDealSelected(int dealId);
	}
}
