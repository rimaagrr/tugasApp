package com.gigs.maher1957.Models.KategoriDetail;

import com.google.gson.annotations.SerializedName;

public class ResponseKategoriDetail{

	@SerializedName("data")
	private DataKategoriDetail dataKategoriDetail;

	@SerializedName("message")
	private String message;

	public void setDataKategoriDetail(DataKategoriDetail dataKategoriDetail){
		this.dataKategoriDetail = dataKategoriDetail;
	}

	public DataKategoriDetail getDataKategoriDetail(){
		return dataKategoriDetail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}