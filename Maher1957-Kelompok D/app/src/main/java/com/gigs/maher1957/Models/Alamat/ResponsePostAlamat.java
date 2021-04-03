package com.gigs.maher1957.Models.Alamat;

import com.google.gson.annotations.SerializedName;

public class ResponsePostAlamat{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}