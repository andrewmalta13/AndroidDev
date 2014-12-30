package com.example.yaleimapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.standings_list_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    //list of the 12 residential college objects.
	    /*final ResidentialCollege[] residentialColleges = */
        ResidentialCollege[] residentialColleges = {(new ResidentialCollege("Branford", R.drawable.branford, 123))};
        
        if(getJSON() != null){
        	residentialColleges[0] = (new ResidentialCollege("Saybrook", R.drawable.saybrook, 123));
        }
        
        ListAdapter adapter = new StandingsAdapter(getActivity(), residentialColleges);
	    setListAdapter(adapter);  
	}
	
	//TO DO, Make an intent for an Activity that displays a particular College Page
	public void onListItemClick(ListView l, View v, int position, long id) {
		//provide a page for viewing various information about a particular college
	}
	
	public ResidentialCollege[] generateColleges(){
	    String json_string = getJSON();
	    
	    try {
	      JSONObject jObject = new JSONObject(json_string);
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
	       
	      return residentialColleges;
	      
	    } catch (JSONException e) {
	      e.printStackTrace();
	    }
	    
	    return null;
	  }
	  
	  public String getJSON(){
	    DefaultHttpClient  httpclient = new DefaultHttpClient();
	    HttpPost content = new HttpPost("http://yale-im.appspot.com");
	    
	    content.setHeader("Content-type", "application/json");
	    
	    InputStream inputStream = null;
	    String result = null;
	    try {
	        HttpResponse response = httpclient.execute(content);           
	        HttpEntity entity = response.getEntity();

	        inputStream = entity.getContent();
	        // json is UTF-8 by default
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	        StringBuilder sb = new StringBuilder();

	        String line = null;
	        while ((line = reader.readLine()) != null)
	        {
	            sb.append(line + "\n");
	        }
	        result = sb.toString();
	    } catch (Exception e) { 
	        Log.e("JSON parser", "error parsing JSON");
	    }
	    
	    finally {
	        try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	    }
	    
	    return result;
	  }
}

	

