package com.example.yaleimapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;

public class JSONParserTask extends AsyncTask<Void, Void, String>{
	private String url = "http://yale-im.appspot.com";
    private Fragment parentFragment;
    private String task;
    
    public JSONParserTask(Fragment a, String t, String jsonurl){
    	parentFragment = a;
    	task = t;
    	url = jsonurl;
    }
	
	@Override
	protected String doInBackground(Void... params) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		String data = "";
		try {
			HttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			
			if(status == 200){
				HttpEntity entity = response.getEntity();
				data = EntityUtils.toString(entity);
			}
	    
			
	    //TODO handle the use case in which the user does not have network access.
	    //display "Check internet connection" in some way.
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
    protected void onPostExecute(String data) {
		if(task == "standings"){
            ((StandingsFragment) this.parentFragment).generateColleges(data);  
		}
		else if (task == "matches"){
			((MatchesFragment) this.parentFragment).generateMatches(data);
		}
	}
	
}
