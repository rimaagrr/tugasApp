package com.gigs.maher1957.Models.Product;

import com.google.gson.annotations.SerializedName;

public class ProductImagesItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("product_img_name")
	private String productImgName;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setProductImgName(String productImgName){
		this.productImgName = productImgName;
	}

	public String getProductImgName(){
		return productImgName;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
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

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}
}