package com.bhcnav.jialin.crimeminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.bhcnav.jialin.crimeminalintent.test.CrimeFragment;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_crime);

        FragmentManager fm = getSupportFragmentManager();   //add fragment to activity
        Fragment fragment = fm.findFragmentById(R.id.crime_title);

        if(fragment == null) {
            fragment = new CrimeFragment();
        //    fm.beginTransaction()
        //            .add(R.id.crime_title, fragment)
        //            .commit();
        }
    }
}