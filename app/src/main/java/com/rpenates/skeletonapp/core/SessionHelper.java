package com.rpenates.skeletonapp.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by selphfer on 27/01/16.
 */
public class SessionHelper {

    private String username;
    SharedPreferences appPreferences;
    private String TAG = getClass().getSimpleName();


    public SessionHelper(Context context){

        appPreferences = context.getSharedPreferences("appSession", Context.MODE_PRIVATE);

    }

    public String getUser() {
        username = appPreferences.getString("username", "none");
        Log.d(TAG, "accessToken: " + username);
        return username;
    }

    public void storeUser (String token) {
        if (!token.isEmpty()){
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putString("username", token);
            editor.commit();
        }else {
            Log.w(TAG, "Empty token!");
        }

    }

    public void deleteUser() {
        String token = getUser();

        if(!token.isEmpty()) {
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.remove("username");
            editor.commit();
        }else {
            Log.w(TAG, "Cannot delete accessToken!");
        }
    }



}
