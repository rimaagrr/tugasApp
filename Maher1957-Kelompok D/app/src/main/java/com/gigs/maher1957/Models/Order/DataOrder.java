package com.gigs.maher1957.Models.Order;

import com.google.gson.annotations.SerializedName;

public class DataOrder {

	@SerializedName("order")
	private Order order;

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}
}