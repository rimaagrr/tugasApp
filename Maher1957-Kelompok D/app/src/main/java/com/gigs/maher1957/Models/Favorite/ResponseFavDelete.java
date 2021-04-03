package com.gigs.maher1957.Models.Favorite;

import com.google.gson.annotations.SerializedName;

public class ResponseFavDelete{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}