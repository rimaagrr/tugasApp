package com.gigs.maher1957.Models.Cart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetCart{

	@SerializedName("Items in Cart")
	private List<ItemsInCartItem> itemsInCart;

	@SerializedName("cart Id atau = cartToken")
	private String cartIdAtauCartToken;

	public void setItemsInCart(List<ItemsInCartItem> itemsInCart){
		this.itemsInCart = itemsInCart;
	}

	public List<ItemsInCartItem> getItemsInCart(){
		return itemsInCart;
	}

	public void setCartIdAtauCartToken(String cartIdAtauCartToken){
		this.cartIdAtauCartToken = cartIdAtauCartToken;
	}

	public String getCartIdAtauCartToken(){
		return cartIdAtauCartToken;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetCart{" + 
			"items in Cart = '" + itemsInCart + '\'' + 
			",cart Id atau = cartToken = '" + cartIdAtauCartToken + '\'' + 
			"}";
		}
}