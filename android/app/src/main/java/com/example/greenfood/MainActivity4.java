package com.example.greenfood;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private ImageButton btn;
    private Button btn1, btn2, btn3, btn4;
    String Account, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15;
    ArrayList<String> restaurantIDList = new ArrayList<String>();
    ArrayList<String> restaurantNameList = new ArrayList<String>();
    ArrayList<String> restaurantRateList = new ArrayList<>();
    ArrayList<String> restaurantRateNumList = new ArrayList<>();
    ArrayList<String> restaurantIDList2 = new ArrayList<String>();
    ArrayList<String> restaurantNameList2 = new ArrayList<String>();
    ArrayList<String> restaurantRateList2 = new ArrayList<>();
    ArrayList<String> restaurantRateIDList2 = new ArrayList<>();
    ArrayList<String> restaurantRateNumList2 = new ArrayList<>();
    ArrayList<String> restaurantIDList3 = new ArrayList<String>();
    ArrayList<String> restaurantNameList3 = new ArrayList<String>();
    ArrayList<String> restaurantRateList3 = new ArrayList<>();
    ArrayList<String> restaurantRateIDList3 = new ArrayList<>();
    ArrayList<String> restaurantRateNumList3 = new ArrayList<>();
    ArrayList<String> restaurantHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        SharedPreferences user = getSharedPreferences("account", MODE_PRIVATE);
        Account = user.getString("acc", "");

        SharedPreferences area = getSharedPreferences("area", MODE_PRIVATE);
        SharedPreferences.Editor edit = area.edit();
        edit.remove("city");
        edit.remove("d1");
        edit.remove("d2");
        edit.remove("d3");
        edit.remove("d4");
        edit.remove("d5");
        edit.remove("d6");
        edit.remove("d7");
        edit.remove("d8");
        edit.remove("d9");
        edit.remove("d10");
        edit.remove("d11");
        edit.remove("d12");
        edit.remove("d13");
        edit.remove("d14");
        edit.remove("d15");
        edit.remove("d16");
        edit.remove("d17");
        edit.remove("d18");
        edit.remove("d19");
        edit.remove("d20");
        edit.remove("d21");
        edit.commit();
        SharedPreferences style = getSharedPreferences("style", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = style.edit();
        edit1.remove("s1");
        edit1.remove("s2");
        edit1.remove("s3");
        edit1.remove("s4");
        edit1.remove("s5");
        edit1.remove("s6");
        edit1.remove("s7");
        edit1.remove("s8");
        edit1.remove("s9");
        edit1.remove("s10");
        edit1.remove("s11");
        edit1.remove("s12");
        edit1.remove("s13");
        edit1.remove("s14");
        edit1.remove("s15");
        edit1.remove("s16");
        edit1.remove("s17");
        edit1.remove("s18");
        edit1.remove("s19");
        edit1.commit();
        SharedPreferences meal = getSharedPreferences("meal", MODE_PRIVATE);
        SharedPreferences.Editor edit2 = meal.edit();
        edit2.remove("m1");
        edit2.remove("m2");
        edit2.remove("m3");
        edit2.remove("m4");
        edit2.commit();
        SharedPreferences payment = getSharedPreferences("payment", MODE_PRIVATE);
        SharedPreferences.Editor edit3 = payment.edit();
        edit3.remove("p1");
        edit3.remove("p2");
        edit3.remove("p3");
        edit3.commit();
        SharedPreferences liststring1 = getSharedPreferences("ListString1", MODE_PRIVATE);
        SharedPreferences.Editor edit4 = liststring1.edit();
        int num1 = liststring1.getInt("num", 0);
        for(int i = 0; i < num1; i++){
            edit4.remove("item_"+i);
        }
        edit4.remove("num");
        edit4.commit();
        SharedPreferences liststring2 = getSharedPreferences("ListString2", MODE_PRIVATE);
        SharedPreferences.Editor edit5 = liststring2.edit();
        int num2 = liststring2.getInt("num", 0);
        for(int i = 0; i < num2; i++){
            edit5.remove("item_"+i);
        }
        edit5.remove("num");
        edit5.commit();
        SharedPreferences liststring3 = getSharedPreferences("ListString3", MODE_PRIVATE);
        SharedPreferences.Editor edit6 = liststring3.edit();
        int num3 = liststring3.getInt("num", 0);
        for(int i = 0; i < num3; i++){
            edit6.remove("item_"+i);
        }
        edit6.remove("num");
        edit6.commit();
        SharedPreferences liststring4 = getSharedPreferences("ListString4", MODE_PRIVATE);
        SharedPreferences.Editor edit7 = liststring4.edit();
        int num4 = liststring4.getInt("num", 0);
        for(int i = 0; i < num4; i++){
            edit7.remove("item_"+i);
        }
        edit7.remove("num");
        edit7.commit();
        SharedPreferences liststring5 = getSharedPreferences("ListString5", MODE_PRIVATE);
        SharedPreferences.Editor edit8 = liststring5.edit();
        int num5 = liststring5.getInt("num", 0);
        for(int i = 0; i < num5; i++){
            edit8.remove("item_"+i);
        }
        edit8.remove("num");
        edit8.commit();
        SharedPreferences liststring6 = getSharedPreferences("ListString6", MODE_PRIVATE);
        SharedPreferences.Editor edit9 = liststring6.edit();
        int num6 = liststring6.getInt("num", 0);
        for(int i = 0; i < num6; i++){
            edit9.remove("item_"+i);
        }
        edit9.remove("num");
        edit9.commit();
        SharedPreferences liststring7 = getSharedPreferences("ListString7", MODE_PRIVATE);
        SharedPreferences.Editor edit10 = liststring7.edit();
        int num7 = liststring7.getInt("num", 0);
        for(int i = 0; i < num7; i++){
            edit10.remove("item_"+i);
        }
        edit10.remove("num");
        edit10.commit();
        SharedPreferences liststring8 = getSharedPreferences("ListString8", MODE_PRIVATE);
        SharedPreferences.Editor edit11 = liststring8.edit();
        int num8 = liststring8.getInt("num", 0);
        for(int i = 0; i < num8; i++){
            edit11.remove("item_"+i);
        }
        edit11.remove("num");
        edit11.commit();
        SharedPreferences liststring9 = getSharedPreferences("ListString9", MODE_PRIVATE);
        SharedPreferences.Editor edit12 = liststring9.edit();
        int num9 = liststring9.getInt("num", 0);
        for(int i = 0; i < num9; i++){
            edit12.remove("item_"+i);
        }
        edit12.remove("num");
        edit12.commit();
        SharedPreferences liststring10 = getSharedPreferences("ListString10", MODE_PRIVATE);
        SharedPreferences.Editor edit13 = liststring10.edit();
        int num10 = liststring10.getInt("num", 0);
        for(int i = 0; i < num10; i++){
            edit13.remove("item_"+i);
        }
        edit13.remove("num");
        edit13.commit();
        SharedPreferences liststring11 = getSharedPreferences("ListString11", MODE_PRIVATE);
        SharedPreferences.Editor edit14 = liststring11.edit();
        int num11 = liststring11.getInt("num", 0);
        for(int i = 0; i < num11; i++){
            edit14.remove("item_"+i);
        }
        edit14.remove("num");
        edit14.commit();
        SharedPreferences liststring12 = getSharedPreferences("ListString12", MODE_PRIVATE);
        SharedPreferences.Editor edit15 = liststring12.edit();
        int num12 = liststring12.getInt("num", 0);
        for(int i = 0; i < num12; i++){
            edit15.remove("item_"+i);
        }
        edit15.remove("num");
        edit15.commit();
        SharedPreferences liststring13 = getSharedPreferences("ListString13", MODE_PRIVATE);
        SharedPreferences.Editor edit16 = liststring13.edit();
        int num13 = liststring13.getInt("num", 0);
        for(int i = 0; i < num13; i++){
            edit16.remove("item_"+i);
        }
        edit16.remove("num");
        edit16.commit();
        SharedPreferences liststring14 = getSharedPreferences("ListString14", MODE_PRIVATE);
        SharedPreferences.Editor edit17 = liststring14.edit();
        int num14 = liststring14.getInt("num", 0);
        for(int i = 0; i < num14; i++){
            edit17.remove("item_"+i);
        }
        edit17.remove("num");
        edit17.commit();
        SharedPreferences liststring15 = getSharedPreferences("ListString15", MODE_PRIVATE);
        SharedPreferences.Editor edit18 = liststring15.edit();
        int num15 = liststring15.getInt("num", 0);
        for(int i = 0; i < num15; i++){
            edit18.remove("item_"+i);
        }
        edit18.remove("num");
        edit18.commit();
        SharedPreferences liststring16 = getSharedPreferences("ListString16", MODE_PRIVATE);
        SharedPreferences.Editor edit19 = liststring16.edit();
        int num16 = liststring16.getInt("num", 0);
        for(int i = 0; i < num16; i++){
            edit19.remove("item_"+i);
        }
        edit19.remove("num");
        edit19.commit();
        SharedPreferences liststring17 = getSharedPreferences("ListString17", MODE_PRIVATE);
        SharedPreferences.Editor edit20 = liststring17.edit();
        int num17 = liststring17.getInt("num", 0);
        for(int i = 0; i < num17; i++){
            edit20.remove("item_"+i);
        }
        edit20.remove("num");
        edit20.commit();
        SharedPreferences liststring18 = getSharedPreferences("ListString18", MODE_PRIVATE);
        SharedPreferences.Editor edit21 = liststring18.edit();
        int num18 = liststring18.getInt("num", 0);
        for(int i = 0; i < num18; i++){
            edit21.remove("item_"+i);
        }
        edit21.remove("num");
        edit21.commit();
        SharedPreferences liststring19 = getSharedPreferences("ListString19", MODE_PRIVATE);
        SharedPreferences.Editor edit22 = liststring19.edit();
        int num19 = liststring19.getInt("num", 0);
        for(int i = 0; i < num19; i++){
            edit22.remove("item_"+i);
        }
        edit22.remove("num");
        edit22.commit();
        SharedPreferences liststring20 = getSharedPreferences("ListString20", MODE_PRIVATE);
        SharedPreferences.Editor edit23 = liststring20.edit();
        int num20 = liststring20.getInt("num", 0);
        for(int i = 0; i < num20; i++){
            edit23.remove("item_"+i);
        }
        edit23.remove("num");
        edit23.commit();

        btn = (ImageButton) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this,MainActivity5.class);
                startActivity(intent);
            }
        });

        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity4.this,MainActivity18.class);
                startActivity(intent);
            }
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(mutiThread);
                thread.start();
            }
        });

        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread2 = new Thread(mutiThread2);
                thread2.start();
            }
        });

        btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread4 = new Thread(mutiThread4);
                thread4.start();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu_without_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 取得點選項目的id
        int id = item.getItemId();

        // 依照id判斷點了哪個項目並做相應事件
        if (id == R.id.action_help) {
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
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("popular.php"));
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
                            String ID = info.getString("ID");
                            String Name = info.getString("Name");
                            Double AvgStar = info.getDouble("AvgStar");
                            Double AvgStar1 = Math.round(AvgStar*100.0)/100.0;
                            int num = info.getInt("Num");
                            result1 = ID.toString();
                            result2 = Name.toString();
                            result3 = AvgStar1.toString();
                            result4 = String.valueOf(num);
                            restaurantIDList.add(result1);
                            restaurantNameList.add(result2);
                            restaurantRateList.add(result3);
                            restaurantRateNumList.add(result4);
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
                result4 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString6", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList.size());
                    for(int i = 0; i < restaurantIDList.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString7", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList.size());
                    for(int i = 0; i < restaurantNameList.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList.get(i));
                    }
                    edit2.commit();
                    SharedPreferences.Editor edit3 = getSharedPreferences("ListString8", MODE_PRIVATE).edit();
                    edit3.putInt("num", restaurantRateList.size());
                    for(int i = 0; i < restaurantRateList.size(); i++){
                        edit3.putString("item_"+i, restaurantRateList.get(i));
                    }
                    edit3.commit();
                    SharedPreferences.Editor edit4 = getSharedPreferences("ListString9", MODE_PRIVATE).edit();
                    edit4.putInt("num", restaurantRateNumList.size());
                    for(int i = 0; i < restaurantRateNumList.size(); i++){
                        edit4.putString("item_"+i, restaurantRateNumList.get(i));
                    }
                    edit4.commit();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity4.this,MainActivity13.class);
                    startActivity(intent);
                }
            });
        }
    };
    private Runnable mutiThread2 = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("myfavorite.php"));
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Account", "UTF-8")+"="+URLEncoder.encode(Account, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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
                            String ID = info.getString("ID");
                            String Name = info.getString("Name");
                            result5 = ID.toString();
                            result6 = Name.toString();
                            restaurantIDList2.add(result5);
                            restaurantNameList2.add(result6);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result5 = e.toString();
                result6 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString10", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList2.size());
                    for(int i = 0; i < restaurantIDList2.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList2.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString11", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList2.size());
                    for(int i = 0; i < restaurantNameList2.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList2.get(i));
                    }
                    edit2.commit();
                    Thread thread3 = new Thread(mutiThread3);
                    thread3.start();
                }
            });
        }
    };
    private Runnable mutiThread3 = new Runnable(){
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
                            result7 = AvgStar1.toString();
                            result8 = ID.toString();
                            result9 = String.valueOf(num);
                            restaurantRateList2.add(result7);
                            restaurantRateIDList2.add(result8);
                            restaurantRateNumList2.add(result9);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result7 = e.toString();
                result8 = e.toString();
                result9 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit3 = getSharedPreferences("ListString12", MODE_PRIVATE).edit();
                    edit3.putInt("num", restaurantRateList2.size());
                    for(int i = 0; i < restaurantRateList2.size(); i++){
                        edit3.putString("item_"+i, restaurantRateList2.get(i));
                    }
                    edit3.commit();
                    SharedPreferences.Editor edit4 = getSharedPreferences("ListString13", MODE_PRIVATE).edit();
                    edit4.putInt("num", restaurantRateIDList2.size());
                    for(int i = 0; i < restaurantRateIDList2.size(); i++){
                        edit4.putString("item_"+i, restaurantRateIDList2.get(i));
                    }
                    edit4.commit();
                    SharedPreferences.Editor edit5 = getSharedPreferences("ListString14", MODE_PRIVATE).edit();
                    edit5.putInt("num", restaurantRateNumList2.size());
                    for(int i = 0; i < restaurantRateNumList2.size(); i++){
                        edit5.putString("item_"+i, restaurantRateNumList2.get(i));
                    }
                    edit5.commit();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity4.this, MainActivity14.class);
                    startActivity(intent);
                }
            });
        }
    };
    private Runnable mutiThread4 = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("history.php"));
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Account", "UTF-8")+"="+URLEncoder.encode(Account, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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
                            String ID = info.getString("ID");
                            String Name = info.getString("Name");
                            String Time = info.getString("Time");
                            result10 = ID.toString();
                            result11 = Name.toString();
                            result12 = Time.toString();
                            restaurantIDList3.add(result10);
                            restaurantNameList3.add(result11);
                            restaurantHistoryList.add(result12);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result10 = e.toString();
                result11 = e.toString();
                result12 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString15", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList3.size());
                    for(int i = 0; i < restaurantIDList3.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList3.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString16", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList3.size());
                    for(int i = 0; i < restaurantNameList3.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList3.get(i));
                    }
                    edit2.commit();
                    SharedPreferences.Editor edit3 = getSharedPreferences("ListString17", MODE_PRIVATE).edit();
                    edit3.putInt("num", restaurantHistoryList.size());
                    for(int i = 0; i < restaurantHistoryList.size(); i++){
                        edit3.putString("item_"+i, restaurantHistoryList.get(i));
                    }
                    edit3.commit();
                    Thread thread5 = new Thread(mutiThread5);
                    thread5.start();
                }
            });
        }
    };
    private Runnable mutiThread5 = new Runnable(){
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
                            result13 = AvgStar1.toString();
                            result14 = ID.toString();
                            result15 = String.valueOf(num);
                            restaurantRateList3.add(result13);
                            restaurantRateIDList3.add(result14);
                            restaurantRateNumList3.add(result15);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result13 = e.toString();
                result14 = e.toString();
                result15 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit4 = getSharedPreferences("ListString18", MODE_PRIVATE).edit();
                    edit4.putInt("num", restaurantRateList3.size());
                    for(int i = 0; i < restaurantRateList3.size(); i++){
                        edit4.putString("item_"+i, restaurantRateList3.get(i));
                    }
                    edit4.commit();
                    SharedPreferences.Editor edit5 = getSharedPreferences("ListString19", MODE_PRIVATE).edit();
                    edit5.putInt("num", restaurantRateIDList3.size());
                    for(int i = 0; i < restaurantRateIDList3.size(); i++){
                        edit5.putString("item_"+i, restaurantRateIDList3.get(i));
                    }
                    edit5.commit();
                    SharedPreferences.Editor edit6 = getSharedPreferences("ListString20", MODE_PRIVATE).edit();
                    edit6.putInt("num", restaurantRateNumList3.size());
                    for(int i = 0; i < restaurantRateNumList3.size(); i++){
                        edit6.putString("item_"+i, restaurantRateNumList3.get(i));
                    }
                    edit6.commit();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity4.this,MainActivity15.class);
                    startActivity(intent);
                }
            });
        }
    };
}