package com.gigs.maher1957.Models.Alamat.Provinsi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProvinsi{

	@SerializedName("data")
	private List<DataProvinsi> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataProvinsi> data){
		this.data = data;
	}

	public List<DataProvinsi> getData(){ return data; }

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