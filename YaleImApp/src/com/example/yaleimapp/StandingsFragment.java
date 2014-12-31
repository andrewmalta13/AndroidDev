package com.example.yaleimapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

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
import android.widget.ListAdapter;
import android.widget.ListView;

public class StandingsFragment extends ListFragment{
	
	//dummy initialization for the res college list. Still need to figure out how to
	//ensure the async task populates the list before the list adapter is called.
	
	private ResidentialCollege [] residentialCollegeList = {(new ResidentialCollege("Trumbull", R.drawable.trumbull, 1912))};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.standings_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

        JSONParserTask parser = new JSONParserTask(this, "standings");
        parser.execute();
        
        ListAdapter adapter = new StandingsAdapter(getActivity(), residentialCollegeList);
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
	      
	      ResidentialCollege[] residentialColleges = {
	          (new ResidentialCollege("Berkeley", R.drawable.berkeley, scores.getInt("berkeley"))),
	          (new ResidentialCollege("Branford", R.drawable.branford, scores.getInt("branford"))),
	          (new ResidentialCollege("Calhoun", R.drawable.calhoun, scores.getInt("calhoun"))),
	          (new ResidentialCollege("Davenport", R.drawable.davenport, scores.getInt("davenport"))),
	          (new ResidentialCollege("Erza Stiles", R.drawable.erzastiles, scores.getInt("erzastiles"))),
	          (new ResidentialCollege("Johnathan Edwards", R.drawable.johnathanedwards, scores.getInt("johnathanedwards"))), 
	          (new ResidentialCollege("Morse", R.drawable.morse, scores.getInt("morse"))),
	          (new ResidentialCollege("Pierson", R.drawable.pierson, scores.getInt("pierson"))), 
	          (new ResidentialCollege("Saybrook", R.drawable.saybrook, scores.getInt("saybrook"))),
	          (new ResidentialCollege("Silliman", R.drawable.silliman, scores.getInt("silliman"))),
	          (new ResidentialCollege("Timothy Dwight", R.drawable.timothydwight, scores.getInt("timothydwight"))),
	          (new ResidentialCollege("Trumbull", R.drawable.trumbull, scores.getInt("trumbull")))};
	       
	       residentialCollegeList = residentialColleges;
	      
	    } catch (JSONException e) {
	      e.printStackTrace();
	    }
	    
	}
	
	
}

	

