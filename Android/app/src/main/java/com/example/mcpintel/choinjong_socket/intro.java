package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by intel on 17. 11. 18.
 */

public class intro extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
/*
        ActionBar actionbar = getActionBar();
        actionbar.hide();*/

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(intro.this ,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }

}
