package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by intel on 17. 11. 18.
 */

public class camerasetup extends Activity {

        EditText ed_cameraip ;
        Button btn_camerasubmit;
        String data ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerasetup);

        ed_cameraip = (EditText)findViewById(R.id.ed_cameraip);
        btn_camerasubmit = (Button)findViewById(R.id.btn_camerasubmit);

        btn_camerasubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(camerasetup.this , insertIp.class);
                intent.putExtra("cameraip","http://"+ed_cameraip.getText().toString()+":8080");
                intent.putExtra("ori_ip",ed_cameraip.getText().toString());
                startActivity(intent);
            }
        });


    }
}
