package com.example.yaleimapp;


import java.util.ArrayList;
import java.util.Calendar;


import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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
	
	//parses json from yale-im.appspot.com/matches.json to retrieve a match list.
	public void generateMatches(String json){
		ArrayList<Match> matchList = new ArrayList<Match>();
		try{
		    JSONObject jObject = new JSONObject(json);
            JSONArray matchesArray = jObject.getJSONArray("matches");
        
            for(int i = 0; i < matchesArray.length(); i++){
                JSONObject match = (JSONObject) matchesArray.get(i);
                JSONObject date = (JSONObject) match.get("date");
            
                Calendar d = Calendar.getInstance();
                d.set(Integer.parseInt(date.getString("year")),
                	  Integer.parseInt(date.getString("month")) - 1, //to adjust for the zero based month numbering in calendar
                	  Integer.parseInt(date.getString("day")),
                	  Integer.parseInt(date.getString("hour")),
                	  Integer.parseInt(date.getString("minutes")));
            
                String team1 = match.getString("team1");
                String team2 = match.getString("team2");
                
                //using dummy values for score since it is not needed here.
                ResidentialCollege college1 = new ResidentialCollege(team1,((MainActivity) getActivity()).getDrawableRes(team1), (double) 0); 
                ResidentialCollege college2 = new ResidentialCollege(team2,((MainActivity) getActivity()).getDrawableRes(team2), (double) 0); 
            
                String sport = match.getString("sport");
                String location = match.getString("location");
             
                matchList.add((new Match(college1, college2, d, sport, location)));
            }
            	
                matches = matchList;
        	    adapter.updateMatches(matchList);
        	    
        	    
        }
		
		catch(Exception e){
			Log.e("json parsing", "error parsing json" + e.toString());
			Toast.makeText(getActivity(), "Error loading matches! Check Connection", Toast.LENGTH_LONG).show();
		}
        
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Spinner spinner = (Spinner) parent;
		String itemSelected = (String) parent.getItemAtPosition(position);
		ArrayList<Match> filteredMatches = new ArrayList<Match>();
		
		//input event is a filter to the colleges, apply the time filter first to the list of matches
		//then perform the college filter on the resultant matches list.
		if(spinner.getId() == R.id.matches_college_spinner){
			Spinner timeFilterSpinner = ((Spinner) getView().findViewById(R.id.matches_time_spinner));
			String filter = (String)timeFilterSpinner.getItemAtPosition(timeFilterSpinner.getSelectedItemPosition());
			filteredMatches = filterMatchesByDate(filter, matches);
			
			Log.d("match length", "after sorting by " + filter + " by Date, is " + filteredMatches.size());
			
			filteredMatches = filterMatchesByCollege(formatCollegeInput(itemSelected), filteredMatches);
			Log.d("match length", "after sorting by " + itemSelected + " by College, is " + filteredMatches.size());
		}
		
		//input event is a filter to the date, apply the time filter first to the list of matches
		//then perform the college filter on the resultant matches list.
		else if(spinner.getId() == R.id.matches_time_spinner){
			Spinner collegeFilterSpinner = ((Spinner) getView().findViewById(R.id.matches_college_spinner));
			String filter = (String)collegeFilterSpinner.getItemAtPosition(collegeFilterSpinner.getSelectedItemPosition());
			filteredMatches = filterMatchesByCollege(formatCollegeInput(filter), matches);
			Log.d("match length", "after sorting by " + filter + " by College, is " + filteredMatches.size());

			filteredMatches = filterMatchesByDate(itemSelected, filteredMatches);
			Log.d("match length", "after sorting by " + itemSelected + " by Date, is " + filteredMatches.size());
		}
		
		adapter.updateMatches(filteredMatches); //update the matches list to be displayed according to the filters.
	}
    
	
	private ArrayList<Match> filterMatchesByDate(String timeFilter, ArrayList<Match> listToFilter) {
		ArrayList<Match> filteredMatches = new ArrayList<Match>();
		Calendar now = Calendar.getInstance();
		
		if(timeFilter.equals("Today")){
			for(Match match : listToFilter){
				if((now.get(Calendar.YEAR) == match.getDate().get(Calendar.YEAR)) &&
		                  (now.get(Calendar.DAY_OF_YEAR) == match.getDate().get(Calendar.DAY_OF_YEAR))){
					filteredMatches.add(match);
				}
			}
		}
		else if(timeFilter.equals("This Week")){
			for(Match match : listToFilter){
				if((now.get(Calendar.YEAR) == match.getDate().get(Calendar.YEAR)) &&
						(now.get(Calendar.WEEK_OF_YEAR) == match.getDate().get(Calendar.WEEK_OF_YEAR))){
					filteredMatches.add(match);
				}
			}
		}
		else if(timeFilter.equals("This Month")){
			for(Match match : listToFilter){
				if((now.get(Calendar.YEAR) == match.getDate().get(Calendar.YEAR)) &&
						(now.get(Calendar.MONTH) == match.getDate().get(Calendar.MONTH))){
					filteredMatches.add(match);
				}
			}
		}
		
		else if(timeFilter.equals("Anytime")){
			filteredMatches = listToFilter;
		}
		
		return filteredMatches;
	}
    
	//update the match list to include only the matches involving the desired team.
	private ArrayList<Match> filterMatchesByCollege(String formatedCollegeName, ArrayList<Match> listToFilter) {
		ArrayList<Match> filteredMatches = new ArrayList<Match>();
		
		if(formatedCollegeName.equals("allcolleges")){
			filteredMatches = listToFilter;
		}
		else{
			for(Match match : listToFilter){
			    if (match.getTeam1().getName().equals(formatedCollegeName) || match.getTeam2().getName().equals(formatedCollegeName)){
				    filteredMatches.add(match);
			   }
		    }
		}
		
		return filteredMatches;
	}

	//change a college name from the spinner to one that we can search the matches for
	//e.g formateCollegeName("Johnathan Edwards") ==> "johnathanedwards"
	@SuppressLint("DefaultLocale")
	private static String formatCollegeInput(String collegeName) {
	    String newName = collegeName.replaceAll(" ", ""); //remove all spaces.
	    return newName.toLowerCase();
	} 

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}
}