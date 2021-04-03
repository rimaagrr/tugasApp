package com.gigs.maher1957.Models.Alamat.Provinsi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataProvinsiID {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("city")
	private List<CityItem> city;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public List<CityItem> getCity(){
		return city;
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
}