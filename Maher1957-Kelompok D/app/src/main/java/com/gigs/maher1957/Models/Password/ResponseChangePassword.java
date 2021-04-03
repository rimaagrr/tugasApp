package com.gigs.maher1957.Models.Password;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseChangePassword{

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<Object> data){
		this.data = data;
	}

	public List<Object> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}