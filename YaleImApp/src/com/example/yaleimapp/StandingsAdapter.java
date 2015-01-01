package com.example.yaleimapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StandingsAdapter extends ArrayAdapter<ResidentialCollege>{
	
	public StandingsAdapter(Context context, ArrayList<ResidentialCollege> colleges) {
		super(context, R.layout.row_layout, colleges);
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
	
	public void updateStandings(ArrayList<ResidentialCollege> newResList) {
	    this.clear();
	    for(ResidentialCollege res : newResList){
	    	this.add(res);
	    }
	    this.notifyDataSetChanged();
	}
	
	//perform a simple insertion sort(only 12 elements, so should be fast enough)
	public ArrayList<ResidentialCollege> sortByScore(ArrayList<ResidentialCollege> listToSort){
		ArrayList<ResidentialCollege> sorted = new ArrayList<ResidentialCollege>();
		sorted.add(listToSort.get(0));
		listToSort.remove(0);
		
		for(ResidentialCollege college : listToSort){
			for(int i = 0; i < sorted.size(); i++){
				if(college.getScore() >= sorted.get(i).getScore()){
					sorted.add(i, college);
					Log.d("inserted:", college.getName() + " " + "at position " + i);
					break;
				}
				//add it to the end it has the lowest score seen so far.
				else if(i == (sorted.size() - 1)){
					sorted.add(college);
					Log.d("appended:", college.getName() + " " + "at position" + i);
					break;
				}
			}
		}
		return sorted;
	}
}
