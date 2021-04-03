package com.gigs.maher1957.Models.Favorite;

import com.google.gson.annotations.SerializedName;

public class ResponsePostFav{

	@SerializedName("data")
	private DataPostFav dataPostFav;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public DataPostFav getDataPostFav(){
		return dataPostFav;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}