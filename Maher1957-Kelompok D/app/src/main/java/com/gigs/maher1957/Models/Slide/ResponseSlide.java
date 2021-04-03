package com.gigs.maher1957.Models.Slide;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSlide{

	@SerializedName("data")
	private List<DataSlide> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataSlide> data){
		this.data = data;
	}

	public List<DataSlide> getData(){
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