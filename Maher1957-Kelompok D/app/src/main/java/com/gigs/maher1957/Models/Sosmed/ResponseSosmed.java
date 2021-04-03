package com.gigs.maher1957.Models.Sosmed;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSosmed{

	@SerializedName("data")
	private List<DataSosmed> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataSosmed> data){
		this.data = data;
	}

	public List<DataSosmed> getData(){
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