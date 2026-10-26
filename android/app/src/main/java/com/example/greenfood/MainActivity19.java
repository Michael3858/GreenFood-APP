package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
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

public class MainActivity19 extends AppCompatActivity {

    Spinner spinner;
    Button select, pass, confirm;
    ArrayList<String> districtlist = new ArrayList<>();
    ArrayList<String> stylelist = new ArrayList<>();
    ArrayList<String> restaurantIDList = new ArrayList<>();
    ArrayList<String> restaurantNameList = new ArrayList<>();
    ArrayList<String> restaurantDistrictList = new ArrayList<>();
    ArrayList<String> restaurantstyleList = new ArrayList<>();
    ArrayList<String> restaurantpaymentList = new ArrayList<>();
    ArrayList<String> restaurantmealList = new ArrayList<>();
    ArrayList<String> districtlist2 = new ArrayList<>();
    ArrayList<String> restaurantIDList2 = new ArrayList<>();
    ArrayList<String> restaurantNameList2 = new ArrayList<>();
    ArrayList<String> restaurantDistrictList2 = new ArrayList<>();
    ArrayList<String> restaurantstyleList2 = new ArrayList<>();
    ArrayList<String> restaurantpaymentList2 = new ArrayList<>();
    ArrayList<String> restaurantmealList2 = new ArrayList<>();
    String district, out = "";
    String result, response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);

        getstyle();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        districtlist = bundle.getStringArrayList("districtlist");
        restaurantIDList = bundle.getStringArrayList("restaurantIDList");
        restaurantNameList = bundle.getStringArrayList("restaurantNameList");
        restaurantDistrictList = bundle.getStringArrayList("restaurantDistrictList");
        restaurantstyleList = bundle.getStringArrayList("restaurantstyleList");
        restaurantpaymentList = bundle.getStringArrayList("restaurantpaymentList");
        restaurantmealList = bundle.getStringArrayList("restaurantmealList");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, districtlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!out.contains(spinner.getSelectedItem().toString())){
                    out += "\n" + spinner.getSelectedItem().toString();
                }
                Toast toast = Toast.makeText(MainActivity19.this, "您選擇了" + out, Toast.LENGTH_SHORT);
                toast.show();
                district = spinner.getSelectedItem().toString();
                districtlist2.add(district);
            }
        });

        pass = findViewById(R.id.pass);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity19.this, MainActivity20.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("stylelist", stylelist);
                bundle.putStringArrayList("restaurantIDList", restaurantIDList);
                bundle.putStringArrayList("restaurantNameList", restaurantNameList);
                bundle.putStringArrayList("restaurantstyleList", restaurantstyleList);
                bundle.putStringArrayList("restaurantpaymentList", restaurantpaymentList);
                bundle.putStringArrayList("restaurantmealList", restaurantmealList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(districtlist2.size() == 0){
                    Toast toast = Toast.makeText(MainActivity19.this, "請選擇行政區", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //int n = 0, m = 0;
                    for (int i = 0; i < districtlist2.size(); i++) {
                        String d = districtlist2.get(i);
                        for (int j = 0; j < restaurantIDList.size(); j++) {
                            String r = restaurantDistrictList.get(j);
                            if (d.equals(r)) {
                                //n++;
                                restaurantIDList2.add(restaurantIDList.get(j));
                                restaurantNameList2.add(restaurantNameList.get(j));
                                restaurantstyleList2.add(restaurantstyleList.get(j));
                                restaurantpaymentList2.add(restaurantpaymentList.get(j));
                                restaurantmealList2.add(restaurantmealList.get(j));
                            }
                        /*if(d.substring(0, 3).equals("三重區")){
                            n++;
                        }*/
                        /*if(d.equals("三重區")){
                            n++;
                        }*/
                            //m++;
                        }
                    }
                /*Toast toast = Toast.makeText(MainActivity19.this, districtlist2.get(1) + String.valueOf(n) + " " + String.valueOf(m), Toast.LENGTH_SHORT);
                toast.show();*/
                /*Toast toast = Toast.makeText(MainActivity19.this, String.valueOf(restaurantNameList2.size()), Toast.LENGTH_SHORT);
                toast.show();*/
                    Intent intent = new Intent();
                    intent.setClass(MainActivity19.this, MainActivity20.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("stylelist", stylelist);
                    bundle.putStringArrayList("restaurantIDList", restaurantIDList2);
                    bundle.putStringArrayList("restaurantNameList", restaurantNameList2);
                    bundle.putStringArrayList("restaurantstyleList", restaurantstyleList2);
                    bundle.putStringArrayList("restaurantpaymentList", restaurantpaymentList2);
                    bundle.putStringArrayList("restaurantmealList", restaurantmealList2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu, menu);
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
            intent.setClass(MainActivity19.this, MainActivity4.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_help) {
            // 按下「使用說明」要做的事
            Toast.makeText(this, "使用說明", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_about) {
            // 按下「關於」要做的事
            Toast.makeText(this, "關於", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getstyle() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response = executeHttpGet();
                    }
                }

        ).start();
    }

    private String executeHttpGet() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("getstylelist.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String style = info.getString("style");
                result = style.toString();
                stylelist.add(result);
            }
        }
        return sb.toString();
    }
}