package com.example.yaleimapp;


import java.util.Date;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MatchesFragment extends ListFragment{
	final Match[] matches = getMatches();
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.matches_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
       
	    
	    ListAdapter adapter = new MatchesAdapter(getActivity(), matches);
	    setListAdapter(adapter);  
	}
	
	//dummy encapsulated method that retrieves the matches list for the matches tab.
	public Match[] getMatches(){
		Match[] matches = {new Match(new ResidentialCollege("Berkeley", R.drawable.berkeley),
                new ResidentialCollege("SayBrook", R.drawable.saybrook),
                new Date(), "Soccer", "IM Fields"),
                
                new Match(new ResidentialCollege("Erza Stiles", R.drawable.erzastiles),
                new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards),
                new Date(), "Football", "IM Fields"),

               new Match(new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards),
               new ResidentialCollege("Trumbull", R.drawable.trumbull),
               new Date(), "Football", "IM Fields"),

               new Match(new ResidentialCollege("Calhoun", R.drawable.calhoun),
               new ResidentialCollege("Silliman", R.drawable.silliman),
               new Date(), "Football", "IM Fields"),
               
               new Match(new ResidentialCollege("Branford", R.drawable.branford),
               new ResidentialCollege("berkeley", R.drawable.berkeley),
               new Date(), "Football", "IM Fields")};
		
		return matches;
	}
	
}