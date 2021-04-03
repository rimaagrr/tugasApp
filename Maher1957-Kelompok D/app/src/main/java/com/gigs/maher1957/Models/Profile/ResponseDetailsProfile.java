package com.gigs.maher1957.Models.Profile;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailsProfile{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}
}