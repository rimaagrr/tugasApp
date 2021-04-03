package com.gigs.maher1957.Models.Checkout;

import com.google.gson.annotations.SerializedName;

public class DtCourier{

	@SerializedName("jne")
	private String jne;

	@SerializedName("jnt")
	private String jnt;

	@SerializedName("pos")
	private String pos;

	@SerializedName("tiki")
	private String tiki;

	public void setJne(String jne){
		this.jne = jne;
	}

	public String getJne(){
		return jne;
	}

	public void setJnt(String jnt){
		this.jnt = jnt;
	}

	public String getJnt(){
		return jnt;
	}

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setTiki(String tiki){
		this.tiki = tiki;
	}

	public String getTiki(){
		return tiki;
	}
}