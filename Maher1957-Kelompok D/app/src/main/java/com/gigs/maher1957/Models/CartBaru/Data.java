package com.gigs.maher1957.Models.CartBaru;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("dt_cartitems")
	private List<DataItemCart> dataItemCart;

	@SerializedName("count_cart")
	private int countCart;

	@SerializedName("subtotal")
	private int subtotal;

	@SerializedName("total_weight")
	private int totalWeight;

	public void setDtCartitems(List<DataItemCart> dataItemCart){
		this.dataItemCart = dataItemCart;
	}

	public List<DataItemCart> getDataItemCart(){
		return dataItemCart;
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

	public void setTotalWeight(int totalWeight){
		this.totalWeight = totalWeight;
	}

	public int getTotalWeight(){
		return totalWeight;
	}
}