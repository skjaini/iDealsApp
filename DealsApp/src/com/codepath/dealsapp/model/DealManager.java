package com.codepath.dealsapp.model;

import java.util.ArrayList;
import java.util.List;

public class DealManager {

	private static final DealManager _instance = new DealManager();  
	private int categoryID = 0;
	
	private List<Deal> deals = new ArrayList<Deal>();	
	
	private DealManager() {
		
	}
	
	public static DealManager getInstance() {
		return _instance;
	}
	
	public List<Deal> getDeals(){
		return deals;
	}

	public Deal getDeal(int position) {
		return deals.get(position);
	}
	
	public void addDeals(List<Deal> deals) {
		this.deals.addAll(deals);
	}
	
	public int getSize() {
		return deals.size();
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
	
	
}
