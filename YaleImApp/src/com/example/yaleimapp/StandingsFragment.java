package com.example.yaleimapp;

import java.util.ArrayList;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StandingsFragment extends ListFragment{
	
	//dummy initialization for the res college list. Still need to figure out how to
	//ensure the async task populates the list before the list adapter is called.
	
	private ArrayList<ResidentialCollege> residentialCollegeList = new ArrayList<ResidentialCollege>();
	private StandingsAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.standings_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
        
	    if(residentialCollegeList.isEmpty()){
            JSONParserTask parser = new JSONParserTask(this, "standings", "http://yale-im.appspot.com/scores.json");
            parser.execute();
	    }
	
        adapter = new StandingsAdapter(getActivity(), residentialCollegeList);
        setListAdapter(adapter);
	}
	
	//TO DO, Make an intent for an Activity that displays a particular College Page
	public void onListItemClick(ListView l, View v, int position, long id) {
		//provide a page for viewing various information about a particular college
	}
	
	
	public void generateColleges(String json){
	    try {
	        JSONObject jObject = new JSONObject(json);
	        JSONObject scores = jObject.getJSONObject("scores");
	      
	        ArrayList<ResidentialCollege> residentialColleges = new ArrayList<ResidentialCollege>();
	        residentialColleges.add(new ResidentialCollege("Berkeley", R.drawable.berkeley, scores.getDouble("berkeley")));
	        residentialColleges.add(new ResidentialCollege("Branford", R.drawable.branford, scores.getDouble("branford")));
	        residentialColleges.add(new ResidentialCollege("Calhoun", R.drawable.calhoun, scores.getDouble("calhoun")));
	        residentialColleges.add(new ResidentialCollege("Davenport", R.drawable.davenport, scores.getDouble("davenport")));
	        residentialColleges.add(new ResidentialCollege("Erza Stiles", R.drawable.erzastiles, scores.getDouble("erzastiles")));
	        residentialColleges.add(new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards, scores.getDouble("johnathanedwards"))); 
	        residentialColleges.add(new ResidentialCollege("Morse", R.drawable.morse, scores.getDouble("morse")));
	        residentialColleges.add(new ResidentialCollege("Pierson", R.drawable.pierson, scores.getDouble("pierson"))); 
	        residentialColleges.add(new ResidentialCollege("Saybrook", R.drawable.saybrook, scores.getDouble("saybrook")));
	        residentialColleges.add(new ResidentialCollege("Silliman", R.drawable.silliman, scores.getDouble("silliman")));
	        residentialColleges.add(new ResidentialCollege("Timothy Dwight", R.drawable.timothydwight, scores.getDouble("timothydwight")));
	        residentialColleges.add(new ResidentialCollege("Trumbull", R.drawable.trumbull, scores.getDouble("trumbull")));
	       
	      
	        //update the residential college list because the get json task is done.
	        residentialCollegeList = adapter.sortByScore(residentialColleges);
	    	adapter.updateStandings(residentialCollegeList);
	    	
	    	    
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	}

}

	

