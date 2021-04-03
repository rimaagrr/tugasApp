package com.gigs.maher1957.Models.Checkout;

import com.google.gson.annotations.SerializedName;

public class ResponseCheckout {

	@SerializedName("data")
	private DataCheckout dataCheckout;

	@SerializedName("message")
	private String message;

	public void setDataCheckout(DataCheckout dataCheckout){
		this.dataCheckout = dataCheckout;
	}

	public DataCheckout getDataCheckout(){
		return dataCheckout;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}