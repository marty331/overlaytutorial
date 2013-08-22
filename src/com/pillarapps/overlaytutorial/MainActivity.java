package com.pillarapps.overlaytutorial;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	Button mActivity;
	boolean showTut;
	SharedPreferences setNoti;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mActivity = (Button) findViewById(R.id.bActivty);
		mActivity.setOnClickListener(this);

		getActionBar().setHomeButtonEnabled(true);

		// Get SharedPrefs
		setNoti = PreferenceManager.getDefaultSharedPreferences(this);
		// SharedPref tutorial

		showTut = setNoti.getBoolean("tutorial", true);
		Log.i("MainActivity", "onCreate showTut =" + showTut);
		if (showTut == true) {
			showActivityOverlay();
		}

	}

	private void showActivityOverlay() {
		final Dialog dialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar);

		dialog.setContentView(R.layout.overlay_activity);

		LinearLayout layout = (LinearLayout) dialog
				.findViewById(R.id.llOverlay_activity);
		layout.setBackgroundColor(Color.TRANSPARENT);
		layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				dialog.dismiss();

			}

		});

		dialog.show();

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:

			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.preferences:
			Intent setPref = new Intent(MainActivity.this, Prefs.class);
			startActivity(setPref);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bActivty) {
			Intent startFragment = new Intent(this, FragDemo.class);
			showTut = setNoti.getBoolean("tutorial", true);
			Log.i("MainActivity", "onClick showTut =" + showTut);
			Bundle bundle = new Bundle();
			bundle.putBoolean("showtut", showTut);
			startFragment.putExtras(bundle);
			startActivity(startFragment);

		}

	}
}
