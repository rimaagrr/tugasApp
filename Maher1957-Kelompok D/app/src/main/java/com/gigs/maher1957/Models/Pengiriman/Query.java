package com.gigs.maher1957.Models.Pengiriman;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("originType")
	private String originType;

	@SerializedName("courier")
	private String courier;

	@SerializedName("origin")
	private String origin;

	@SerializedName("destination")
	private String destination;

	@SerializedName("destinationType")
	private String destinationType;

	@SerializedName("weight")
	private int weight;

	public void setOriginType(String originType){
		this.originType = originType;
	}

	public String getOriginType(){
		return originType;
	}

	public void setCourier(String courier){
		this.courier = courier;
	}

	public String getCourier(){
		return courier;
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

	public void setDestinationType(String destinationType){
		this.destinationType = destinationType;
	}

	public String getDestinationType(){
		return destinationType;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}
}