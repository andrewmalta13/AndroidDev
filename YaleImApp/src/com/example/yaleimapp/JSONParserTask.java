package com.example.yaleimapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParserTask{
	InputStream inputStream = null;
	String result = "";
	private String url = "http://yale-im.appspot.com";

	public ResidentialCollege[] generateColleges(){
	    String json = getJSON("");
	    try {
	      JSONObject jObject = new JSONObject();
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
	
	  public String getJSON(String url){
		  try{
		      HttpClient httpclient = new DefaultHttpClient();
		      
		      HttpGet request = new HttpGet();
		      URI website = new URI("http://yale-im.appspot.com");
		      request.setURI(website);
		      HttpResponse response = httpclient.execute(request);
		      
		      //TODO continue the json fetch from the site. 
		  }
		  
		  return result;
	  }
}
