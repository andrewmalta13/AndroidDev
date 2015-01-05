package com.example.yaleimapp;

public class ResidentialCollege {
	private String name;
	private int imgResource;
	private Double tyngScore;
	
	public ResidentialCollege(String n, int imgRes, Double score){
		name = n;
		imgResource = imgRes;
		tyngScore = score;
	}
	
	public String getName(){
		return name;
	}
	
	public int getImgResource(){
		return imgResource;
	}
	
	public Double getScore(){
		return tyngScore;
	}
}
