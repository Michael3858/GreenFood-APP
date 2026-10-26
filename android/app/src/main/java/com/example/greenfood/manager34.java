package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class manager34 extends AppCompatActivity {

    ImageButton back;
    Spinner spinner1, spinner2;
    Button confirm;
    ArrayList<String> allcitylist = new ArrayList<>();
    ArrayList<String> alldistrictlist = new ArrayList<>();
    ArrayList<String> citylist = new ArrayList<>();
    ArrayList<String> districtlist = new ArrayList<>();
    ArrayList<String> restaurantIDList = new ArrayList<>();
    ArrayList<String> restaurantNameList = new ArrayList<>();
    String result1, result2, result3, result4, result5, response1, response2;
    String city1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager34);

        getalllocation();
        getcity();

        citylist.add("請選擇縣市");
        districtlist.add("請選擇區域");

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, citylist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city1 = parent.getSelectedItem().toString();
                districtlist.clear();
                districtlist.add("請選擇區域");
                for(int i = 0; i < alldistrictlist.size(); i++){
                    if(city1.equals(allcitylist.get(i).toString())){
                        districtlist.add(alldistrictlist.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, districtlist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner1.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(manager34.this, "請選擇縣市", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(spinner2.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(manager34.this, "請選擇行政區", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Thread thread = new Thread(mutiThread);
                    thread.start();
                }
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
            intent.setClass(manager34.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                String City = spinner1.getSelectedItem().toString();
                String District = spinner2.getSelectedItem().toString();
                URL url = new URL(ApiConfig.url("findrestaurant.php"));
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
                String post_data = URLEncoder.encode("City", "UTF-8")+"="+URLEncoder.encode(City, "UTF-8")+"&"
                        +URLEncoder.encode("District", "UTF-8")+"="+URLEncoder.encode(District, "UTF-8");
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
                            String ID = info.getString("ID");
                            String Name = info.getString("Name");
                            result4 = ID.toString();
                            result5 = Name.toString();
                            restaurantIDList.add(result4);
                            restaurantNameList.add(result5);
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
                //result = e.toString();
                result4 = e.toString();
                result5 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(manager34.this, manager35.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("restaurantIDList", restaurantIDList);
                    bundle.putStringArrayList("restaurantNameList", restaurantNameList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    };
    private void getcity() {
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
        String path=ApiConfig.url("getcitylist.php"); //192.168.1.24需改成自己的ip
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
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String city = info.getString("city");
                result1 = city.toString();
                citylist.add(result1);
            }
        }
        return sb.toString();
    }
    private void getalllocation() {
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
        String path=ApiConfig.url("getalllocation.php"); //192.168.1.24需改成自己的ip
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
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String city = info.getString("city");
                String district = info.getString("district");
                result2 = city.toString();
                result3 = district.toString();
                allcitylist.add(result2);
                alldistrictlist.add(result3);
            }
        }
        return sb.toString();
    }
}