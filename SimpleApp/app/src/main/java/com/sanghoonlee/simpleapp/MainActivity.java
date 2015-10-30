package com.sanghoonlee.simpleapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sanghoonlee.simpleapp.Fragments.FragmentA;
import com.sanghoonlee.simpleapp.Fragments.FragmentB;


public class MainActivity extends AppCompatActivity {
    private final FragmentA mFirstFragment = new FragmentA();
    private final FragmentB mSecondFragment = new FragmentB();
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            // check if restored from prev state
            if (savedInstanceState != null) {
                return;
            }
            // display first fragment
            mFragmentManager.beginTransaction().add(R.id.fragment_container, mFirstFragment).commit();
        }
    }

    public void replaceFragment(boolean isChangingToSecond) {
        mFragmentManager.beginTransaction().replace(R.id.fragment_container,
                                isChangingToSecond? mSecondFragment:mFirstFragment)
                //add to back stack so we do not destroy the fragment
                .addToBackStack(null)
                .commit();
        //force execute committed transaction
        mFragmentManager.executePendingTransactions();
    }

    public void addSecondFragment() {
        mFragmentManager.beginTransaction().replace(R.id.fragment_container, mSecondFragment)
                //add to back stack so we do not destroy the fragment
                .addToBackStack(null)
                .commit();
        //force execute committed transaction
        mFragmentManager.executePendingTransactions();
    }

    public void removeSecondFragment() {
        mFragmentManager.popBackStack();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
