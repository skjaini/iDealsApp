package com.codepath.dealsapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.dealsapp.model.Deal;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DealsAdapter extends ArrayAdapter<Deal> {

	public DealsAdapter(Context context, List<Deal> deals) {
		super(context, 0, deals);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view = convertView;
	    if (view == null) {
	    	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	view = inflater.inflate(R.layout.deal_item, null);
	    }
	    
        Deal deal = getItem(position);
        
        ImageView imageView = (ImageView) view.findViewById(R.id.ivStoreImage);
        ImageLoader.getInstance().displayImage(deal.getShowImage(), imageView);
        
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(deal.getDealTitle());

        TextView postedDate = (TextView) view.findViewById(R.id.tvPostedDate);
        postedDate.setText("Posted at: "+deal.getPostDate());
        
        TextView expiryDate = (TextView) view.findViewById(R.id.tvExpiryDate);
        expiryDate.setText("Expires on:" +deal.getExpirationDate());
        
        TextView tvDealSource = (TextView) view.findViewById(R.id.tvDealSource);
        tvDealSource.setText("from "+deal.getDealSource());
		
        return view;
	}
}