package com.example.yaleimapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamsAdapter extends ArrayAdapter<Team>{

	public TeamsAdapter(Context context, ArrayList<Team> teams) {
		super(context,R.layout.teams_row_layout, teams);
		// TODO Auto-generated constructor stub
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.teams_row_layout, parent, false);
		
		Team team = (Team) getItem(position);
		
		//residential College Image
		ImageView collegeView = (ImageView) view.findViewById(R.id.team_college_image);
		collegeView.setImageResource(team.getCollege().getImgResource());
		
		//the text for the sport
		TextView sportText = (TextView) view.findViewById(R.id.team_sport_text);
		sportText.setText(team.getSport());
	
		//the text for the team's wins. 
		TextView wins = (TextView) view.findViewById(R.id.team_wins_text);
		wins.setTextColor(Color.GREEN);
		wins.setText("" + team.getWins());
		
		//hyphen between wins and losses
		TextView hyphen = (TextView) view.findViewById(R.id.hyphen);
		hyphen.setText(R.string.hyphen);
		
		//the text for the team's losses.
		TextView losses = (TextView) view.findViewById(R.id.team_losses_text);
		losses.setTextColor(Color.RED);
		losses.setText("" + team.getLosses());
	
		return view;
	}
	
	public void updateTeams(ArrayList<Team> newTeamList) {
		this.clear();
	    for(Team t : newTeamList){
	    	this.add(t);
	    }
	    this.notifyDataSetChanged();
	}
}
