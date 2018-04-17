package com.stclaircollege.rnb.hikingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.stclaircollege.rnb.hikingapp.Fragment.AddTripFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.CompletedTripFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.EditCompletedTripFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.EditFutureTripFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.MainFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.FutureTripFragment;
import com.stclaircollege.rnb.hikingapp.Fragment.SummaryFragment;
import com.stclaircollege.rnb.hikingapp.Model.Trip;
import com.stclaircollege.rnb.hikingapp.Util.DatabaseHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        FutureTripFragment.FutureTripListener,
        AddTripFragment.AddTripListener,
        EditFutureTripFragment.EditFutureTripListener,
        CompletedTripFragment.CompletedTripListener,
        EditCompletedTripFragment.EditCompletedTripListener,
        SummaryFragment.SummaryListener {

    //Adding FragmentManager
    FragmentManager fm = getSupportFragmentManager();

    private DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DatabaseHandler(MainActivity.this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
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
            tran.replace(R.id.content_main, new AddTripFragment(MainActivity.this));
            tran.commit();

        } else if (id == R.id.nav_pasthikes) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new CompletedTripFragment(MainActivity.this));
            tran.commit();
        } else if (id == R.id.nav_summary) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new SummaryFragment(MainActivity.this));
            tran.commit();

        } else if (id == R.id.nav_futuretrips) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new FutureTripFragment(MainActivity.this));
            tran.commit();
        } else if (id == R.id.nav_contact) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        } else if (id == R.id.nav_credits) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new MainFragment());
            tran.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onFragmentInteraction(Uri uri){
    }

    @Override
    public void onClickCreateTripButton() {
        FragmentTransaction tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        tran.replace(R.id.content_main, new FutureTripFragment(MainActivity.this));
        tran.commit();
    }

    @Override
    public void onClickEditFutureTrip(int trip_id) {
        Trip trip = handler.getTrip(trip_id);
        if (trip != null) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new EditFutureTripFragment(MainActivity.this, trip));
            tran.addToBackStack(null).commit();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClickUpdateFutureTripButton() {
        fm.popBackStack();
    }

    @Override
    public void onClickCompleteFutureTripButton() {
        fm.popBackStack();
        FragmentTransaction tran = fm.beginTransaction();
        tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        tran.replace(R.id.content_main, new CompletedTripFragment(MainActivity.this));
        tran.commit();
    }

    @Override
    public void onClickEditCompletedTrip(int trip_id) {
        Trip trip = handler.getTrip(trip_id);
        if (trip != null) {
            FragmentTransaction tran = fm.beginTransaction();
            tran.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            tran.replace(R.id.content_main, new EditCompletedTripFragment(MainActivity.this, trip));
            tran.addToBackStack(null).commit();
        }
    }

    @Override
    public void onClickUpdateCompletedTripButton() {
        fm.popBackStack();
    }

    @Override
    public void onClickCancelButton() {
        fm.popBackStack();
    }
}
