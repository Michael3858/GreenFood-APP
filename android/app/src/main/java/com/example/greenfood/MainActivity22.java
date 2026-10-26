package com.example.greenfood;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity22 extends AppCompatActivity {

    Spinner spinner;
    Button select, pass, confirm;
    ArrayList<String> meallist = new ArrayList<>();
    ArrayList<String> restaurantIDList = new ArrayList<>();
    ArrayList<String> restaurantNameList = new ArrayList<>();
    ArrayList<String> restaurantmealList = new ArrayList<>();
    ArrayList<String> meallist2 = new ArrayList<>();
    ArrayList<String> restaurantIDList2 = new ArrayList<>();
    ArrayList<String> restaurantNameList2 = new ArrayList<>();
    ArrayList<String> restaurantmealList2 = new ArrayList<>();
    ArrayList<String> restaurantIDList3 = new ArrayList<>();
    ArrayList<String> restaurantNameList3 = new ArrayList<>();
    ArrayList<String> restaurantRateList = new ArrayList<>();
    ArrayList<String> restaurantRateIDList = new ArrayList<>();
    ArrayList<String> restaurantRateNumList = new ArrayList<>();
    String result1, result2, result3;
    String meal, out = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        meallist = bundle.getStringArrayList("meallist");
        restaurantIDList = bundle.getStringArrayList("restaurantIDList");
        restaurantNameList = bundle.getStringArrayList("restaurantNameList");
        restaurantmealList = bundle.getStringArrayList("restaurantmealList");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, meallist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!out.contains(spinner.getSelectedItem().toString())){
                    out += "\n" + spinner.getSelectedItem().toString();
                }
                Toast toast = Toast.makeText(MainActivity22.this, "您選擇了" + out, Toast.LENGTH_SHORT);
                toast.show();
                meal = spinner.getSelectedItem().toString();
                meallist2.add(meal);
            }
        });

        pass = findViewById(R.id.pass);
        pass.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Thread thread1 = new Thread(mutiThread1);
                thread1.start();
            }
        });

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(meallist2.size() == 0){
                    Toast toast = Toast.makeText(MainActivity22.this, "請選擇用餐方式", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Thread thread = new Thread(mutiThread);
                    thread.start();
                }
            }
        });
    }
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("average.php"));
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode = connection.getResponseCode();
                // 建立取得回應的物件
                if(responseCode == HttpURLConnection.HTTP_OK){
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream = connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        for(int i = 0; i < dataJson.length(); i++){
                            JSONObject info = dataJson.getJSONObject(i);
                            Double AvgStar = info.getDouble("AvgStar");
                            Double AvgStar1 = Math.round(AvgStar*100.0)/100.0;
                            String ID = info.getString("ID");
                            int num = info.getInt("Num");
                            result1 = AvgStar1.toString();
                            result2 = ID.toString();
                            result3 = String.valueOf(num);
                            restaurantRateList.add(result1);
                            restaurantRateIDList.add(result2);
                            restaurantRateNumList.add(result3);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result1 = e.toString();
                result2 = e.toString();
                result3 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    for(int i = 0; i < meallist2.size(); i++){
                        String m = meallist2.get(i);
                        for(int j = 0; j < restaurantIDList.size(); j++) {
                            String r = restaurantmealList.get(j);
                            if (m.equals(r)) {
                                restaurantIDList2.add(restaurantIDList.get(j));
                                restaurantNameList2.add(restaurantNameList.get(j));
                            }
                        }
                    }
                    for(int i = 0; i < restaurantIDList2.size(); i++){
                        int s = 0;
                        for(int j = 0; j < restaurantIDList3.size(); j++){
                            if(restaurantIDList2.get(i).equals(restaurantIDList3.get(j))){
                                s = 1;
                            }
                        }
                        if(s == 0){
                            restaurantIDList3.add(restaurantIDList2.get(i));
                            restaurantNameList3.add(restaurantNameList2.get(i));
                        }
                    }
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString1", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList3.size());
                    for(int i = 0; i < restaurantIDList3.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList3.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString2", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList3.size());
                    for(int i = 0; i < restaurantNameList3.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList3.get(i));
                    }
                    edit2.commit();
                    SharedPreferences.Editor edit3 = getSharedPreferences("ListString3", MODE_PRIVATE).edit();
                    edit3.putInt("num", restaurantRateList.size());
                    for(int i = 0; i < restaurantRateList.size(); i++){
                        edit3.putString("item_"+i, restaurantRateList.get(i));
                    }
                    edit3.commit();
                    SharedPreferences.Editor edit4 = getSharedPreferences("ListString4", MODE_PRIVATE).edit();
                    edit4.putInt("num", restaurantRateIDList.size());
                    for(int i = 0; i < restaurantRateIDList.size(); i++){
                        edit4.putString("item_"+i, restaurantRateIDList.get(i));
                    }
                    edit4.commit();
                    SharedPreferences.Editor edit5 = getSharedPreferences("ListString5", MODE_PRIVATE).edit();
                    edit5.putInt("num", restaurantRateNumList.size());
                    for(int i = 0; i < restaurantRateNumList.size(); i++){
                        edit5.putString("item_"+i, restaurantRateNumList.get(i));
                    }
                    edit5.commit();
                    if(restaurantIDList3.size() == 0){
                        Toast toast = Toast.makeText(MainActivity22.this, "無符合餐廳!請返回主選單", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    Intent intent = new Intent();
                    intent.setClass(MainActivity22.this, MainActivity11.class);
                    startActivity(intent);
                }
            });
        }
    };
    private Runnable mutiThread1 = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("average.php"));
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode = connection.getResponseCode();
                // 建立取得回應的物件
                if(responseCode == HttpURLConnection.HTTP_OK){
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream = connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        for(int i = 0; i < dataJson.length(); i++){
                            JSONObject info = dataJson.getJSONObject(i);
                            Double AvgStar = info.getDouble("AvgStar");
                            Double AvgStar1 = Math.round(AvgStar*100.0)/100.0;
                            String ID = info.getString("ID");
                            int num = info.getInt("Num");
                            result1 = AvgStar1.toString();
                            result2 = ID.toString();
                            result3 = String.valueOf(num);
                            restaurantRateList.add(result1);
                            restaurantRateIDList.add(result2);
                            restaurantRateNumList.add(result3);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result1 = e.toString();
                result2 = e.toString();
                result3 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    for(int i = 0; i < restaurantIDList.size(); i++){
                        int s = 0;
                        for(int j = 0; j < restaurantIDList3.size(); j++){
                            if(restaurantIDList.get(i).equals(restaurantIDList3.get(j))){
                                s = 1;
                            }
                        }
                        if(s == 0){
                            restaurantIDList3.add(restaurantIDList.get(i));
                            restaurantNameList3.add(restaurantNameList.get(i));
                        }
                    }
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString1", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList3.size());
                    for(int i = 0; i < restaurantIDList3.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList3.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString2", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList3.size());
                    for(int i = 0; i < restaurantNameList3.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList3.get(i));
                    }
                    edit2.commit();
                    SharedPreferences.Editor edit3 = getSharedPreferences("ListString3", MODE_PRIVATE).edit();
                    edit3.putInt("num", restaurantRateList.size());
                    for(int i = 0; i < restaurantRateList.size(); i++){
                        edit3.putString("item_"+i, restaurantRateList.get(i));
                    }
                    edit3.commit();
                    SharedPreferences.Editor edit4 = getSharedPreferences("ListString4", MODE_PRIVATE).edit();
                    edit4.putInt("num", restaurantRateIDList.size());
                    for(int i = 0; i < restaurantRateIDList.size(); i++){
                        edit4.putString("item_"+i, restaurantRateIDList.get(i));
                    }
                    edit4.commit();
                    SharedPreferences.Editor edit5 = getSharedPreferences("ListString5", MODE_PRIVATE).edit();
                    edit5.putInt("num", restaurantRateNumList.size());
                    for(int i = 0; i < restaurantRateNumList.size(); i++){
                        edit5.putString("item_"+i, restaurantRateNumList.get(i));
                    }
                    edit5.commit();
                    if(restaurantIDList3.size() == 0){
                        Toast toast = Toast.makeText(MainActivity22.this, "無符合餐廳!請返回主選單", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    Intent intent = new Intent();
                    intent.setClass(MainActivity22.this, MainActivity11.class);
                    startActivity(intent);
                }
            });
        }
    };
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
            intent.setClass(MainActivity22.this, MainActivity4.class);
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
}