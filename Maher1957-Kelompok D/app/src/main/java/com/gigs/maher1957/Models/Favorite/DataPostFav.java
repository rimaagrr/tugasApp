package com.gigs.maher1957.Models.Favorite;

import com.google.gson.annotations.SerializedName;

public class DataPostFav {

	@SerializedName("user_id")
	private String userId;

	@SerializedName("product_id")
	private String productId;

	public String getUserId(){
		return userId;
	}

	public String getProductId(){
		return productId;
	}
}