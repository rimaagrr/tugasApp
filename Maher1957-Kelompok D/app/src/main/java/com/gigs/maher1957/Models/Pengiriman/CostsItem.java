package com.gigs.maher1957.Models.Pengiriman;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CostsItem{

	@SerializedName("cost")
	private List<CostItem> cost;

	@SerializedName("service")
	private String service;

	@SerializedName("description")
	private String description;

	public void setCost(List<CostItem> cost){
		this.cost = cost;
	}

	public List<CostItem> getCost(){
		return cost;
	}

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return service;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}
}