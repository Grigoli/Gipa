package com.example.giga.gapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by giga on 21.02.2016.
 */
public class NotificationReturnSlot extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        String action = (String) getIntent().getExtras().get("DO");


        if (action.equals("volume")) {

//          Log.i("NotificationReturnSlot", "volume");
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

            //Your code
        } else if (action.equals("stopNotification")) {
            Log.i("NotificationReturnSlot", "stopNotification");
        }
        finish();
    }


}
