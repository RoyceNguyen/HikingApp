package com.stclaircollege.rnb.hikingapp;


import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;


//Created a settings activity to allow user to change the theme of the app to a darker theme
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new HikingPrefFragment()).commit();
    }

    public static class HikingPrefFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
