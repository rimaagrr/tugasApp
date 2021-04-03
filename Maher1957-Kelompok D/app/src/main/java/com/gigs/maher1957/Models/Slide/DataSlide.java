package com.gigs.maher1957.Models.Slide;

import com.google.gson.annotations.SerializedName;

public class DataSlide {

	@SerializedName("img_slide")
	private String imgSlide;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("img_mobile")
	private String imgMobile;

	@SerializedName("link")
	private String link;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("slug")
	private String slug;

	@SerializedName("status")
	private String status;

	public void setImgSlide(String imgSlide){
		this.imgSlide = imgSlide;
	}

	public String getImgSlide(){
		return imgSlide;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setImgMobile(String imgMobile){
		this.imgMobile = imgMobile;
	}

	public String getImgMobile(){
		return imgMobile;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}