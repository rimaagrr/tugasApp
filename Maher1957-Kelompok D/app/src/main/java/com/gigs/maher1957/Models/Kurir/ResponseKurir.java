package com.gigs.maher1957.Models.Kurir;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKurir{

	@SerializedName("data")
	private List<DataKurir> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataKurir> data){
		this.data = data;
	}

	public List<DataKurir> getData(){
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