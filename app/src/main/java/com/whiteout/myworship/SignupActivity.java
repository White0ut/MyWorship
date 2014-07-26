package com.whiteout.myworship;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.res.Configuration;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Kendrick Cline on 7/25/14.
 */
public class SignupActivity extends Activity {

    ParseUser user;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText reenter_passwordEditText;
    private EditText emailEditText;
    private EditText phoneEditText;

    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        usernameEditText = (EditText) findViewById(R.id.signup_username_editText);
        passwordEditText = (EditText) findViewById(R.id.signup_password_editText);
        reenter_passwordEditText = (EditText) findViewById(R.id.signup_reenter_password_editText);
        emailEditText = (EditText) findViewById(R.id.signup_email_editText);
        phoneEditText = (EditText) findViewById(R.id.signup_phone_editText);

        signupButton = (Button) findViewById(R.id.signup_button);
        // Build the signup button's onClick event listener
        signupButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if there is a username to add
                if (usernameEditText.getText() == null) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter a username",
                            Toast.LENGTH_LONG);
                    return;

                // Validate the passwords match
                } else if (passwordEditText.getText().toString().compareTo(reenter_passwordEditText.getText().toString()) != 0) {
                    Toast.makeText(getApplicationContext(),
                            "Be sure to use the same password both times",
                            Toast.LENGTH_LONG).show();
                    return;
                } else if (emailEditText.getText() == null) {
                    Toast.makeText(getApplicationContext(),
                            "Be sure to enter an e-mail to recover you password",
                            Toast.LENGTH_LONG).show();
                }

                // Register the user
                user = new ParseUser();

                user.setPassword(passwordEditText.getText().toString());

                user.setUsername(usernameEditText.getText().toString());

                if(phoneEditText.getText() != null) {
                    user.put("phone", phoneEditText.getText().toString());
                }

                user.signUpInBackground( new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Signup unsuccessful:(",
                                    Toast.LENGTH_LONG).show();
                            Log.e("MyWorship",e.getMessage());
                        }
                    }
                });

            }
        });



    }

}
