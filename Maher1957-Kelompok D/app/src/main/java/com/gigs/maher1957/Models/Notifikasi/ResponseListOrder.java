package com.gigs.maher1957.Models.Notifikasi;

import com.google.gson.annotations.SerializedName;

public class ResponseListOrder{

	@SerializedName("data")
	private DataListOrder dataListOrder;

	public void setDataListOrder(DataListOrder dataListOrder){
		this.dataListOrder = dataListOrder;
	}

	public DataListOrder getDataListOrder(){
		return dataListOrder;
	}
}