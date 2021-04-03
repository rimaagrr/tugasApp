package com.gigs.maher1957.Models.CartV2;

import com.google.gson.annotations.SerializedName;

public class ResponseGetCartv2 {

	@SerializedName("data")
	private DataGetCart dataGetCart;

	@SerializedName("message")
	private String message;

	public void setDataGetCart(DataGetCart dataGetCart){
		this.dataGetCart = dataGetCart;
	}

	public DataGetCart getDataGetCart(){
		return dataGetCart;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}