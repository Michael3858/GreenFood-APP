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

public class MainActivity6 extends AppCompatActivity {

    private ImageButton back;
    private Button btn2, btn3, confrim;
    String C = "", D1 = "", D2 = "", D3 = "", D4 = "", D5 = "", D6 = "", D7 = "", D8 = "", D9 = "", D10 = "", D11 = "", D12 = "", D13 = "", D14 = "", D15 = "", D16 = "", D17 = "", D18 = "", D19 = "", D20 = "", D21 = "",
    S1 = "", S2 = "", S3 = "", S4 = "", S5 = "", S6 = "", S7 = "", S8 = "", S9 = "", S10 = "", S11 = "", S12 = "", S13 = "", S14 = "", S15 = "", S16 = "", S17 = "", S18 = "", S19 = "", M1 = "", M2 = "", M3 = "", M4 = "", P1 = "", P2 = "", P3 = "";
    String result1, result2, result3, result4, result5;
    ArrayList<String> restaurantIDList = new ArrayList<String>();
    ArrayList<String> restaurantNameList = new ArrayList<String>();
    ArrayList<String> restaurantRateList = new ArrayList<>();
    ArrayList<String> restaurantRateIDList = new ArrayList<>();
    ArrayList<String> restaurantRateNumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        /*SharedPreferences G1 = getSharedPreferences("restaurantName1", MODE_PRIVATE);
        int n1 = G1.getInt("num", 0);
        for(int i = 0; i < n1; i++){
            String item1 = G1.getString("item_"+i, null);
            restaurantIDList.add(item1);
        }
        Toast toast = Toast.makeText(MainActivity6.this, String.valueOf(restaurantIDList.size()), Toast.LENGTH_SHORT);
        toast.show();*/

        SharedPreferences area = getSharedPreferences("area", MODE_PRIVATE);
        int city = area.getInt("city", 0);
        int d1 = area.getInt("d1", 0);
        int d2 = area.getInt("d2", 0);
        int d3 = area.getInt("d3", 0);
        int d4 = area.getInt("d4", 0);
        int d5 = area.getInt("d5", 0);
        int d6 = area.getInt("d6", 0);
        int d7 = area.getInt("d7", 0);
        int d8 = area.getInt("d8", 0);
        int d9 = area.getInt("d9", 0);
        int d10 = area.getInt("d10", 0);
        int d11 = area.getInt("d11", 0);
        int d12 = area.getInt("d12", 0);
        int d13 = area.getInt("d13", 0);
        int d14 = area.getInt("d14", 0);
        int d15 = area.getInt("d15", 0);
        int d16 = area.getInt("d16", 0);
        int d17 = area.getInt("d17", 0);
        int d18 = area.getInt("d18", 0);
        int d19 = area.getInt("d19", 0);
        int d20 = area.getInt("d20", 0);
        int d21 = area.getInt("d21", 0);
        SharedPreferences style = getSharedPreferences("style", MODE_PRIVATE);
        int s1 = style.getInt("s1", 1);
        int s2 = style.getInt("s2", 1);
        int s3 = style.getInt("s3", 1);
        int s4 = style.getInt("s4", 1);
        int s5 = style.getInt("s5", 1);
        int s6 = style.getInt("s6", 1);
        int s7 = style.getInt("s7", 1);
        int s8 = style.getInt("s8", 1);
        int s9 = style.getInt("s9", 1);
        int s10 = style.getInt("s10", 1);
        int s11 = style.getInt("s11", 1);
        int s12 = style.getInt("s12", 1);
        int s13 = style.getInt("s13", 1);
        int s14 = style.getInt("s14", 1);
        int s15 = style.getInt("s15", 1);
        int s16 = style.getInt("s16", 1);
        int s17 = style.getInt("s17", 1);
        int s18 = style.getInt("s18", 1);
        int s19 = style.getInt("s19", 1);
        SharedPreferences meal = getSharedPreferences("meal", MODE_PRIVATE);
        int m1 = meal.getInt("m1", 1);
        int m2 = meal.getInt("m2", 1);
        int m3 = meal.getInt("m3", 1);
        int m4 = meal.getInt("m4", 1);
        SharedPreferences payment = getSharedPreferences("payment", MODE_PRIVATE);
        int p1 = payment.getInt("p1", 1);
        int p2 = payment.getInt("p2", 1);
        int p3 = payment.getInt("p3", 1);

