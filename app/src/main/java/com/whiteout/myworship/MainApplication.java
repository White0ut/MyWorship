package com.whiteout.myworship;

import android.app.Application;

import com.parse.Parse;


/**
* Created by Kendrick Cline on 7/24/14.
*/
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
        //ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
        //ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));


    }
}
