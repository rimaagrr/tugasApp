package com.gigs.maher1957.Models.Notifikasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataListOrder {

	@SerializedName("Orders")
	private List<OrdersItem> orders;

	@SerializedName("orders_count")
	private int ordersCount;

	public void setOrders(List<OrdersItem> orders){
		this.orders = orders;
	}

	public List<OrdersItem> getOrders(){
		return orders;
	}

	public void setOrdersCount(int ordersCount){
		this.ordersCount = ordersCount;
	}

	public int getOrdersCount(){
		return ordersCount;
	}
}