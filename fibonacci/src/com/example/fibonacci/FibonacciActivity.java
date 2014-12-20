package com.example.fibonacci;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class FibonacciActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE= "com.mycompany.fibonacci.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fibonacci, menu);
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
    
    
    public void checkFibo(View view){
    	Intent intent = new Intent(this, CheckFiboActivity.class);
    	EditText editText = (EditText) findViewById(R.id.enter_num);
    	String num = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, num);
    	startActivity(intent);
    }
    
}
