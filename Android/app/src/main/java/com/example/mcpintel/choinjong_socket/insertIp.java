package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by intel on 17. 11. 14.
 */

public class insertIp extends Activity {
    TextView tv_main,tv_test , tv_settingip;
    EditText ed_ip;
    Button btn_gocamera , btn_camerasetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertip);

        btn_gocamera = (Button)findViewById(R.id.btn_gocamera);
        btn_camerasetting = (Button)findViewById(R.id.btn_camerasetting);
        tv_settingip = (TextView) findViewById(R.id.tv_settingip);

        Intent intent = getIntent();
        final String data_cameraip = intent.getStringExtra("cameraip");
        final String data_ori_camera = intent.getStringExtra("ori_ip");

        tv_settingip.setText("설정된 주소 : "+data_cameraip);
        //카메라 셋팅 버튼을 눌렀을때
        btn_camerasetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(insertIp.this , camerasetup.class);
                startActivity(intent);
            }
        });


        //카메라보기 버튼을 눌렀을때
        btn_gocamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(insertIp.this,viewCamera.class);
                intent.putExtra("ip",data_cameraip);
                intent.putExtra("data_ori_ip",data_ori_camera);
                startActivity(intent);

                //tv_test.setText(ed_ip.getText().toString());
            }
        });

    }
}
