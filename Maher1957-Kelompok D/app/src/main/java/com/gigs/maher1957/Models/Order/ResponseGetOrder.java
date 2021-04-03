package com.gigs.maher1957.Models.Order;

import com.google.gson.annotations.SerializedName;

public class ResponseGetOrder{

	@SerializedName("data")
	private DataOrder dataOrder;

	@SerializedName("message")
	private String message;

	public void setDataOrder(DataOrder dataOrder){
		this.dataOrder = dataOrder;
	}

	public DataOrder getDataOrder(){
		return dataOrder;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}