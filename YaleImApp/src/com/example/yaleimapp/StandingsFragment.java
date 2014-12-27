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
	    
	    //list of the 12 residential college objects.
	    final ResidentialCollege[] residentialColleges = {(new ResidentialCollege("Berkeley", R.drawable.berkeley)),
	    		(new ResidentialCollege("Branford", R.drawable.branford)), (new ResidentialCollege("Calhoun", R.drawable.calhoun)),
	    		(new ResidentialCollege("Davenport", R.drawable.davenport)), (new ResidentialCollege("Erza Stiles", R.drawable.erzastiles)),
	    		(new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards)), 
	    		(new ResidentialCollege("Morse", R.drawable.morse)), (new ResidentialCollege("Pierson", R.drawable.pierson)), 
	    	    (new ResidentialCollege("Saybrook", R.drawable.saybrook)), (new ResidentialCollege("Silliman", R.drawable.silliman)),
	    	    (new ResidentialCollege("Timothy Dwight", R.drawable.timothydwight)), (new ResidentialCollege("Trumbull", R.drawable.trumbull))};
      
        ListAdapter adapter = new StandingsAdapter(getActivity(), residentialColleges);
	    setListAdapter(adapter);  
	}
	
	//TO DO, Make an intent for an Activity that displays a particular College Page
	public void onListItemClick(ListView l, View v, int position, long id) {
		//provide a page for viewing various information about a particular college
	}
	
}
