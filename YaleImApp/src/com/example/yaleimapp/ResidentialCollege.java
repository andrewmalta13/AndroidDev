package com.example.yaleimapp;

public class ResidentialCollege {
	private String name;
	private int imgResource;
	
	public ResidentialCollege(String n, int imgRes){
		name = n;
		imgResource = imgRes;
	}
	
	public String getName(){
		return name;
	}
	
	public int getImgResource(){
		return imgResource;
	}
}
