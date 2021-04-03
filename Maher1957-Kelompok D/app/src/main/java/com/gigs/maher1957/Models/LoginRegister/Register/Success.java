package com.gigs.maher1957.Models.LoginRegister.Register;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("message")
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}