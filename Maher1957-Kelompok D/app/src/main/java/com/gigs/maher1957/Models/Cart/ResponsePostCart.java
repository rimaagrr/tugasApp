package com.gigs.maher1957.Models.Cart;

import com.google.gson.annotations.SerializedName;

public class ResponsePostCart{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}