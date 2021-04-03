package com.gigs.maher1957.Models.LoginRegister.Register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("success")
	private Success success;

	public void setSuccess(Success success){
		this.success = success;
	}

	public Success getSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegister{" + 
			"success = '" + success + '\'' + 
			"}";
		}
}