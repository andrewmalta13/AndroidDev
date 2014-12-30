package com.example.yaleimapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StandingsAdapter extends ArrayAdapter<Object>{

	public StandingsAdapter(Context context, ResidentialCollege[] colleges) {
		super(context, R.layout.row_layout, colleges);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.row_layout, parent, false);
		
		ResidentialCollege college = (ResidentialCollege) getItem(position);
		TextView collegeView = (TextView) view.findViewById(R.id.standingsTextView);
		collegeView.setText(getDisplayName(college.getName()));
		
		TextView scoreView = (TextView) view.findViewById(R.id.tyngScore);
		scoreView.setText("" + college.getScore());
		
		
		ImageView imageView = (ImageView) view.findViewById(R.id.resCollegeImage);
		imageView.setImageResource(college.getImgResource());
		
		return view;
		
	}
    
	//generate a string to display from a given residential college name to avoid
	//long names messing up the display. E.G Johnathan Edwards
	private String getDisplayName(String name) {
		if(name.length() > 10){
			return (name.substring(0, 7).toUpperCase() + " ...");
		}
		
		else return name.toUpperCase();
		
	}
	

}
