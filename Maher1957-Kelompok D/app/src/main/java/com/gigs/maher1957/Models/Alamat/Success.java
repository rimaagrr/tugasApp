package com.gigs.maher1957.Models.Alamat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Success implements Serializable {

	@SerializedName("address")
	private String address;

	@SerializedName("google_id")
	private Object googleId;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("kode_pos")
	private int kodePos;

	@SerializedName("email_verified_at")
	private String emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("district_id")
	private int districtId;

	@SerializedName("slug")
	private String slug;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public String getAddress(){ return address; }

	public void setPhone(String phone){ this.phone = phone; }

	public void setGoogleId(Object googleId){
		this.googleId = googleId;
	}

	public Object getGoogleId(){
		return googleId;
	}

	public void setAddress(String address){
		this.address = address;
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

	public void setEmailVerifiedAt(String emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public String getEmailVerifiedAt(){
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

	public void setKelurahan(String kelurahan){
		this.kelurahan = kelurahan;
	}

	public String getKelurahan(){
		return kelurahan;
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