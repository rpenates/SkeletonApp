package com.rpenates.skeletonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rpenates.skeletonapp.core.SessionHelper;

public class LoginScreen extends AppCompatActivity {

    protected EditText usernameEdit;
    protected EditText passwordEdit;

    protected Button loginBtn; // tira a signinBtn
    protected Button registryBtn; // tira a signupBtn
    private SessionHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        usernameEdit = (EditText) findViewById(R.id.usernameField);
        passwordEdit = (EditText) findViewById(R.id.passwordField);

        loginBtn = (Button) findViewById(R.id.signinBtn);
        registryBtn = (Button) findViewById(R.id.signupBtn);
        helper = new SessionHelper(getApplicationContext());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLogin();
            }
        });

    }

    private void makeLogin() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        boolean canContinue = true;

        if (username.isEmpty()) {
            usernameEdit.setError(getResources().getString(R.string.requiredField));
            canContinue = false;
        } else if (password.isEmpty()) {
            passwordEdit.setError(getResources().getString(R.string.requiredField));
            canContinue = false;
        }

        if (canContinue) {
            helper.storeUser(username);
            Intent homeIntent = new Intent(this, HomeActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            this.finish();
        }

    }
}
