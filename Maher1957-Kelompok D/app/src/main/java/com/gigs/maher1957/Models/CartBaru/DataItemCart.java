package com.gigs.maher1957.Models.CartBaru;

import com.google.gson.annotations.SerializedName;

public class DataItemCart {

	@SerializedName("color")
	private String color;

	@SerializedName("weight")
	private String weight;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("product_img_hover")
	private String productImgHover;

	@SerializedName("cart_id")
	private String cartId;

	@SerializedName("size")
	private String size;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("price")
	private String price;

	@SerializedName("product_id")
	private int productId;

	@SerializedName("qty")
	private int qty;

	@SerializedName("id")
	private int id;

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductImgHover(String productImgHover){
		this.productImgHover = productImgHover;
	}

	public String getProductImgHover(){
		return productImgHover;
	}

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public int getProductId(){
		return productId;
	}

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}