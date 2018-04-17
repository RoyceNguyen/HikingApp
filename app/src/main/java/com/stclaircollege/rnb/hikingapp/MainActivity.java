package com.stclaircollege.rnb.hikingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
//import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        //adding all the interaction listeners
        MainFragment.OnFragmentInteractionListener,
        PastHikeFragment.OnFragmentInteractionListener,
        SummaryFragment.OnFragmentInteractionListener,
        AddTripFragment.OnFragmentInteractionListener,
        ContactUsFragment.OnFragmentInteractionListener{

    //Adding FragmentManager
    FragmentManager fm = getSupportFragmentManager();
    public static FloatingActionButton fab;
    public static final String TAG = "THEMES";
    private boolean isLight;
    private boolean isChecked;
    private TextView tv;
    private int currentTheme;
    private int oldTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        isChecked = sharedPref.getBoolean("caps_pref", false);
        String lister = sharedPref.getString("list_preference", "1");
        oldTheme = Integer.parseInt(lister);

        // Following options to change the Theme must precede setContentView().
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        toggleTheme();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Checking to see if the activity has already been created
        if(savedInstanceState == null){
            FragmentTransaction tran = fm.beginTransaction();
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
        toggleTheme();
    }

    // Method to check SharedPreferences and set the current theme
    private void toggleTheme(){

        // Following options to change the Theme must precede setContentView().
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        isChecked = sharedPref.getBoolean("caps_pref", false);
        String lister = sharedPref.getString("list_preference", "1");
        String myName = sharedPref.getString("edittext_preference", "");

        currentTheme = Integer.parseInt(lister);
        if(currentTheme == 2){
            isLight = false;
        } else {
            isLight = true;
        }


        if(isLight) {
            setTheme(R.style.HoloLightCustom);
        } else {
            setTheme(R.style.HoloCustom);
        }

        // If theme has changed, force a restart of MainActivity to get the new theme
        // to display for it. That this is required may be a known bug in Android.  See
        //
        //    https://code.google.com/p/android/issues/detail?id=4394
        //
        // for further discussion.

        if(oldTheme != currentTheme){

            oldTheme = currentTheme;

            Intent k = new Intent(this, MainActivity.class);

            // Following flag clears the activity with old theme from the stack so an exit from the
            // activity with new theme will not take you back to the version with the old theme.

            k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(k);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        } else if (id == R.id.nav_addtrip) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new AddTripFragment());
            tran.commit();

        } else if (id == R.id.nav_pasthikes) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new PastHikeFragment());
            tran.commit();

        } else if (id == R.id.nav_summary) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new SummaryFragment());
            tran.commit();

        } else if (id == R.id.nav_futuretrips) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new PastHikeFragment());
            tran.commit();

        } else if (id == R.id.nav_contact) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new ContactUsFragment());
            tran.commit();
        } else if (id == R.id.nav_credits) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new CreditsFragment());
            tran.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onFragmentInteraction(Uri uri){

    }
}
