package com.gigs.maher1957.Models.CartBaru;

import com.google.gson.annotations.SerializedName;

public class ResponseDeleteCartBaru{

	@SerializedName("data")
	private int data;

	@SerializedName("message")
	private String message;

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}