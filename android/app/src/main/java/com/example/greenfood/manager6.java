package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class manager6 extends AppCompatActivity {

    ImageButton back;
    Button button1, button2, button3, button4, button5, button6, button7, button8;
    String response1, response2, response3, response4, response5, response6, response7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager6);

        back = findViewById(R.id.back);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrestaurant();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countlocationchart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager40.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countstylechart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager8.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countmealchart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager9.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countpaymentchart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager10.class);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countfoodchart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager11.class);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //countcountrychart();
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager12.class);
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager6.this, manager34.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu_only_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 取得點選項目的id
        int id = item.getItemId();

        // 依照id判斷點了哪個項目並做相應事件
        if (id == R.id.action_home) {
            // 按下「設定」要做的事
            Intent intent = new Intent();
            intent.setClass(manager6.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private void countlocationchart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response1 = executeHttpGet1();
                    }
                }

        ).start();
    }

    private String executeHttpGet1() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countlocationchart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo1(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo1(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String lnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                lnum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager7.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", lnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }

    private void countstylechart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response2 = executeHttpGet2();
                    }
                }

        ).start();
    }

    private String executeHttpGet2() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countstylechart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo2(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo2(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String snum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                snum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager8.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", snum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }

    private void countmealchart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response3 = executeHttpGet3();
                    }
                }

        ).start();
    }

    private String executeHttpGet3() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countmealchart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo3(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo3(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String mnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                mnum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager9.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", mnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }

    private void countpaymentchart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response4 = executeHttpGet4();
                    }
                }

        ).start();
    }

    private String executeHttpGet4() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countpaymentchart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo4(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo4(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String pnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                pnum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager10.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", pnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }*/

    private void countrestaurant() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response5 = executeHttpGet5();
                    }
                }

        ).start();
    }

    private String executeHttpGet5() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countrestaurant.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo5(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo5(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String rnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String ID = info.getString("ID");
                int n = Integer.parseInt(ID);
                rnum = String.valueOf(n + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager2.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", rnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }
    /*private void countfoodchart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response6 = executeHttpGet6();
                    }
                }

        ).start();
    }

    private String executeHttpGet6() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countfoodchart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo6(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo6(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String fnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                fnum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager11.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", fnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }

    private void countcountrychart() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response7 = executeHttpGet7();
                    }
                }

        ).start();
    }

    private String executeHttpGet7() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("countcountrychart.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo7(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo7(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line = null;
        String cnum = null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                int numcount = info.getInt("numcount");
                cnum = String.valueOf(numcount + 1);
            }
        }
        Intent intent = new Intent();
        intent.setClass(manager6.this, manager12.class);
        Bundle bundle = new Bundle();
        bundle.putString("num", cnum);
        intent.putExtras(bundle);
        startActivity(intent);
        return sb.toString();
    }*/
}