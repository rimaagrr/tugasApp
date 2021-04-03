package com.gigs.maher1957.Models.Product;

import com.google.gson.annotations.SerializedName;

public class Pivot{

	@SerializedName("category_color_id")
	private String categoryColorId;

	@SerializedName("product_id")
	private String productId;

	public void setCategoryColorId(String categoryColorId){
		this.categoryColorId = categoryColorId;
	}

	public String getCategoryColorId(){
		return categoryColorId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}
}