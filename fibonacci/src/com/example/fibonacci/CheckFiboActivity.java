package com.example.fibonacci;

import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CheckFiboActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String message = intent.getStringExtra(FibonacciActivity.EXTRA_MESSAGE);
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		int numToCheck = Integer.parseInt(message);
		
		if(isFibo(numToCheck)) textView.setText("That is a Fibonacci Number!");
		else textView.setText("That is not a Fibonacci Number!");
		
		setContentView(textView);
		
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
	
	
	 public boolean isSquare(int n){
	    int root = (int) Math.sqrt(n);
	    if ((root * root) == n) return true;
	    else return false;
	    
	}
	    
	public boolean isFibo(int num){
	    return(isSquare(5  *num * num - 4) || (isSquare(5 * num * num + 4)));
	}
}
