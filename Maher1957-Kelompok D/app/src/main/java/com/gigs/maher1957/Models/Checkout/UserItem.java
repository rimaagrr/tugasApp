package com.gigs.maher1957.Models.Checkout;

import com.google.gson.annotations.SerializedName;

public class UserItem{

	@SerializedName("img_user")
	private String imgUser;

	@SerializedName("address")
	private String address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("kode_pos")
	private int kodePos;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("district_id")
	private int districtId;

	@SerializedName("slug")
	private String slug;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setImgUser(String imgUser){
		this.imgUser = imgUser;
	}

	public String getImgUser(){
		return imgUser;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setKodePos(int kodePos){
		this.kodePos = kodePos;
	}

	public int getKodePos(){
		return kodePos;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDistrictId(int districtId){
		this.districtId = districtId;
	}

	public int getDistrictId(){
		return districtId;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}