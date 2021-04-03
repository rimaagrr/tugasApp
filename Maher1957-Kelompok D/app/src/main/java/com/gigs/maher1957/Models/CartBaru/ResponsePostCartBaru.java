package com.gigs.maher1957.Models.CartBaru;

import com.google.gson.annotations.SerializedName;

public class ResponsePostCartBaru{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}