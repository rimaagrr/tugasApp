package com.gigs.maher1957.Models.Alamat.KotKab;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKotKabID {

	@SerializedName("data")
	private List<DataKotKabID> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataKotKabID> data){
		this.data = data;
	}

	public List<DataKotKabID> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}