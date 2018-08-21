package com.checkin.webapp.booking.model;

import java.util.List;

public class CheckBookingVO {
	private int a;
	private String checkin;
	private String checkout;
	
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}

	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	@Override
	public String toString() {
		return "CheckBookingVO [a=" + a + ", checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
}
