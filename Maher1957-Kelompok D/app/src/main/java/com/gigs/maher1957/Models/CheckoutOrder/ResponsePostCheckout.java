package com.gigs.maher1957.Models.CheckoutOrder;

import com.google.gson.annotations.SerializedName;

public class ResponsePostCheckout{

	@SerializedName("data")
	private DataCheckoutOrder dataCheckoutOrder;

	@SerializedName("message")
	private String message;

	public void setDataCheckoutOrder(DataCheckoutOrder dataCheckoutOrder){
		this.dataCheckoutOrder = dataCheckoutOrder;
	}

	public DataCheckoutOrder getDataCheckoutOrder(){
		return dataCheckoutOrder;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}