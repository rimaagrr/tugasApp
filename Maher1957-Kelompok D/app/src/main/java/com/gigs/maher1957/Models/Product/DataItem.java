package com.gigs.maher1957.Models.Product;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("product_images")
	private List<ProductImagesItem> productImages;

	@SerializedName("product_tags")
	private List<ProductTagsItem> productTags;

	@SerializedName("hit")
	private String hit;

	@SerializedName("product_category_products")
	private List<ProductCategoryProductsItem> productCategoryProducts;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("product_sizes")
	private List<ProductSizesItem> productSizes;

	@SerializedName("id")
	private int id;

	@SerializedName("sku")
	private Object sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("img_hover")
	private String imgHover;

	@SerializedName("slug")
	private String slug;

	@SerializedName("height")
	private Object height;

	@SerializedName("price_result")
	private String priceResult;

	@SerializedName("weight")
	private String weight;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("lenght")
	private Object lenght;

	@SerializedName("id2")
	private String id2;

	@SerializedName("name")
	private String name;

	@SerializedName("width")
	private Object width;

	@SerializedName("selling")
	private int selling;

	@SerializedName("disc")
	private String disc;

	@SerializedName("short_desc")
	private Object shortDesc;

	@SerializedName("product_colors")
	private List<ProductColorsItem> productColors;

	@SerializedName("desc")
	private String desc;

	@SerializedName("status")
	private String status;

	@SerializedName("addedTocart")
	private boolean addedTocart = false;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setProductImages(List<ProductImagesItem> productImages){
		this.productImages = productImages;
	}

	public List<ProductImagesItem> getProductImages(){
		return productImages;
	}

	public void setProductTags(List<ProductTagsItem> productTags){
		this.productTags = productTags;
	}

	public List<ProductTagsItem> getProductTags(){
		return productTags;
	}

	public void setHit(String hit){
		this.hit = hit;
	}

	public String getHit(){
		return hit;
	}

	public void setProductCategoryProducts(List<ProductCategoryProductsItem> productCategoryProducts){
		this.productCategoryProducts = productCategoryProducts;
	}

	public List<ProductCategoryProductsItem> getProductCategoryProducts(){
		return productCategoryProducts;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setProductSizes(List<ProductSizesItem> productSizes){
		this.productSizes = productSizes;
	}

	public List<ProductSizesItem> getProductSizes(){
		return productSizes;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSku(Object sku){
		this.sku = sku;
	}

	public Object getSku(){
		return sku;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setImgHover(String imgHover){
		this.imgHover = imgHover;
	}

	public String getImgHover(){
		return imgHover;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setHeight(Object height){
		this.height = height;
	}

	public Object getHeight(){
		return height;
	}

	public void setPriceResult(String priceResult){
		this.priceResult = priceResult;
	}

	public String getPriceResult(){
		return priceResult;
	}

	public void setWeight(String weight){
		this.weight = weight;
	}

	public String getWeight(){
		return weight;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setLenght(Object lenght){
		this.lenght = lenght;
	}

	public Object getLenght(){
		return lenght;
	}

	public void setId2(String id2){
		this.id2 = id2;
	}

	public String getId2(){
		return id2;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setWidth(Object width){
		this.width = width;
	}

	public Object getWidth(){
		return width;
	}

	public void setSelling(int selling){
		this.selling = selling;
	}

	public int getSelling(){
		return selling;
	}

	public void setDisc(String disc){
		this.disc = disc;
	}

	public String getDisc(){
		return disc;
	}

	public void setShortDesc(Object shortDesc){
		this.shortDesc = shortDesc;
	}

	public Object getShortDesc(){
		return shortDesc;
	}

	public void setProductColors(List<ProductColorsItem> productColors){
		this.productColors = productColors;
	}

	public List<ProductColorsItem> getProductColors(){
		return productColors;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public boolean isAddedTocart() { return addedTocart; }

	public void setAddedTocart(boolean addedTocart) {
		this.addedTocart = addedTocart;
	}

}