package com.whiteout.myworship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseUser;
import com.whiteout.myworship.adapters.MyPagerAdapter;

import com.viewpagerindicator.TitlePageIndicator;




public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    MyPagerAdapter mSectionsPagerAdapter;


    Menu menu;
    MenuItem logoutMenuItem;
    MenuItem loginMenuItem;

    ParseUser currentUser;

    private final int LOGIN_ACTIVITY_REQUEST_CODE = 1;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null) {
            // was rotated
            //Toast.makeText(getApplicationContext(), "HEY! ROTATED", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), "HEY! NOT ROTATED", Toast.LENGTH_SHORT).show();
            currentUser = ParseUser.getCurrentUser();
            if(currentUser == null) {

                Intent intent = new Intent(this, LoginActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Welcome, " + currentUser.getUsername(),
                        Toast.LENGTH_SHORT).show();

            }
        }





        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new MyPagerAdapter(getFragmentManager());

        /*
        adapterViewPager.getRegisteredFragment(0);
        // returns first Fragment item within the pager
        adapterViewPager.getRegisteredFragment(mViewPager.getCurrentItem());
        // return current Fragment item displayed within the pager
         */

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (menu != null) {
            loginMenuItem = menu.findItem(R.id.login_menu_item);
            logoutMenuItem = menu.findItem(R.id.logout_menu_item);

            if (ParseUser.getCurrentUser() == null) {
                loginMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                logoutMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
            } else {
                logoutMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                loginMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        loginMenuItem = menu.findItem(R.id.login_menu_item);
        logoutMenuItem = menu.findItem(R.id.logout_menu_item);
        if (ParseUser.getCurrentUser() == null) {
            loginMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            logoutMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        } else {
            logoutMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            loginMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        }

        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.logout_menu_item) {
            ParseUser.logOut();
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
            if (menu != null) {
                loginMenuItem = menu.findItem(R.id.login_menu_item);
                loginMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
            return true;
        }
        if(id == R.id.login_menu_item) {
            login();
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
            if (menu != null) {
                logoutMenuItem = menu.findItem(R.id.logout_menu_item);
                logoutMenuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
        }
        return super.onOptionsItemSelected(item);
    }



    private void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
