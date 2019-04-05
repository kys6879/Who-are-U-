package com.example.mcpintel.choinjong_socket;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by intel on 17. 11. 14.
 */

public class viewCamera extends Activity {

    WebView webView;
    Button btn_shoot , btn_movieshoot;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcamera);
        webView = (WebView)findViewById(R.id.web_camera);
        btn_shoot = (Button)findViewById(R.id.btn_shoot);
        btn_movieshoot = (Button)findViewById(R.id.btn_movieshoot);

        // 웹 주소 를담는 intent 생성 , 정의
        Intent intent = getIntent();
        //String 으로 변환후 data 변수에 대입
        String data = intent.getStringExtra("ip");
        final String ori_ip = intent.getStringExtra("data_ori_ip");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(data);







        //사진촬영 버튼을 눌렀을때
        btn_shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(viewCamera.this,"구현중입니다..",Toast.LENGTH_SHORT).show();
                MyClientTask myClientTask = new MyClientTask(ori_ip, 8888, "1");
                myClientTask.execute();
            }
        });
        //영상녹화 버튼을 눌렀을때
        btn_movieshoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(viewCamera.this,"영상녹화 기능은 준비중입니다.",Toast.LENGTH_SHORT).show();
                MyClientTask myClientTask = new MyClientTask(ori_ip, 8888, "2");
                myClientTask.execute();
            }
        });

    }

    //
    public class MyClientTask extends AsyncTask<Void, Void, Void> {
        String dstAddress;
        int dstPort;
        String response = "";
        String myMessage = "";

        //constructor
        MyClientTask(String addr, int port, String message){
            dstAddress = addr;
            dstPort = port;
            myMessage = message;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;
            myMessage = myMessage.toString();
            try {
                socket = new Socket(dstAddress, dstPort);
                //송신
                OutputStream out = socket.getOutputStream();
                out.write(myMessage.getBytes());

                //수신
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];
                int bytesRead;
                InputStream inputStream = socket.getInputStream();
    /*
     * notice:
     * inputStream.read() will block if no data return
     */
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }
                response = "서버의 응답: " + response;

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
           // recieveText.setText(response);
            super.onPostExecute(result);
        }
    }
}
