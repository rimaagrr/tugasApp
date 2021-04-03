package com.gigs.maher1957.Models.Alamat.Provinsi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProvinsiID{

	@SerializedName("data")
	private List<DataProvinsiID> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public List<DataProvinsiID> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}