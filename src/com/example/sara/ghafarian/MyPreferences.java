package com.example.sara.ghafarian;



import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

public class MyPreferences extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);}

}
