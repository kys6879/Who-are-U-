package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by intel on 17. 11. 15.
 */

public class sossetup extends Activity {
    EditText ed_name , ed_phonenumber , ed_sosmsg;
    Button btn_sossubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sossetup);
        ed_name = (EditText)findViewById(R.id.ed_name);
        ed_phonenumber = (EditText)findViewById(R.id.ed_phonenumber);
        ed_sosmsg = (EditText)findViewById(R.id.ed_sosmsg);

        btn_sossubmit = (Button)findViewById(R.id.btn_sossubmit);

        btn_sossubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sossetup.this , sosActivity.class);
                intent.putExtra("sos_name",ed_name.getText().toString());
                intent.putExtra("sos_phonenumber",ed_phonenumber.getText().toString());
                intent.putExtra("sos_sosmsg",ed_sosmsg.getText().toString());

                startActivity(intent);
            }
        });




    }
}
