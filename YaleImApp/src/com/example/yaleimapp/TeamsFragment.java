package com.example.yaleimapp;


import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class TeamsFragment extends ListFragment implements OnItemSelectedListener{
	private ArrayList<Team> teams = new ArrayList<Team>();
	private TeamsAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.teams_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
      
	    Spinner collegeFilterSpinner = (Spinner) getView().findViewById(R.id.teams_college_spinner);
	    ArrayAdapter<CharSequence> collegeSpinnerAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.colleges_array, android.R.layout.simple_spinner_item);
	    collegeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    collegeFilterSpinner.setAdapter(collegeSpinnerAdapter);
	    collegeFilterSpinner.setOnItemSelectedListener(this);
	    
	    Spinner sportFilterSpinner = (Spinner) getView().findViewById(R.id.teams_sports_spinner);
	    ArrayAdapter<CharSequence> sportFilterAdapter = ArrayAdapter.createFromResource(this.getActivity(),
	    		R.array.sports_array, android.R.layout.simple_spinner_item);
	    sportFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sportFilterSpinner.setAdapter(sportFilterAdapter);
	    sportFilterSpinner.setOnItemSelectedListener(this);
	    
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
		}
        
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}