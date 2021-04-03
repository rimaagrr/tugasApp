package com.gigs.maher1957.Models.Alamat.District;

import com.google.gson.annotations.SerializedName;

public class ResponseDistrict{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}
}