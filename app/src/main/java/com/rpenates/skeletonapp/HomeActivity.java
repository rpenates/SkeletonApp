package com.rpenates.skeletonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rpenates.skeletonapp.core.SessionHelper;

public class HomeActivity extends AppCompatActivity {

    private SessionHelper helper;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        helper = new SessionHelper(getApplicationContext());
        checkLogin();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.home_logout:
                doLogout();
                break;
            default:
                Log.i(TAG, "default statement menu");
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkLogin() {

        String username = helper.getUser();

        if(username.equals("none")) {
            Intent loginIntent = new Intent(this, LoginScreen.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
            this.finish();
        }else {
            Toast.makeText(this,"Hello from HomeActivity " +  username, Toast.LENGTH_SHORT).show();
        }

    }

    private void doLogout () {
        helper.deleteUser();
        Intent loginIntent = new Intent(this, LoginScreen.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loginIntent);
        this.finish();
    }
}
