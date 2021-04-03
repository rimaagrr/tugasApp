package com.gigs.maher1957.Models.Alamat;

import com.google.gson.annotations.SerializedName;

public class ResponseAlamat{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}
}