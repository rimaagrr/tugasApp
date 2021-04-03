package com.gigs.maher1957.Models.KategoriDetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataKategoriDetail {

	@SerializedName("result_category")
	private List<ResultCategoryItem> resultCategory;

	public void setResultCategory(List<ResultCategoryItem> resultCategory){
		this.resultCategory = resultCategory;
	}

	public List<ResultCategoryItem> getResultCategory(){
		return resultCategory;
	}
}