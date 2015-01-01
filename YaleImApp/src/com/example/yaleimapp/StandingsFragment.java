package com.example.yaleimapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StandingsFragment extends ListFragment{
	
	//dummy initialization for the res college list. Still need to figure out how to
	//ensure the async task populates the list before the list adapter is called.
	
	private ArrayList<ResidentialCollege> residentialCollegeList = new ArrayList<ResidentialCollege>();
	StandingsAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.standings_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
        
	    if(residentialCollegeList.isEmpty()){
            JSONParserTask parser = new JSONParserTask(this, "standings");
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
	        residentialColleges.add(new ResidentialCollege("Berkeley", R.drawable.berkeley, scores.getInt("berkeley")));
	        residentialColleges.add(new ResidentialCollege("Branford", R.drawable.branford, scores.getInt("branford")));
	        residentialColleges.add(new ResidentialCollege("Calhoun", R.drawable.calhoun, scores.getInt("calhoun")));
	        residentialColleges.add(new ResidentialCollege("Davenport", R.drawable.davenport, scores.getInt("davenport")));
	        residentialColleges.add(new ResidentialCollege("Erza Stiles", R.drawable.erzastiles, scores.getInt("erzastiles")));
	        residentialColleges.add(new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards, scores.getInt("johnathanedwards"))); 
	        residentialColleges.add(new ResidentialCollege("Morse", R.drawable.morse, scores.getInt("morse")));
	        residentialColleges.add(new ResidentialCollege("Pierson", R.drawable.pierson, scores.getInt("pierson"))); 
	        residentialColleges.add(new ResidentialCollege("Saybrook", R.drawable.saybrook, scores.getInt("saybrook")));
	        residentialColleges.add(new ResidentialCollege("Silliman", R.drawable.silliman, scores.getInt("silliman")));
	        residentialColleges.add(new ResidentialCollege("Timothy Dwight", R.drawable.timothydwight, scores.getInt("timothydwight")));
	        residentialColleges.add(new ResidentialCollege("Trumbull", R.drawable.trumbull, scores.getInt("trumbull")));
	       
	      
	        //check to see if the adapter has been initialized yet. If it has been, update the
	        //residential college list because the get json task is done.
	        if(adapter != null){
	    	    adapter.updateStandings(adapter.sortByScore(residentialColleges));
	        }
	        else{
	    	    residentialCollegeList = residentialColleges;
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    
	}

}

	