        switch (city){
            case 1:
                C = "台北市";
                if(d1 == 1){
                    D1 = "中正區";
                }
                if(d2 == 1){
                    D2 = "大同區";
                }
                if(d3 == 1){
                    D3 = "中山區";
                }
                if(d4 == 1){
                    D4 = "松山區";
                }
                if(d5 == 1){
                    D5 = "大安區";
                }
                if(d6 == 1){
                    D6 = "萬華區";
                }
                if(d7 == 1){
                    D7 = "信義區";
                }
                if(d8 == 1){
                    D8 = "士林區";
                }
                if(d9 == 1){
                    D9 = "南港區";
                }
                if(d10 == 1){
                    D10 = "北投區";
                }
                if(d11 == 1){
                    D11 = "文山區";
                }
                if(d12 == 1){
                    D12 = "內湖區";
                }
                break;
            case 2:
                C = "新北市";
                if(d1 == 1){
                    D1 = "板橋區";
                }
                if(d2 == 1){
                    D2 = "三重區";
                }
                if(d3 == 1){
                    D3 = "三峽區";
                }
                if(d4 == 1){
                    D4 = "土城區";
                }
                if(d5 == 1){
                    D5 = "中和區";
                }
                if(d6 == 1){
                    D6 = "五股區";
                }
                if(d7 == 1){
                    D7 = "永和區";
                }
                if(d8 == 1){
                    D8 = "汐止區";
                }
                if(d9 == 1){
                    D9 = "林口區";
                }
                if(d10 == 1){
                    D10 = "泰山區";
                }
                if(d11 == 1){
                    D11 = "淡水區";
                }
                if(d12 == 1){
                    D12 = "新莊區";
                }
                if(d13 == 1){
                    D13 = "新店區";
                }
                if(d14 == 1){
                    D14 = "樹林區";
                }
                if(d15 == 1){
                    D15 = "蘆洲區";
                }
                if(d16 == 1){
                    D16 = "鶯歌區";
                }
                if(d17 == 1){
                    D17 = "烏來區";
                }
                if(d18 == 1){
                    D18 = "萬里區";
                }
                if(d19 == 1){
                    D19 = "金山區";
                }
                if(d20 == 1){
                    D20 = "坪林區";
                }
                if(d21 == 1){
                    D21 = "八里區";
                }
                break;
            case 3:
                C = "桃園市";
                if(d1 == 1){
                    D1 = "中壢區";
                }
                if(d2 == 1){
                    D2 = "桃園區";
                }
                if(d3 == 1){
                    D3 = "龜山區";
                }
                if(d4 == 1){
                    D4 = "蘆竹區";
                }
                if(d5 == 1){
                    D5 = "復興區";
                }
                if(d6 == 1){
                    D6 = "大園區";
                }
                if(d7 == 1){
                    D7 = "八德區";
                }
                if(d8 == 1){
                    D8 = "平鎮區";
                }
                if(d9 == 1){
                    D9 = "大溪區";
                }
                break;
            case 4:
                C = "新竹市";
                if(d1 == 1){
                    D1 = "東區";
                }
                break;
            case 5:
                C = "新竹縣";
                if(d1 == 1){
                    D1 = "竹北市";
                }
                break;
            case 6:
                C = "台中市";
                if(d1 == 1){
                    D1 = "西區";
                }
                if(d2 == 1){
                    D2 = "中區";
                }
                if(d3 == 1){
                    D3 = "西屯區";
                }
                if(d4 == 1){
                    D4 = "北區";
                }
                if(d5 == 1){
                    D5 = "東區";
                }
                if(d6 == 1){
                    D6 = "南區";
                }
                if(d7 == 1){
                    D7 = "北屯區";
                }
                if(d8 == 1){
                    D8 = "南屯區";
                }
                if(d9 == 1){
                    D9 = "潭子區";
                }
                if(d10 == 1){
                    D10 = "新社區";
                }
                if(d11 == 1){
                    D11 = "烏日區";
                }
                if(d12 == 1){
                    D12 = "東勢區";
                }
                if(d13 == 1){
                    D13 = "沙鹿區";
                }
                if(d14 == 1){
                    D14 = "后里區";
                }
                if(d15 == 1){
                    D15 = "大甲區";
                }
                if(d16 == 1){
                    D16 = "和平區";
                }
                break;
            case 7:
                C = "雲林縣";
                if(d1 == 1){
                    D1 = "斗六市";
                }
                if(d2 == 1){
                    D2 = "古坑鄉";
                }
                if(d3 == 1){
                    D3 = "虎尾鎮";
                }
                if(d4 == 1){
                    D4 = "莿桐鄉";
                }
                break;
            case 8:
                C = "臺南市";
                if(d1 == 1){
                    D1 = "中西區";
                }
                if(d2 == 1){
                    D2 = "東區";
                }
                if(d3 == 1){
                    D3 = "南區";
                }
                if(d4 == 1){
                    D4 = "北區";
                }
                if(d5 == 1){
                    D5 = "安平區";
                }
                if(d6 == 1){
                    D6 = "安南區";
                }
                if(d7 == 1){
                    D7 = "歸仁區";
                }
                if(d8 == 1){
                    D8 = "新化區";
                }
                if(d9 == 1){
                    D9 = "仁德區";
                }
                if(d10 == 1){
                    D10 = "將軍區";
                }
                if(d11 == 1){
                    D11 = "新營區";
                }
                if(d12 == 1){
                    D12 = "後壁區";
                }
                if(d13 == 1){
                    D13 = "東山區";
                }
                if(d14 == 1){
                    D14 = "柳營區";
                }
                if(d15 == 1){
                    D15 = "善化區";
                }
                if(d16 == 1){
                    D16 = "西港區";
                }
                if(d17 == 1){
                    D17 = "玉井區";
                }
                if(d18 == 1){
                    D18 = "永康區";
                }
                break;
        }

