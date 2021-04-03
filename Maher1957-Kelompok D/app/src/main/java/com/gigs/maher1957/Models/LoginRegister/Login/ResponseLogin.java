package com.gigs.maher1957.Models.LoginRegister.Login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("user")
	private User user;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}