package com.example.yaleimapp;


import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MatchesFragment extends ListFragment implements OnItemSelectedListener{
	private ArrayList<Match> matches = new ArrayList<Match>();
	private MatchesAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.matches_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    //code for initializing a spinner (drop down list) for filtering matches by college
	    Spinner collegeFilterSpinner = (Spinner) getView().findViewById(R.id.matches_college_spinner);
	    ArrayAdapter<CharSequence> collegeSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.colleges_array, android.R.layout.simple_spinner_item);
	    collegeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    collegeFilterSpinner.setAdapter(collegeSpinnerAdapter);
	    collegeFilterSpinner.setOnItemSelectedListener(this);
	    
	    Spinner timeFilterSpinner = (Spinner) getView().findViewById(R.id.matches_time_spinner);
	    ArrayAdapter<CharSequence> timeSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.time_filter_choices_array, android.R.layout.simple_spinner_item);
	    timeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    timeFilterSpinner.setAdapter(timeSpinnerAdapter);
	    timeFilterSpinner.setOnItemSelectedListener(this);
	    
	    if(matches.isEmpty()){
            JSONParserTask parser = new JSONParserTask(this, "matches", "http://yale-im.appspot.com/matches.json");
            parser.execute();
	    }
	    
	    adapter = new MatchesAdapter(getActivity(), matches);
	    setListAdapter(adapter);
	    
	}
	
	//dummy encapsulated method that retrieves the matches list for the matches tab.
	@SuppressWarnings("deprecation")
	public void generateMatches(String json){
		ArrayList<Match> matchList = new ArrayList<Match>();
		try{
		    JSONObject jObject = new JSONObject(json);
            JSONArray matchesArray = jObject.getJSONArray("matches");
        
            for(int i = 0; i < matchesArray.length(); i++){
                JSONObject match = (JSONObject) matchesArray.get(i);
                JSONObject date = (JSONObject) match.get("date");
            
                Date d = new Date();
                d.setMinutes(Integer.parseInt(date.getString("minutes")));
                d.setHours(Integer.parseInt(date.getString("hour")));
                d.setDate(Integer.parseInt(date.getString("day")));
                d.setMonth(Integer.parseInt(date.getString("month")));
            
                String team1 = match.getString("team1");
                String team2 = match.getString("team2");
                ResidentialCollege college1 = new ResidentialCollege(team1, getDrawableRes(team1), 0); //score field not used in this fragment.
                ResidentialCollege college2 = new ResidentialCollege(team2, getDrawableRes(team2), 0); //score field not used in this fragment.
            
                String sport = match.getString("sport");
                String location = match.getString("location");
             
                matchList.add((new Match(college1, college2, d, sport, location)));
            }
            	
                matches = matchList;
        	    adapter.updateMatches(matchList);
        	    
        	    
        }
		
		catch(Exception e){
			Log.e("json parsing", "error parsing json" + e.toString());
		}
        
	}
	
	//gets the integer mapped to the drawable resource to be displayed from a given string.
	//otherwise returns -1. 
	public int getDrawableRes(String name){
		Activity c = getActivity();
	    int resourceId = c.getApplicationContext().getResources().getIdentifier(name, "drawable", c.getPackageName());
	    if(resourceId == 0){
	        return -1;
	    } else {
	        return resourceId;
	    }
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Spinner spinner = (Spinner) parent;
		String itemSelected = (String) parent.getItemAtPosition(position);
		
		if(spinner.getId() == R.id.matches_college_spinner){
			Log.d("selected:", itemSelected + " Formatted: " + formatCollegeName(itemSelected));
			filterMacthesByCollege(formatCollegeName(itemSelected));
		}
		else if(spinner.getId() == R.id.matches_time_spinner){
			filterMatchesByDate(parent.getItemAtPosition(position).toString());
		}
	}
    
	
	//TODO, implement a filter by date function
	private void filterMatchesByDate(String itemAtPosition) {
		int i = 5; //dummy code 
	}
    
	
	//update the match list to include only the matches involving the desired team.
	private void filterMacthesByCollege(String formatedCollegeName) {
		ArrayList<Match> filteredMatches = new ArrayList<Match>();
		for(Match match : matches){
			if (match.getTeam1().getName().equals(formatedCollegeName) || match.getTeam2().getName().equals(formatedCollegeName)){
				filteredMatches.add(match);
			}
		}
		adapter.updateMatches(filteredMatches);
	}

	//change a college name from the spinner to one that we can search the matches for
	//e.g formateCollegeName("Johnathan Edwards") ==> "johnathanedwards"
	private String formatCollegeName(String collegeName) {
		String newName = collegeName.toLowerCase();
		newName.replaceAll(" ", ""); //eliminate spaces.
		return newName;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}
}