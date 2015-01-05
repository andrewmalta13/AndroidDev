package com.example.yaleimapp;
import java.util.Calendar;
import java.util.Date;

public class Match{
	private ResidentialCollege team1;
	private ResidentialCollege team2;
	private Calendar date;
	private String sport;
	private String location;  //possibly can switch this out to a google maps location and link incorporate
							  //a get directions capability.
	
	public Match(ResidentialCollege teamOne, ResidentialCollege teamTwo, Calendar d, String spt, String loc){
		team1 = teamOne;
		team2 = teamTwo;
		date = d;
		sport = spt;
		location = loc;
	}
	
	public ResidentialCollege getTeam1(){
		return team1;
	}
	
	public ResidentialCollege getTeam2(){
		return team2;
	}
	
	public Calendar getDate(){
		return date;
	}
	
	public String getSport(){
		return sport;
	}
	
	public String getLocation(){
		return location;
	}
}
