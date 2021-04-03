package com.gigs.maher1957.Models.Pengiriman;

import com.google.gson.annotations.SerializedName;

public class ResponsePengiriman{

	@SerializedName("data")
	private DataPengiriman dataPengiriman;

	@SerializedName("message")
	private String message;

	public void setDataPengiriman(DataPengiriman dataPengiriman){
		this.dataPengiriman = dataPengiriman;
	}

	public DataPengiriman getDataPengiriman(){
		return dataPengiriman;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}