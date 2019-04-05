package com.example.mcpintel.choinjong_socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    Button btn_viewcamera , btn_sos ,  btn_tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //앱 기본 스타일 설정
        getSupportActionBar().setElevation(0);


        btn_viewcamera = (Button) findViewById(R.id.btn_viewcamera);
        btn_sos = (Button) findViewById(R.id.btn_sos);
        btn_tts = (Button)findViewById(R.id.btn_tts);

        //btn_tts = (Button) findViewById(R.id.btn_tts);

        //tts 버튼 클릭
        btn_tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tts.class);
                startActivity(intent);
            }
        });

        //sos 버튼 클릭
        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sosActivity.class);
                startActivity(intent);
            }
        });


        //viewcamera 버튼 클릭

        btn_viewcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, insertIp.class);
                startActivity(intent);
            }
        });

    }

}

