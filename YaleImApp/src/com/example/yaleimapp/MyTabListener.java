package com.example.yaleimapp;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MyTabListener<T extends Fragment> implements TabListener{
	private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    //Constructor used each time a new tab is created.
    //activity  The host Activity, used to instantiate the fragment
    //tag  The identifier tag for the fragment
    //clz  The fragment's Class, used to instantiate the fragment
    public MyTabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }


    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            // If it exists, simply show it again.
            ft.show(mFragment);
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // hide the fragment rather than detaching it to allow for saving state.
            ft.hide(mFragment);
        }
    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
	}
}