        if(s1 == 1){
            S1 = "義式";
        }
        if(s2 == 1){
            S2 = "複合式";
        }
        if(s3 == 1){
            S3 = "美式";
        }
        if(s4 == 1){
            S4 = "洋食";
        }
        if(s5 == 1){
            S5 = "泰式";
        }
        if(s6 == 1){
            S6 = "中式";
        }
        if(s7 == 1){
            S7 = "西式";
        }
        if(s8 == 1){
            S8 = "素食";
        }
        if(s9 == 1){
            S9 = "火鍋";
        }
        if(s10 == 1){
            S10 = "壽喜燒";
        }
        if(s11 == 1){
            S11 = "港式";
        }
        if(s12 == 1){
            S12 = "日式";
        }
        if(s13 == 1){
            S13 = "甜點";
        }
        if(s14 == 1){
            S14 = "蔬食";
        }
        if(s15 == 1){
            S15 = "法式";
        }
        if(s16 == 1){
            S16 = "韓式";
        }
        if(s17 == 1){
            S17 = "澳式";
        }
        if(s18 == 1){
            S18 = "英式";
        }
        if(s19 == 1){
            S19 = "歐式";
        }
        if(m1 == 1){
            M1 = "單點";
        }
        if(m2 == 1){
            M2 = "自助餐";
        }
        if(m3 == 1){
            M3 = "吃到飽";
        }
        if(m4 == 1){
            M4 = "自助餐吃到飽";
        }
        if(p1 == 1){
            P1 = "付現";
        }
        if(p2 == 1){
            P2 = "刷卡";
        }
        if(p3 == 1){
            P3 = "Line Pay";
        }

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity6.this, MainActivity7.class);
                startActivity(intent);
            }
        });

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity6.this, MainActivity9.class);
                startActivity(intent);
            }
        });

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity6.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        confrim = (Button) findViewById(R.id.confrim);
        confrim.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(mutiThread);
                thread.start();
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
            intent.setClass(MainActivity6.this, MainActivity4.class);
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
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("search.php"));
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
                String post_data = URLEncoder.encode("C", "UTF-8")+"="+URLEncoder.encode(C, "UTF-8")+"&"
                        +URLEncoder.encode("D1", "UTF-8")+"="+URLEncoder.encode(D1, "UTF-8")+"&"
                        +URLEncoder.encode("D2", "UTF-8")+"="+URLEncoder.encode(D2, "UTF-8")+"&"
                        +URLEncoder.encode("D3", "UTF-8")+"="+URLEncoder.encode(D3, "UTF-8")+"&"
                        +URLEncoder.encode("D4", "UTF-8")+"="+URLEncoder.encode(D4, "UTF-8")+"&"
                        +URLEncoder.encode("D5", "UTF-8")+"="+URLEncoder.encode(D5, "UTF-8")+"&"
                        +URLEncoder.encode("D6", "UTF-8")+"="+URLEncoder.encode(D6, "UTF-8")+"&"
                        +URLEncoder.encode("D7", "UTF-8")+"="+URLEncoder.encode(D7, "UTF-8")+"&"
                        +URLEncoder.encode("D8", "UTF-8")+"="+URLEncoder.encode(D8, "UTF-8")+"&"
                        +URLEncoder.encode("D9", "UTF-8")+"="+URLEncoder.encode(D9, "UTF-8")+"&"
                        +URLEncoder.encode("D10", "UTF-8")+"="+URLEncoder.encode(D10, "UTF-8")+"&"
                        +URLEncoder.encode("D11", "UTF-8")+"="+URLEncoder.encode(D11, "UTF-8")+"&"
                        +URLEncoder.encode("D12", "UTF-8")+"="+URLEncoder.encode(D12, "UTF-8")+"&"
                        +URLEncoder.encode("D13", "UTF-8")+"="+URLEncoder.encode(D13, "UTF-8")+"&"
                        +URLEncoder.encode("D14", "UTF-8")+"="+URLEncoder.encode(D14, "UTF-8")+"&"
                        +URLEncoder.encode("D15", "UTF-8")+"="+URLEncoder.encode(D15, "UTF-8")+"&"
                        +URLEncoder.encode("D16", "UTF-8")+"="+URLEncoder.encode(D16, "UTF-8")+"&"
                        +URLEncoder.encode("D17", "UTF-8")+"="+URLEncoder.encode(D17, "UTF-8")+"&"
                        +URLEncoder.encode("D18", "UTF-8")+"="+URLEncoder.encode(D18, "UTF-8")+"&"
                        +URLEncoder.encode("D19", "UTF-8")+"="+URLEncoder.encode(D19, "UTF-8")+"&"
                        +URLEncoder.encode("D20", "UTF-8")+"="+URLEncoder.encode(D20, "UTF-8")+"&"
                        +URLEncoder.encode("D21", "UTF-8")+"="+URLEncoder.encode(D21, "UTF-8")+"&"
                        +URLEncoder.encode("S1", "UTF-8")+"="+URLEncoder.encode(S1, "UTF-8")+"&"
                        +URLEncoder.encode("S2", "UTF-8")+"="+URLEncoder.encode(S2, "UTF-8")+"&"
                        +URLEncoder.encode("S3", "UTF-8")+"="+URLEncoder.encode(S3, "UTF-8")+"&"
                        +URLEncoder.encode("S4", "UTF-8")+"="+URLEncoder.encode(S4, "UTF-8")+"&"
                        +URLEncoder.encode("S5", "UTF-8")+"="+URLEncoder.encode(S5, "UTF-8")+"&"
                        +URLEncoder.encode("S6", "UTF-8")+"="+URLEncoder.encode(S6, "UTF-8")+"&"
                        +URLEncoder.encode("S7", "UTF-8")+"="+URLEncoder.encode(S7, "UTF-8")+"&"
                        +URLEncoder.encode("S8", "UTF-8")+"="+URLEncoder.encode(S8, "UTF-8")+"&"
                        +URLEncoder.encode("S9", "UTF-8")+"="+URLEncoder.encode(S9, "UTF-8")+"&"
                        +URLEncoder.encode("S10", "UTF-8")+"="+URLEncoder.encode(S10, "UTF-8")+"&"
                        +URLEncoder.encode("S11", "UTF-8")+"="+URLEncoder.encode(S11, "UTF-8")+"&"
                        +URLEncoder.encode("S12", "UTF-8")+"="+URLEncoder.encode(S12, "UTF-8")+"&"
                        +URLEncoder.encode("S13", "UTF-8")+"="+URLEncoder.encode(S13, "UTF-8")+"&"
                        +URLEncoder.encode("S14", "UTF-8")+"="+URLEncoder.encode(S14, "UTF-8")+"&"
                        +URLEncoder.encode("S15", "UTF-8")+"="+URLEncoder.encode(S15, "UTF-8")+"&"
                        +URLEncoder.encode("S16", "UTF-8")+"="+URLEncoder.encode(S16, "UTF-8")+"&"
                        +URLEncoder.encode("S17", "UTF-8")+"="+URLEncoder.encode(S17, "UTF-8")+"&"
                        +URLEncoder.encode("S18", "UTF-8")+"="+URLEncoder.encode(S18, "UTF-8")+"&"
                        +URLEncoder.encode("S19", "UTF-8")+"="+URLEncoder.encode(S19, "UTF-8")+"&"
                        +URLEncoder.encode("M1", "UTF-8")+"="+URLEncoder.encode(M1, "UTF-8")+"&"
                        +URLEncoder.encode("M2", "UTF-8")+"="+URLEncoder.encode(M2, "UTF-8")+"&"
                        +URLEncoder.encode("M3", "UTF-8")+"="+URLEncoder.encode(M3, "UTF-8")+"&"
                        +URLEncoder.encode("M4", "UTF-8")+"="+URLEncoder.encode(M4, "UTF-8")+"&"
                        +URLEncoder.encode("P1", "UTF-8")+"="+URLEncoder.encode(P1, "UTF-8")+"&"
                        +URLEncoder.encode("P2", "UTF-8")+"="+URLEncoder.encode(P2, "UTF-8")+"&"
                        +URLEncoder.encode("P3", "UTF-8")+"="+URLEncoder.encode(P3, "UTF-8");
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
                    //String box = "";
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        for(int i = 0; i < dataJson.length(); i++){
                            JSONObject info = dataJson.getJSONObject(i);
                            String num = info.getString("ID");
                            String name = info.getString("Name");
                            result1 = num.toString();
                            result2 = name.toString();
                            restaurantIDList.add(result1);
                            restaurantNameList.add(result2);
                        }
                        //box += line + "\n";
                    }
                    inputStream.close(); // 關閉輸入串流
                    //result = box;
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result1 = e.toString();
                result2 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    SharedPreferences.Editor edit1 = getSharedPreferences("ListString1", MODE_PRIVATE).edit();
                    edit1.putInt("num", restaurantIDList.size());
                    for(int i = 0; i < restaurantIDList.size(); i++){
                        edit1.putString("item_"+i, restaurantIDList.get(i));
                    }
                    edit1.commit();
                    SharedPreferences.Editor edit2 = getSharedPreferences("ListString2", MODE_PRIVATE).edit();
                    edit2.putInt("num", restaurantNameList.size());
                    for(int i = 0; i < restaurantNameList.size(); i++){
                        edit2.putString("item_"+i, restaurantNameList.get(i));
                    }
                    edit2.commit();
                    Thread thread1 = new Thread(mutiThread1);
                    thread1.start();
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
                            result3 = AvgStar1.toString();
                            result4 = ID.toString();
                            result5 = String.valueOf(num);
                            restaurantRateList.add(result3);
                            restaurantRateIDList.add(result4);
                            restaurantRateNumList.add(result5);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result3 = e.toString();
                result4 = e.toString();
                result5 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
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
                    Intent intent = new Intent();
                    intent.setClass(MainActivity6.this, MainActivity11.class);
                    startActivity(intent);
                }
            });
        }
    };
}