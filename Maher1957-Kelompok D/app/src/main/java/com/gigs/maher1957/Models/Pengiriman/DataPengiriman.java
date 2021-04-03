package com.gigs.maher1957.Models.Pengiriman;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataPengiriman {

	@SerializedName("dt_cartitems")
	private List<DtCartitemsItem> dtCartitems;

	@SerializedName("count_cart")
	private int countCart;

	@SerializedName("array_result")
	private ArrayResult arrayResult;

	@SerializedName("subtotal")
	private int subtotal;

	@SerializedName("origin")
	private String origin;

	@SerializedName("destination")
	private String destination;

	@SerializedName("weight")
	private int weight;

	@SerializedName("dest_id")
	private String destId;

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

	public void setArrayResult(ArrayResult arrayResult){
		this.arrayResult = arrayResult;
	}

	public ArrayResult getArrayResult(){
		return arrayResult;
	}

	public void setSubtotal(int subtotal){
		this.subtotal = subtotal;
	}

	public int getSubtotal(){
		return subtotal;
	}

	public void setOrigin(String origin){
		this.origin = origin;
	}

	public String getOrigin(){
		return origin;
	}

	public void setDestination(String destination){
		this.destination = destination;
	}

	public String getDestination(){
		return destination;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}

	public void setDestId(String destId){
		this.destId = destId;
	}

	public String getDestId(){
		return destId;
	}
}