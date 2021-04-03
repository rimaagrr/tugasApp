package com.gigs.maher1957.Models.Favorite;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetFav{

	@SerializedName("data")
	private List<DataGetFav> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataGetFav> data){
		this.data = data;
	}

	public List<DataGetFav> getData(){
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