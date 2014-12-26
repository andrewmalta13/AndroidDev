package com.example.yaleimapp;
import java.util.Date;

public class Match{
	private ResidentialCollege team1;
	private ResidentialCollege team2;
	private Date date;
	private String sport;
	private String location;  //possibly can switch this out to a google maps location and link incorporate
							  //a get directions capability.
	
	public Match(ResidentialCollege teamOne, ResidentialCollege teamTwo, Date d, String s, String loc){
		team1 = teamOne;
		team2 = teamTwo;
		date = d;
		sport = s;
		location = loc;
	}
	
	public ResidentialCollege getTeam1(){
		return team1;
	}
	
	public ResidentialCollege getTeam2(){
		return team2;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String getSport(){
		return sport;
	}
	
	public String getLocation(){
		return location;
	}
}
