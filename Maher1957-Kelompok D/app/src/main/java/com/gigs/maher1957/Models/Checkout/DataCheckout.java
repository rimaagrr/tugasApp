package com.gigs.maher1957.Models.Checkout;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataCheckout {

	@SerializedName("dt_cartitems")
	private List<DtCartitems> dtCartitems;

	@SerializedName("dt_courier")
	private DtCourier dtCourier;

	@SerializedName("count_cart")
	private int countCart;

	@SerializedName("subtotal")
	private int subtotal;

	@SerializedName("weight")
	private int weight;

	@SerializedName("user")
	private List<UserItem> user;

	public void setDtCartitems(List<DtCartitems> dtCartitems){
		this.dtCartitems = dtCartitems;
	}

	public List<DtCartitems> getDtCartitems(){
		return dtCartitems;
	}

	public void setDtCourier(DtCourier dtCourier){
		this.dtCourier = dtCourier;
	}

	public DtCourier getDtCourier(){
		return dtCourier;
	}

	public void setCountCart(int countCart){
		this.countCart = countCart;
	}

	public int getCountCart(){
		return countCart;
	}

	public void setSubtotal(int subtotal){
		this.subtotal = subtotal;
	}

	public int getSubtotal(){
		return subtotal;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}
}