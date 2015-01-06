package com.example.yaleimapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchesAdapter extends ArrayAdapter<Match>{

	public MatchesAdapter(Context context, ArrayList<Match> matches) {
		super(context, R.layout.matches_row_layout, matches);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.matches_row_layout, parent, false);
		
		Match match = (Match) getItem(position);
		
		//first residential college image
		ImageView team1IV = (ImageView) view.findViewById(R.id.team1image);
		team1IV.setImageResource(match.getTeam1().getImgResource());
		
		//text for Vs. TODO just add this string in the XML
		TextView versusText = (TextView) view.findViewById(R.id.versus_text);
		versusText.setText("Vs.");
		
		//second residential college image
		ImageView team2IV = (ImageView) view.findViewById(R.id.team2image);
		team2IV.setImageResource(match.getTeam2().getImgResource());
		
		TextView sportTV = (TextView) view.findViewById(R.id.sport_text);
		sportTV.setText(match.getSport());
		
		TextView dateTime = (TextView) view.findViewById(R.id.time_text);
		Calendar date = match.getDate();
        SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
		dateTime.setText(format.format(date.getTime()));
		
		return view;
		
	}

	public void updateMatches(ArrayList<Match> newMatchList) {
		this.clear();
	    for(Match m : newMatchList){
	    	this.add(m);
	    }
	    this.notifyDataSetChanged();
	}
}
