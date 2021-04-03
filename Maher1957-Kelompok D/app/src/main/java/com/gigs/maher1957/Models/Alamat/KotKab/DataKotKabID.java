package com.gigs.maher1957.Models.Alamat.KotKab;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataKotKabID {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("province_id")
	private int provinceId;

	@SerializedName("name")
	private String name;

	@SerializedName("kec")
	private List<KecItem> kec;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("postal_code")
	private String postalCode;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setProvinceId(int provinceId){
		this.provinceId = provinceId;
	}

	public int getProvinceId(){
		return provinceId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setKec(List<KecItem> kec){
		this.kec = kec;
	}

	public List<KecItem> getKec(){
		return kec;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}

	public String getPostalCode(){
		return postalCode;
	}
}