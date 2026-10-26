package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class manager2 extends AppCompatActivity {

    ImageButton back;
    Button stylenumButton, paymentnumButton, confirm;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7;
    EditText IDET, nameET, addressET, cityET, districtET, phoneET, priceET, businesshoursET, mealET, wifiET, accessibilityET, deliveryET, urlET, fanpageET, picurlET;
    EditText stylenumET, style1ET, style2ET, style3ET, style4ET, style5ET, style6ET, style7ET, style8ET, style9ET, style10ET, style11ET, style12ET, style13ET, style14ET, style15ET, style16ET, style17ET, style18ET, style19ET;
    EditText paymentnumET, payment1ET, payment2ET, payment3ET;
    int snum, pnum;
    ArrayList<String> allcitylist = new ArrayList<>();
    ArrayList<String> alldistrictlist = new ArrayList<>();
    ArrayList<String> citylist = new ArrayList<>();
    ArrayList<String> districtlist = new ArrayList<>();
    ArrayList<String> meallist = new ArrayList<>();
    String city1, ID, num;
    String result1, result2, result3, result4, response1, response2, response3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2);

        Bundle bundle = getIntent().getExtras();
        num = bundle.getString("num");

        getalllocation();
        getcity();
        getmeal();
        citylist.add("請選擇縣市");
        districtlist.add("請選擇區域");
        meallist.add("請選擇用餐方式");

        //IDET = findViewById(R.id.IDET); //記得要改掉
        nameET = findViewById(R.id.nameET);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        addressET = findViewById(R.id.addressET);
        phoneET = findViewById(R.id.phoneET);
        spinner3 = findViewById(R.id.spinner3);
        businesshoursET = findViewById(R.id.businesshoursET);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        urlET = findViewById(R.id.urlET);
        fanpageET = findViewById(R.id.fanpageET);
        picurlET = findViewById(R.id.picurlET);
        /*stylenumET = findViewById(R.id.stylenumET);
        style1ET = findViewById(R.id.style1ET);
        style2ET = findViewById(R.id.style2ET);
        style3ET = findViewById(R.id.style3ET);
        style4ET = findViewById(R.id.style4ET);
        style5ET = findViewById(R.id.style5ET);
        style6ET = findViewById(R.id.style6ET);
        style7ET = findViewById(R.id.style7ET);
        style8ET = findViewById(R.id.style8ET);
        style9ET = findViewById(R.id.style9ET);
        style10ET = findViewById(R.id.style10ET);
        style11ET = findViewById(R.id.style11ET);
        style12ET = findViewById(R.id.style12ET);
        style13ET = findViewById(R.id.style13ET);
        style14ET = findViewById(R.id.style14ET);
        style15ET = findViewById(R.id.style15ET);
        style16ET = findViewById(R.id.style16ET);
        style17ET = findViewById(R.id.style17ET);
        style18ET = findViewById(R.id.style18ET);
        style19ET = findViewById(R.id.style19ET);
        paymentnumET = findViewById(R.id.paymentnumET);
        payment1ET = findViewById(R.id.payment1ET);
        payment2ET = findViewById(R.id.payment2ET);
        payment3ET = findViewById(R.id.payment3ET);*/

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

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

        String[] pricelist = {"請選擇價格區間", "$", "$$", "$$$", "$$$$", "$$$$$"};
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pricelist);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, meallist);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        String[] wifilist = {"請選擇有無Wi-Fi", "有", "無"};
        ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, wifilist);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        String[] accessibilitylist = {"請選擇有無無障礙", "有", "無"};
        ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accessibilitylist);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        String[] deliverylist = {"請選擇有無外送", "有", "無"};
        ArrayAdapter adapter7 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, deliverylist);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);

        /*stylenumButton = findViewById(R.id.stylenumButton);
        stylenumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snum = Integer.parseInt(stylenumET.getText().toString());
                if(snum >= 1){
                    style1ET.setVisibility(View.VISIBLE);
                    if(snum >= 2){
                        style2ET.setVisibility(View.VISIBLE);
                        if(snum >= 3){
                            style3ET.setVisibility(View.VISIBLE);
                            if(snum >= 4){
                                style4ET.setVisibility(View.VISIBLE);
                                if(snum >= 5){
                                    style5ET.setVisibility(View.VISIBLE);
                                    if(snum >= 6){
                                        style6ET.setVisibility(View.VISIBLE);
                                        if(snum >= 7){
                                            style7ET.setVisibility(View.VISIBLE);
                                            if(snum >= 8){
                                                style8ET.setVisibility(View.VISIBLE);
                                                if(snum >= 9){
                                                    style9ET.setVisibility(View.VISIBLE);
                                                    if(snum >= 10){
                                                        style10ET.setVisibility(View.VISIBLE);
                                                        if(snum >= 11){
                                                            style11ET.setVisibility(View.VISIBLE);
                                                            if(snum >= 12){
                                                                style12ET.setVisibility(View.VISIBLE);
                                                                if(snum >= 13){
                                                                    style13ET.setVisibility(View.VISIBLE);
                                                                    if(snum >= 14){
                                                                        style14ET.setVisibility(View.VISIBLE);
                                                                        if(snum >= 15){
                                                                            style15ET.setVisibility(View.VISIBLE);
                                                                            if(snum >= 16){
                                                                                style16ET.setVisibility(View.VISIBLE);
                                                                                if(snum >= 17){
                                                                                    style17ET.setVisibility(View.VISIBLE);
                                                                                    if(snum >= 18){
                                                                                        style18ET.setVisibility(View.VISIBLE);
                                                                                        if(snum >= 19){
                                                                                            style19ET.setVisibility(View.VISIBLE);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        paymentnumButton = findViewById(R.id.paymentnumButton);
        paymentnumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pnum = Integer.parseInt(paymentnumET.getText().toString());
                if(pnum >= 1){
                    payment1ET.setVisibility(View.VISIBLE);
                    if(pnum >= 2){
                        payment2ET.setVisibility(View.VISIBLE);
                        if(pnum >= 3){
                            payment3ET.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });*/
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameET.getText().toString())){
                    Toast toast = Toast.makeText(manager2.this, "請輸入新餐廳的名字", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(spinner1.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(manager2.this, "請選擇新餐廳的縣市", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(spinner2.getSelectedItemPosition() == 0){
                    Toast toast = Toast.makeText(manager2.this, "請選擇新餐廳的行政區", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(TextUtils.isEmpty(addressET.getText().toString())){
                    Toast toast = Toast.makeText(manager2.this, "請輸入新餐廳的地址", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    if(spinner4.getSelectedItemPosition() == 0){
                        manager_addrestaurant2();
                    }
                    manager_addrestaurant();
                    /*if (snum >= 1) {
                        manager_addstyle1();
                        if (snum >= 2) {
                            manager_addstyle2();
                            if (snum >= 3) {
                                manager_addstyle3();
                                if (snum >= 4) {
                                    manager_addstyle4();
                                    if (snum >= 5) {
                                        manager_addstyle5();
                                        if (snum >= 6) {
                                            manager_addstyle6();
                                            if (snum >= 7) {
                                                manager_addstyle7();
                                                if (snum >= 8) {
                                                    manager_addstyle8();
                                                    if (snum >= 9) {
                                                        manager_addstyle9();
                                                        if (snum >= 10) {
                                                            manager_addstyle10();
                                                            if (snum >= 11) {
                                                                manager_addstyle11();
                                                                if (snum >= 12) {
                                                                    manager_addstyle12();
                                                                    if (snum >= 13) {
                                                                        manager_addstyle13();
                                                                        if (snum >= 14) {
                                                                            manager_addstyle14();
                                                                            if (snum >= 15) {
                                                                                manager_addstyle15();
                                                                                if (snum >= 16) {
                                                                                    manager_addstyle16();
                                                                                    if (snum >= 17) {
                                                                                        manager_addstyle17();
                                                                                        if (snum >= 18) {
                                                                                            manager_addstyle18();
                                                                                            if (snum >= 19) {
                                                                                                manager_addstyle19();
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (pnum >= 1) {
                        manager_addpayment1();
                        if (pnum >= 2) {
                            manager_addpayment2();
                            if (pnum >= 3) {
                                manager_addpayment3();
                            }
                        }
                    }*/
                    Intent intent = new Intent();
                    intent.setClass(manager2.this, manager5.class);
                    ID = num.toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", ID);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
            intent.setClass(manager2.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
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
    private void getmeal() {
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
        String path=ApiConfig.url("getmeallist.php"); //192.168.1.24需改成自己的ip
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
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String meal = info.getString("meal");
                result4 = meal.toString();
                meallist.add(result4);
            }
        }
        return sb.toString();
    }

    private void manager_addrestaurant() {
        String ID = num.toString();
        String name = nameET.getText().toString();
        String city = spinner1.getSelectedItem().toString();
        String district = spinner2.getSelectedItem().toString();
        String address = addressET.getText().toString();
        String phone = phoneET.getText().toString();
        String price = spinner3.getSelectedItem().toString();
        String businesshours = businesshoursET.getText().toString();
        String meal = spinner4.getSelectedItem().toString();
        String wifi = spinner5.getSelectedItem().toString();
        String accessibility = spinner6.getSelectedItem().toString();
        String delivery = spinner7.getSelectedItem().toString();
        String url = urlET.getText().toString();
        String fanpage = fanpageET.getText().toString();
        String picurl = picurlET.getText().toString();
        if(spinner3.getSelectedItemPosition() == 0){
            price = "";
        }
        if(spinner4.getSelectedItemPosition() == 0){
            meal = "";
        }
        if(spinner5.getSelectedItemPosition() == 0){
            wifi = "";
        }
        if(spinner6.getSelectedItemPosition() == 0){
            accessibility = "";
        }
        if(spinner7.getSelectedItemPosition() == 0){
            delivery = "";
        }
        try {
            jsonObject.put("ID", ID);
            jsonObject.put("Name", name);
            jsonObject.put("City", city);
            jsonObject.put("District", district);
            jsonObject.put("Address", address);
            jsonObject.put("Phone", phone);
            jsonObject.put("Price", price);
            jsonObject.put("BusinessHours", businesshours);
            jsonObject.put("Meal", meal);
            jsonObject.put("WiFi", wifi);
            jsonObject.put("Accessibility", accessibility);
            jsonObject.put("Delivery", delivery);
            jsonObject.put("URL", url);
            jsonObject.put("FanPage", fanpage);
            jsonObject.put("picurl", picurl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send();
    }

    private void send() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost();
            }
        }).start();
    }

    JSONObject jsonObject = new JSONObject();

    private void executeHttpPost() {
        String path = ApiConfig.url("manager_addrestaurant.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addrestaurant2() {
        String ID = num.toString();
        String name = nameET.getText().toString();
        String city = spinner1.getSelectedItem().toString();
        String district = spinner2.getSelectedItem().toString();
        String address = addressET.getText().toString();
        String phone = phoneET.getText().toString();
        String price = spinner3.getSelectedItem().toString();
        String businesshours = businesshoursET.getText().toString();
        String meal = spinner4.getSelectedItem().toString();
        String wifi = spinner5.getSelectedItem().toString();
        String accessibility = spinner6.getSelectedItem().toString();
        String delivery = spinner7.getSelectedItem().toString();
        String url = urlET.getText().toString();
        String fanpage = fanpageET.getText().toString();
        String picurl = picurlET.getText().toString();
        if(spinner3.getSelectedItemPosition() == 0){
            price = "";
        }
        if(spinner4.getSelectedItemPosition() == 0){
            meal = "";
        }
        if(spinner5.getSelectedItemPosition() == 0){
            wifi = "";
        }
        if(spinner6.getSelectedItemPosition() == 0){
            accessibility = "";
        }
        if(spinner7.getSelectedItemPosition() == 0){
            delivery = "";
        }
        try {
            jsonObject2.put("ID", ID);
            jsonObject2.put("Name", name);
            jsonObject2.put("City", city);
            jsonObject2.put("District", district);
            jsonObject2.put("Address", address);
            jsonObject2.put("Phone", phone);
            jsonObject2.put("Price", price);
            jsonObject2.put("BusinessHours", businesshours);
            jsonObject2.put("Meal", meal);
            jsonObject2.put("WiFi", wifi);
            jsonObject2.put("Accessibility", accessibility);
            jsonObject2.put("Delivery", delivery);
            jsonObject2.put("URL", url);
            jsonObject2.put("FanPage", fanpage);
            jsonObject2.put("picurl", picurl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send2();
    }

    private void send2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost2();
            }
        }).start();
    }

    JSONObject jsonObject2 = new JSONObject();

    private void executeHttpPost2() {
        String path = ApiConfig.url("manager_addrestaurant2.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject2.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void manager_addstyle1() {
        String ID = IDET.getText().toString();
        String style = style1ET.getText().toString();
        try {
            jsonObject2.put("ID", ID);
            jsonObject2.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send2();
    }

    private void send2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost2();
            }
        }).start();
    }

    JSONObject jsonObject2 = new JSONObject();

    private void executeHttpPost2() {
        String path = ApiConfig.url("manager_addstyle1.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject2.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle2() {
        String ID = IDET.getText().toString();
        String style = style2ET.getText().toString();
        try {
            jsonObject3.put("ID", ID);
            jsonObject3.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send3();
    }

    private void send3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost3();
            }
        }).start();
    }

    JSONObject jsonObject3 = new JSONObject();

    private void executeHttpPost3() {
        String path = ApiConfig.url("manager_addstyle2.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject3.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle3() {
        String ID = IDET.getText().toString();
        String style = style3ET.getText().toString();
        try {
            jsonObject4.put("ID", ID);
            jsonObject4.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send4();
    }

    private void send4() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost4();
            }
        }).start();
    }

    JSONObject jsonObject4 = new JSONObject();

    private void executeHttpPost4() {
        String path = ApiConfig.url("manager_addstyle3.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject4.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle4() {
        String ID = IDET.getText().toString();
        String style = style4ET.getText().toString();
        try {
            jsonObject5.put("ID", ID);
            jsonObject5.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send5();
    }

    private void send5() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost5();
            }
        }).start();
    }

    JSONObject jsonObject5 = new JSONObject();

    private void executeHttpPost5() {
        String path = ApiConfig.url("manager_addstyle4.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject5.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle5() {
        String ID = IDET.getText().toString();
        String style = style5ET.getText().toString();
        try {
            jsonObject6.put("ID", ID);
            jsonObject6.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send6();
    }

    private void send6() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost6();
            }
        }).start();
    }

    JSONObject jsonObject6 = new JSONObject();

    private void executeHttpPost6() {
        String path = ApiConfig.url("manager_addstyle5.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject6.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle6() {
        String ID = IDET.getText().toString();
        String style = style6ET.getText().toString();
        try {
            jsonObject7.put("ID", ID);
            jsonObject7.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send7();
    }

    private void send7() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost7();
            }
        }).start();
    }

    JSONObject jsonObject7 = new JSONObject();

    private void executeHttpPost7() {
        String path = ApiConfig.url("manager_addstyle6.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject7.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle7() {
        String ID = IDET.getText().toString();
        String style = style7ET.getText().toString();
        try {
            jsonObject8.put("ID", ID);
            jsonObject8.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send8();
    }

    private void send8() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost8();
            }
        }).start();
    }

    JSONObject jsonObject8 = new JSONObject();

    private void executeHttpPost8() {
        String path = ApiConfig.url("manager_addstyle7.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject8.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle8() {
        String ID = IDET.getText().toString();
        String style = style8ET.getText().toString();
        try {
            jsonObject9.put("ID", ID);
            jsonObject9.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send9();
    }

    private void send9() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost9();
            }
        }).start();
    }

    JSONObject jsonObject9 = new JSONObject();

    private void executeHttpPost9() {
        String path = ApiConfig.url("manager_addstyle8.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject9.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle9() {
        String ID = IDET.getText().toString();
        String style = style9ET.getText().toString();
        try {
            jsonObject10.put("ID", ID);
            jsonObject10.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send10();
    }

    private void send10() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost10();
            }
        }).start();
    }

    JSONObject jsonObject10 = new JSONObject();

    private void executeHttpPost10() {
        String path = ApiConfig.url("manager_addstyle9.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject10.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle10() {
        String ID = IDET.getText().toString();
        String style = style10ET.getText().toString();
        try {
            jsonObject11.put("ID", ID);
            jsonObject11.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send11();
    }

    private void send11() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost11();
            }
        }).start();
    }

    JSONObject jsonObject11 = new JSONObject();

    private void executeHttpPost11() {
        String path = ApiConfig.url("manager_addstyle10.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject11.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle11() {
        String ID = IDET.getText().toString();
        String style = style11ET.getText().toString();
        try {
            jsonObject12.put("ID", ID);
            jsonObject12.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send12();
    }

    private void send12() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost12();
            }
        }).start();
    }

    JSONObject jsonObject12 = new JSONObject();

    private void executeHttpPost12() {
        String path = ApiConfig.url("manager_addstyle11.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject12.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle12() {
        String ID = IDET.getText().toString();
        String style = style12ET.getText().toString();
        try {
            jsonObject13.put("ID", ID);
            jsonObject13.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send13();
    }

    private void send13() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost13();
            }
        }).start();
    }

    JSONObject jsonObject13 = new JSONObject();

    private void executeHttpPost13() {
        String path = ApiConfig.url("manager_addstyle12.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject13.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle13() {
        String ID = IDET.getText().toString();
        String style = style13ET.getText().toString();
        try {
            jsonObject14.put("ID", ID);
            jsonObject14.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send14();
    }

    private void send14() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost14();
            }
        }).start();
    }

    JSONObject jsonObject14 = new JSONObject();

    private void executeHttpPost14() {
        String path = ApiConfig.url("manager_addstyle13.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject14.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle14() {
        String ID = IDET.getText().toString();
        String style = style14ET.getText().toString();
        try {
            jsonObject15.put("ID", ID);
            jsonObject15.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send15();
    }

    private void send15() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost15();
            }
        }).start();
    }

    JSONObject jsonObject15 = new JSONObject();

    private void executeHttpPost15() {
        String path = ApiConfig.url("manager_addstyle14.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject15.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle15() {
        String ID = IDET.getText().toString();
        String style = style15ET.getText().toString();
        try {
            jsonObject16.put("ID", ID);
            jsonObject16.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send16();
    }

    private void send16() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost16();
            }
        }).start();
    }

    JSONObject jsonObject16 = new JSONObject();

    private void executeHttpPost16() {
        String path = ApiConfig.url("manager_addstyle15.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject16.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle16() {
        String ID = IDET.getText().toString();
        String style = style16ET.getText().toString();
        try {
            jsonObject17.put("ID", ID);
            jsonObject17.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send17();
    }

    private void send17() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost17();
            }
        }).start();
    }

    JSONObject jsonObject17 = new JSONObject();

    private void executeHttpPost17() {
        String path = ApiConfig.url("manager_addstyle16.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject17.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle17() {
        String ID = IDET.getText().toString();
        String style = style17ET.getText().toString();
        try {
            jsonObject18.put("ID", ID);
            jsonObject18.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send18();
    }

    private void send18() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost18();
            }
        }).start();
    }

    JSONObject jsonObject18 = new JSONObject();

    private void executeHttpPost18() {
        String path = ApiConfig.url("manager_addstyle17.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject18.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle18() {
        String ID = IDET.getText().toString();
        String style = style18ET.getText().toString();
        try {
            jsonObject19.put("ID", ID);
            jsonObject19.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send19();
    }

    private void send19() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost19();
            }
        }).start();
    }

    JSONObject jsonObject19 = new JSONObject();

    private void executeHttpPost19() {
        String path = ApiConfig.url("manager_addstyle18.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject19.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addstyle19() {
        String ID = IDET.getText().toString();
        String style = style19ET.getText().toString();
        try {
            jsonObject20.put("ID", ID);
            jsonObject20.put("style", style);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send20();
    }

    private void send20() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost20();
            }
        }).start();
    }

    JSONObject jsonObject20 = new JSONObject();

    private void executeHttpPost20() {
        String path = ApiConfig.url("manager_addstyle19.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject20.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addpayment1() {
        String ID = IDET.getText().toString();
        String payment = payment1ET.getText().toString();
        try {
            jsonObject21.put("ID", ID);
            jsonObject21.put("payment", payment);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send21();
    }

    private void send21() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost21();
            }
        }).start();
    }

    JSONObject jsonObject21 = new JSONObject();

    private void executeHttpPost21() {
        String path = ApiConfig.url("manager_addpayment1.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject21.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addpayment2() {
        String ID = IDET.getText().toString();
        String payment = payment2ET.getText().toString();
        try {
            jsonObject22.put("ID", ID);
            jsonObject22.put("payment", payment);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send22();
    }

    private void send22() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost22();
            }
        }).start();
    }

    JSONObject jsonObject22 = new JSONObject();

    private void executeHttpPost22() {
        String path = ApiConfig.url("manager_addpayment2.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject22.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void manager_addpayment3() {
        String ID = IDET.getText().toString();
        String payment = payment3ET.getText().toString();
        try {
            jsonObject23.put("ID", ID);
            jsonObject23.put("payment", payment);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send23();
    }

    private void send23() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost23();
            }
        }).start();
    }

    JSONObject jsonObject23 = new JSONObject();

    private void executeHttpPost23() {
        String path = ApiConfig.url("manager_addpayment3.php");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);  //開啟輸出流，以便向伺服器提交資料
            conn.setDoInput(true);  //開啟輸入流，以便從伺服器獲取資料
            conn.setUseCaches(false);//使用Post方式不能使用快取
            conn.setRequestMethod("POST");  //設定以Post方式提交資料
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("accept", "application/json");

            // 往伺服器裡面傳送資料
            String Json = jsonObject23.toString();

            System.out.println("-----------" + Json);

            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 設定檔案長度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                Log.d("upload: ", "doJsonPost: " + conn.getResponseCode());//如輸出200，則對了
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}