package com.example.nuhakhayat.quizzy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Titanium on 1/8/17.
 */

public class broadcastResever extends BroadcastReceiver {

    static public String Username;
    @Override
    public void onReceive(Context context, Intent intent) {
        Username = intent.getStringExtra("username");

    }
}
