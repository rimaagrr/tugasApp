package com.gigs.maher1957.Models.CartV2;

import com.google.gson.annotations.SerializedName;

public class ResponsePostCartv2 {

	@SerializedName("data")
	private DataPostCart dataPostCart;

	@SerializedName("message")
	private String message;

	public void setDataPostCart(DataPostCart dataPostCart){
		this.dataPostCart = dataPostCart;
	}

	public DataPostCart getDataPostCart(){
		return dataPostCart;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}