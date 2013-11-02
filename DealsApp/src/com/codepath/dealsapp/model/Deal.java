package com.codepath.dealsapp.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Deal {
	String affiliate;
	String name;
	String address;
	String address2;
	String storeID;
	String chainID;
	int totalDealsInThisStore;
	String homepage;
	String phone;
	String state;
	String city;
	String ZIP;
	String URL;
	String storeURL;
	String dealSource;
	String user;
	String userID;
	long ID;
	String dealTitle;
	String disclaimer;
	String dealinfo;
	String expirationDate;
	String postDate;
	String showImage;
	String showImageStandardBig;
	String showImageStandardSmall;
	String showLogo;
	String up;
	String down;
	int DealTypeID;
	int categoryID;
	int subcategoryID;
	double lat;
	double lon;
	double distance;
	float dealOriginalPrice;
	float dealPrice;
	float dealSavings;
	float dealDiscountPercent;
	private JSONObject jsonObject;

	public String getString(String name) {
        try {
            return jsonObject.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public String getAffiliate() {
		return getString("affiliate");
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}

	public int getTotalDealsInThisStore() {
		return totalDealsInThisStore;
	}

	public void setTotalDealsInThisStore(int totalDealsInThisStore) {
		this.totalDealsInThisStore = totalDealsInThisStore;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZIP() {
		return ZIP;
	}

	public void setZIP(String zIP) {
		ZIP = zIP;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getStoreURL() {
		return storeURL;
	}

	public void setStoreURL(String storeURL) {
		this.storeURL = storeURL;
	}

	public String getDealSource() {
		return getString("dealSource");
	}

	public void setDealSource(String dealSource) {
		this.dealSource = dealSource;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getID() {
		return getString("ID");
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getDealTitle() {
		return getString("dealTitle");
	}

	public void setDealTitle(String dealTitle) {
		this.dealTitle = dealTitle;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getDealinfo() {
		return dealinfo;
	}

	public void setDealinfo(String dealinfo) {
		this.dealinfo = dealinfo;
	}

	public String getExpirationDate() {
		return getString("expirationDate");
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPostDate() {
		return getString("postDate");
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getShowImage() {
		return getString("showImage");
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public String getShowImageStandardBig() {
		return getString("showImageStandardBig");
	}

	public void setShowImageStandardBig(String showImageStandardBig) {
		this.showImageStandardBig = showImageStandardBig;
	}

	public String getShowImageStandardSmall() {
		return getString("showImageStandardSmall");
	}

	public void setShowImageStandardSmall(String showImageStandardSmall) {
		this.showImageStandardSmall = showImageStandardSmall;
	}

	public String getShowLogo() {
		return showLogo;
	}

	public void setShowLogo(String showLogo) {
		this.showLogo = showLogo;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public int getDealTypeID() {
		return DealTypeID;
	}

	public void setDealTypeID(int dealTypeID) {
		DealTypeID = dealTypeID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public float getDealOriginalPrice() {
		return dealOriginalPrice;
	}

	public void setDealOriginalPrice(float dealOriginalPrice) {
		this.dealOriginalPrice = dealOriginalPrice;
	}

	public float getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(float dealPrice) {
		this.dealPrice = dealPrice;
	}

	public float getDealSavings() {
		return dealSavings;
	}

	public void setDealSavings(float dealSavings) {
		this.dealSavings = dealSavings;
	}

	public float getDealDiscountPercent() {
		return dealDiscountPercent;
	}

	public void setDealDiscountPercent(float dealDiscountPercent) {
		this.dealDiscountPercent = dealDiscountPercent;
	}

	public static Deal fromJson(JSONObject jsonObject) {
		Deal deal = new Deal();
		deal.jsonObject = jsonObject;
		return deal;
	}

	public static ArrayList<Deal> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Deal> deals = new ArrayList<Deal>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject dealJson = null;
			
			try {
				dealJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Deal deal = Deal.fromJson(dealJson);
			if (deal != null) {
				deals.add(deal);
			}
		}

		return deals;
	}

}
