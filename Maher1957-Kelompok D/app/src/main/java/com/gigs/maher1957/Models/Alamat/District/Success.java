package com.gigs.maher1957.Models.Alamat.District;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("det")
	private List<DetItem> det;

	@SerializedName("user")
	private User user;

	public void setDet(List<DetItem> det){
		this.det = det;
	}

	public List<DetItem> getDet(){
		return det;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
}