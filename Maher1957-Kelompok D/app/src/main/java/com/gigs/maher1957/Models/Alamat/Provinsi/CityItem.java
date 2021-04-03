package com.gigs.maher1957.Models.Alamat.Provinsi;

import com.google.gson.annotations.SerializedName;

public class CityItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("province_id")
	private int provinceId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("postal_code")
	private String postalCode;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getProvinceId(){
		return provinceId;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public String getPostalCode(){
		return postalCode;
	}
}