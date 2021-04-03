package com.gigs.maher1957.Models.CartV2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataGetCart {

	@SerializedName("dt_cartitems")
	private List<DtCartitemsItem> dtCartitems;

	@SerializedName("count_cart")
	private int countCart;

	@SerializedName("subtotal")
	private int subtotal;

	@SerializedName("total_weight")
	private int totalWeight;

	public void setDtCartitems(List<DtCartitemsItem> dtCartitems){
		this.dtCartitems = dtCartitems;
	}

	public List<DtCartitemsItem> getDtCartitems(){
		return dtCartitems;
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