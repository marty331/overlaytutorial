package com.pillarapps.overlaytutorial;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class Prefs extends PreferenceActivity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new MyPreferenceFragment())
				.commit();
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		getActionBar().setHomeButtonEnabled(true);
		

	}

	public static class MyPreferenceFragment extends PreferenceFragment {
		
		
		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.prefs);

		}
	}
	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
 		MenuInflater inflater = getMenuInflater();
 	    inflater.inflate(R.menu.main, menu);
 		return true;
 	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;

		case R.id.preferences:
			Intent setPref = new Intent(this, Prefs.class);
			startActivity(setPref);
			return true;

		
			
		default:
			return super.onOptionsItemSelected(item);

		}
	}
	
	
}
