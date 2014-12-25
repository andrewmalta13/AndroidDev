package com.example.yaleimapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


@SuppressWarnings("deprecation")
public class StandingsActivity extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		//setContentView(R.layout.activity_standings);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
	   
	    Tab standingsTab = actionBar.newTab().setText("Standings").
	    		setTabListener(new MyTabListener<StandingsFragment>(this, "Standings", StandingsFragment.class));
	    actionBar.addTab(standingsTab);
	    
	    //TODO change StandingsFragment to MatchesFragment when module is completed.
	    Tab matchesTab = actionBar.newTab().setText("Matches").
	    		setTabListener(new MyTabListener<MatchesFragment>(this, "Matches", MatchesFragment.class));
	    actionBar.addTab(matchesTab);
	    
	    Tab sportsTab = actionBar.newTab().setText("Sports").
                setTabListener(new MyTabListener<MatchesFragment>(this, "Sports", MatchesFragment.class));
	    actionBar.addTab(sportsTab);
			
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
