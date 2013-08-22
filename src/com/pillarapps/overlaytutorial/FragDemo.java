package com.pillarapps.overlaytutorial;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class FragDemo extends FragmentActivity  {

	static public class MyFragment1 extends Fragment implements OnClickListener {

		Button mActButton;
		boolean showTut;

		
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View myFragmentView = inflater.inflate(R.layout.fragment_main, container, false);
			mActButton = (Button) myFragmentView.findViewById(R.id.bFragment);
			mActButton.setOnClickListener(this);
			
			Bundle bundle = getActivity().getIntent().getExtras();
			showTut = bundle.getBoolean("showtut");
			Log.i("FragDemo", "showTut ="+showTut);
			
			
			
			if (showTut ==true) {
				showActivityOverlay();
			}
			
			return myFragmentView;
		}
		
		
		private void showActivityOverlay() {
			final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);

			dialog.setContentView(R.layout.overlay_fragment);

			LinearLayout layout = (LinearLayout) dialog
					.findViewById(R.id.llOverlay_fragment);
			layout.setBackgroundColor(Color.TRANSPARENT);
			layout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Get SharedPrefs
					PreferenceManager.setDefaultValues(getActivity(), R.xml.prefs, true);
					SharedPreferences setNoti = PreferenceManager
							.getDefaultSharedPreferences(getActivity());
					setNoti.edit().putBoolean("tutorial", false).commit();
					showTut = false;
					dialog.dismiss();

				}

			});

			dialog.show();

		}


		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.bFragment) {
				Intent startFragment = new Intent(getActivity(), MainActivity.class);
				
				startActivity(startFragment);
			}
			
		}

		

	}
	FrameLayout fragmentContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_contain);

		fragmentContainer = (FrameLayout) findViewById(R.id.container);
		if (savedInstanceState == null) {
			// if's the first time created
			MyFragment1 myFragment1 = new MyFragment1();
			FragmentManager supportFragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = supportFragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.container, myFragment1);
			fragmentTransaction.commit();
		}

	}
	
	
	
	

}
