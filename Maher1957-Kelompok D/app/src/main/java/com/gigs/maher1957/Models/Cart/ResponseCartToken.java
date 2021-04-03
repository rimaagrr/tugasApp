package com.gigs.maher1957.Models.Cart;

import com.google.gson.annotations.SerializedName;

public class ResponseCartToken {

	@SerializedName("Message")
	private String message;

	@SerializedName("cartKey")
	private String cartKey;

	@SerializedName("cartToken")
	private String cartToken;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCartKey(String cartKey){
		this.cartKey = cartKey;
	}

	public String getCartKey(){
		return cartKey;
	}

	public void setCartToken(String cartToken){
		this.cartToken = cartToken;
	}

	public String getCartToken(){
		return cartToken;
	}
}