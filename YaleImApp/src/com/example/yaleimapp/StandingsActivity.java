package com.example.yaleimapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StandingsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_standings);
		
		String[] residentialColleges = {"Berkeley", "Branford", "Calhoun", "Davenport", "Erza Stiles",
				"Johnathan Edwards", "Morse", "Pierson", "Saybrook", "Silliman",
				"Timothy Dwight", "Trumbull"}; //string list for residential college names
		
		ListAdapter adapter = new StandingsAdapter(this, residentialColleges);
		ListView listView = (ListView) findViewById(R.id.standingsList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override //TO DO, Make an intent for an Activity that displays a particular College Page
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//provide a page for viewing various information about a particular college
				
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.standings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
