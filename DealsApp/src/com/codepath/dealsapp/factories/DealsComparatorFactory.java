package com.codepath.dealsapp.factories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.codepath.dealsapp.model.Deal;

class ExpiryDealComparator implements Comparator<Deal> {
	@Override
	public int compare(Deal lhs, Deal rhs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date expiryDate1 = null;
		Date expiryDate2 = null;

		try {
			expiryDate1 = sdf.parse(lhs.getExpirationDate());
			expiryDate2 = sdf.parse(rhs.getExpirationDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return expiryDate1.compareTo(expiryDate2);
	}	
}

class RecentDealComparator implements Comparator<Deal> {
	@Override
	public int compare(Deal lhs, Deal rhs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date expiryDate1 = null;
		Date expiryDate2 = null;

		try {
			expiryDate1 = sdf.parse(lhs.getPostDate());
			expiryDate2 = sdf.parse(rhs.getPostDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return expiryDate1.compareTo(expiryDate2);
	}	
}

class ClosestDealComparator implements Comparator<Deal> {
	@Override
	public int compare(Deal lhs, Deal rhs) {
		return (int)(lhs.getDistance() - rhs.getDistance());
	}	
}

class SavingsDealComparator implements Comparator<Deal> {
	@Override
	public int compare(Deal lhs, Deal rhs) {
		return (int)(lhs.getDealSavings() - rhs.getDealSavings());
	}	
}

class DealsCountComparator implements Comparator<Deal> {
	@Override
	public int compare(Deal lhs, Deal rhs) {
		return lhs.getTotalDealsInThisStore() - rhs.getTotalDealsInThisStore();
	}
}

public class DealsComparatorFactory {
	public static Comparator<Deal> getDealsComparator(String comparator) {
		if(comparator.equals("Expiring Soon")) {
			return new ExpiryDealComparator();
		} else if(comparator.equals("Recently Added")) {
			return new RecentDealComparator();
		} else if(comparator.equals("Closest Deal")) {
			return new ClosestDealComparator();
		} else if(comparator.equals("Deal Savings")) {
			return new SavingsDealComparator();
		} else if(comparator.equals("Deals List")) {
			return new DealsCountComparator();
		}
		return null;
	}
}
