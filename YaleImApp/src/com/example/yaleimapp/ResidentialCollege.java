package com.example.yaleimapp;

public class ResidentialCollege {
	private String name;
	private int imgResource;
	private int tyngScore;
	
	public ResidentialCollege(String n, int imgRes, int score){
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
	
	public int getScore(){
		return tyngScore;
	}
}
