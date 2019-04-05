package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by intel on 17. 11. 15.
 */

public class sosActivity extends Activity {




    Button btn_sossetup , btn_gosos , btn_gososcall;
    TextView tv_sosname , tv_sosphonenumber , tv_sosmsg;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        btn_sossetup = (Button)findViewById(R.id.btn_sossetup);
        btn_gosos = (Button)findViewById(R.id.btn_gosos);
        btn_gososcall = (Button)findViewById(R.id.btn_gososcall);

        tv_sosname = (TextView)findViewById(R.id.tv_sosname);
        tv_sosphonenumber = (TextView)findViewById(R.id.tv_sosphonenumber);
        tv_sosmsg = (TextView)findViewById(R.id.tv_sosmsg);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        final SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final String foramatDate = sdfNow.format(date);



        Intent intent = getIntent();

        final String data_sosname = intent.getStringExtra("sos_name");
        final String data_sosphonenumber = intent.getStringExtra("sos_phonenumber");
        final String data_sosmsg = intent.getStringExtra("sos_sosmsg");
        final String data_buffer = data_sosmsg;

        tv_sosname.setText("이름 : "+data_sosname);
        tv_sosphonenumber.setText("전화번호 : "+data_sosphonenumber);
        tv_sosmsg.setText("비상메세지 :" +data_sosmsg);


        /*
        if(data_sosmsg.equals("")){
            tv_sosmsg.setText("정보가 없습니다.");
        }else if(data_sosname.equals("")){
            tv_sosname.setText("정보가 없습니다.");
        }else if(data_sosphonenumber.equals("")){
            tv_sosphonenumber.setText("정보가 없습니다");
        }*/


    /*
        if(!data_sosmsg.equals("")) {
            tv_sosmsg.setText("null 이 아닙니다.");

        }else {
            tv_sosmsg.setText("null 입니다.");
        }*/
        //SOS 요청 (전화) 버튼을 눌렀을때
        btn_gososcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = "tel:" + data_sosphonenumber;
                startActivity(new Intent("android.intent.action.CALL" , Uri.parse(tel)) );
            }
        });


        //SOS 요청(문자) 버튼을 눌렀을떄
        btn_gosos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg ;
                Messenger messenger =new Messenger(getApplicationContext());
                 msg = data_sosname + "님께 : " + data_sosmsg + "sos 요청된시각은 : " + foramatDate;
                if(data_sosphonenumber.length()>0 && data_sosmsg.length()>0 ){
                    messenger.sendSMS(data_sosphonenumber,msg);
                }else{
                    Toast.makeText(sosActivity.this,"모두 입력해주세요",Toast.LENGTH_SHORT).show();
                }




            }


        });
        //sos기능설정 버튼을 눌렀을때
        btn_sossetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sosActivity.this, com.example.mcpintel.choinjong_socket.sossetup.class);
                startActivity(intent);
            }
        });
    }

}
class Messenger{
    private Context mContext;

    public Messenger(Context mContext){
        this.mContext = mContext;
    }

    public void sendSMS(String phoneNum , String  message){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum,null,message,null,null);

        Toast.makeText(mContext,"completed",Toast.LENGTH_SHORT).show();
    }
}