package com.example.yaleimapp;



public class Team {
	private ResidentialCollege college;
	private String sport;
	private String captainEmail;
	private int wins;
	private int losses;
	
	public Team(ResidentialCollege col, String spt, String captEmail, int w, int l){
		college = col;
		sport = spt;
		captainEmail = captEmail;
		wins = w;
		losses = l;
	}
	
	public ResidentialCollege getCollege(){
		return college;
	}
	
	public String getSport(){
		return sport;
	}
	
	public String getCaptainEmail(){
		return captainEmail;
	}
	
	public int getWins(){
		return wins;
	}
	
	public int getLosses(){
		return losses;
	}

}
