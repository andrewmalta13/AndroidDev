package com.example.yaleimapp;


import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeamsFragment extends ListFragment implements OnItemSelectedListener{
	private ArrayList<Team> teams = new ArrayList<Team>();
	
	private TeamsAdapter adapter;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
        
		return inflater.inflate(R.layout.teams_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    
	    Spinner sportFilterSpinner = (Spinner) getView().findViewById(R.id.teams_sports_spinner);
	    ArrayAdapter<CharSequence> sportFilterAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.sports_array, android.R.layout.simple_spinner_item);
	    sportFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sportFilterSpinner.setAdapter(sportFilterAdapter);
	    sportFilterSpinner.setOnItemSelectedListener(this);
	  
      
	    Spinner collegeFilterSpinner = (Spinner) getView().findViewById(R.id.teams_college_spinner);
	    ArrayAdapter<CharSequence> collegeSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.colleges_array, android.R.layout.simple_spinner_item);
	    collegeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    collegeFilterSpinner.setAdapter(collegeSpinnerAdapter);
	    collegeFilterSpinner.setOnItemSelectedListener(this);
	    
	    
	   
	    if(teams.isEmpty()){
            JSONParserTask parser = new JSONParserTask(this, "teams", "http://yale-im.appspot.com/teams.json");
            parser.execute();
	    }
	    
	    adapter = new TeamsAdapter(getActivity(), teams);
	    setListAdapter(adapter);
	    
	}
	
	public void generateTeams(String json){
		ArrayList<Team> teamList = new ArrayList<Team>();
		
		try{
		    JSONObject jObject = new JSONObject(json);
            JSONArray teamsArray = jObject.getJSONArray("teams");
        
            for(int i = 0; i < teamsArray.length(); i++){
                JSONObject team = (JSONObject) teamsArray.get(i);
                
                String collegeName = team.getString("college");
                String sport = team.getString("sport");
                String email = team.getString("email");
                int wins = team.getInt("wins");
                int losses = team.getInt("losses");
                
                ResidentialCollege college = new ResidentialCollege(collegeName,
                		((MainActivity) getActivity()).getDrawableRes(collegeName), (double) 0);
           
             
                teamList.add((new Team(college, sport, email, wins, losses)));
            }
            	
                teams = teamList;
        	    adapter.updateTeams(teamList);
        	    
        	    
        }
		
		catch(Exception e){
			Log.e("json parsing", "error parsing json" + e.toString());
			Toast.makeText(getActivity(), "Error loading teams! Check Connection", Toast.LENGTH_LONG).show();
		}
        
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Spinner spinner = (Spinner) parent;
		String itemSelected = (String) parent.getItemAtPosition(position);
		ArrayList<Team> filteredTeams = new ArrayList<Team>();
		
		//input event is a filter to the colleges, apply the time filter first to the list of teams
		//then perform the college filter on the resultant teams list.
		if(spinner.getId() == R.id.teams_college_spinner){
			Spinner sportsFilterSpinner = ((Spinner) getView().findViewById(R.id.teams_sports_spinner));
			String filter = (String)sportsFilterSpinner.getItemAtPosition(sportsFilterSpinner.getSelectedItemPosition());
			filteredTeams = filterTeamsBySport(filter, teams);
			
			Log.d("team length", "after sorting by " + filter + " by Sport, is " + filteredTeams.size());
			
			filteredTeams = filterTeamsByCollege(formatCollegeInput(itemSelected), filteredTeams);
			Log.d("team length", "after sorting by " + itemSelected + " by College, is " + filteredTeams.size());
		}
		
		//input event is a filter to the date, apply the time filter first to the list of teams
		//then perform the college filter on the resultant teams list.
		else if(spinner.getId() == R.id.teams_sports_spinner){
			Spinner collegeFilterSpinner = ((Spinner) getView().findViewById(R.id.teams_college_spinner));
			String filter = (String)collegeFilterSpinner.getItemAtPosition(collegeFilterSpinner.getSelectedItemPosition());
			filteredTeams = filterTeamsByCollege(formatCollegeInput(filter), teams);
			
			Log.d("team length", "after sorting by " + filter + " by College, is " + filteredTeams.size());

			filteredTeams = filterTeamsBySport(itemSelected, filteredTeams);
			Log.d("team length", "after sorting by " + itemSelected + " by Sport, is " + filteredTeams.size());
		}
		
		adapter.updateTeams(filteredTeams); //update the teams list to be displayed according to the filters.
	}
	
	private ArrayList<Team> filterTeamsByCollege(String college, ArrayList<Team> teamsToFilter) {
		ArrayList<Team> filteredTeams = new ArrayList<Team>();
		
		Log.d("college filter", college);
		if(college.equals("allcolleges")){
			filteredTeams = teamsToFilter;
		}
		else{
			for(Team t : teamsToFilter){
				if(t.getCollege().getName().equals(college)) filteredTeams.add(t);
			}
		}
		
		return filteredTeams;
	}

	private ArrayList<Team> filterTeamsBySport(String sport, ArrayList<Team> teamsToFilter) {
	    ArrayList<Team> filteredTeams = new ArrayList<Team>();
	    
	    
	    Log.d("sport Filter", sport);
	    if(sport.equals("All Sports")){
	    	filteredTeams = teamsToFilter;
	    }
	    else{
	    	for(Team t : teamsToFilter){
	    		if(t.getSport().equals(sport)) filteredTeams.add(t);
	    	}
	    }
	    
	    Log.d("resultant sport list", "" + filteredTeams.size());
	    return filteredTeams;
	}
    
	
	
	//a method to remove spaces from a string and make it lowercase.
	//used to map college spinner strings to their drawable file names.
	@SuppressLint("DefaultLocale")
	private static String formatCollegeInput(String collegeName) {
	    String newName = collegeName.replaceAll(" ", ""); //remove all spaces.
	    return newName.toLowerCase();
	} 

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}