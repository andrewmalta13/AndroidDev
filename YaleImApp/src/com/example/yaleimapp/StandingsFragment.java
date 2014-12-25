package com.example.yaleimapp;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StandingsFragment extends ListFragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.standings_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    final String[] residentialColleges = {"Berkeley", "Branford", "Calhoun", "Davenport", "Erza Stiles",
				"Johnathan Edwards", "Morse", "Pierson", "Saybrook", "Silliman",
				"Timothy Dwight", "Trumbull"}; //string list for residential college names
      
        ListAdapter adapter = new StandingsAdapter(getActivity(), residentialColleges);
	    setListAdapter(adapter);  
	}
	
	//TO DO, Make an intent for an Activity that displays a particular College Page
	public void onListItemClick(ListView l, View v, int position, long id) {
		//provide a page for viewing various information about a particular college
	}
	
}
