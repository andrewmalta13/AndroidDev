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
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.matches_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
        final Match[] matches = {new Match(new ResidentialCollege("Berkeley", R.drawable.berkeley),
        		                           new ResidentialCollege("SayBrook", R.drawable.saybrook),
        		                           new Date(), "Soccer", "IM Fields"),
        		                 new Match(new ResidentialCollege("Erza Stiles", R.drawable.erzastiles),
      		                               new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards),
      		                               new Date(), "Soccer", "IM Fields") };
	    
	    ListAdapter adapter = new MatchesAdapter(getActivity(), matches);
	    setListAdapter(adapter);  
	}
	
}