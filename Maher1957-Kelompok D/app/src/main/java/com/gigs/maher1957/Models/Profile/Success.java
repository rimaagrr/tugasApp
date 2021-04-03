package com.gigs.maher1957.Models.Profile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("det")
	private List<Object> det;

	@SerializedName("user")
	private UserProfile userProfile;

	public void setDet(List<Object> det){
		this.det = det;
	}

	public List<Object> getDet(){
		return det;
	}

	public void setUserProfile(UserProfile userProfile){
		this.userProfile = userProfile;
	}

	public UserProfile getUserProfile(){
		return userProfile;
	}
}