package com.example.yaleimapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StandingsAdapter extends ArrayAdapter<String>{
	
	private final int[] imageNames = {R.drawable.berkeley, R.drawable.branford, R.drawable.calhoun, R.drawable.davenport,
			R.drawable.erzastiles, R.drawable.johnathanedwards, R.drawable.morse, R.drawable.pierson, R.drawable.saybrook,
			R.drawable.silliman, R.drawable.timothydwight, R.drawable.trumbull};

	public StandingsAdapter(Context context, String[] colleges) {
		super(context, R.layout.row_layout, colleges);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.row_layout, parent, false);
		
		String college = getItem(position);
		TextView collegeView = (TextView) view.findViewById(R.id.standingsTextView);
		collegeView.setText(college);
		
		TextView scoreView = (TextView) view.findViewById(R.id.tyngScore);
		scoreView.setText(getScore(college));
		
		
		ImageView imageView = (ImageView) view.findViewById(R.id.resCollegeImage);
		imageView.setImageResource(imageNames[position]);
		
		return view;
		
	}
    
	//dummy getScore module for now. Will eventually be parsing JSON from a site to get
	//a given college's score.
	private String getScore(String college) {
		int score = college.length() * 15;
		return "" + score;
	}	

}
