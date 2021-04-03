package com.gigs.maher1957.Models.Kategori;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKategori{

	@SerializedName("data")
	private List<DataKategori> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataKategori> data){
		this.data = data;
	}

	public List<DataKategori> getData(){
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