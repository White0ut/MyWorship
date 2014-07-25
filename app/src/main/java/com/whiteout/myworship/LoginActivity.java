package com.whiteout.myworship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

/**
 * Created by Kendrick Cline on 7/24/14.
 */
public class LoginActivity extends Activity {

    private EditText userNameEditText;
    private EditText passwordEditText;

    private Button loginButton;
    private Button signUpButton;

    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = new ParseUser();

        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


    }

    private void loginUser() {
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (user != null) {
                    // Yeah! User is logged in!
                    setResult(RESULT_OK);
                    finish();
                } else {
                    // Signup borked, wrong credentials probs
                    Toast.makeText(getApplicationContext(), "Attempt failed, incorrect username/password.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
