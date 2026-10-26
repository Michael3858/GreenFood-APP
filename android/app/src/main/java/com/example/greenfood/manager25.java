package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class manager25 extends AppCompatActivity {

    TextView name, businesshours, address, phone, price, meal, style, payment, delivery, wifi, accessibility, fanpage, url, food, picurl;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7;
    EditText nameET, addressET, phoneET, businesshoursET,urlET, fanpageET, picurlET;
    String ID, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15, result16, result17, result18, result19, result20, result21, result22, response1, response2, response3;
    ArrayList<String> allcitylist = new ArrayList<>();
    ArrayList<String> alldistrictlist = new ArrayList<>();
    ArrayList<String> citylist = new ArrayList<>();
    ArrayList<String> districtlist = new ArrayList<>();
    ArrayList<String> meallist = new ArrayList<>();
    ArrayList<String> styleList = new ArrayList<String>();
    ArrayList<String> paymentList = new ArrayList<String>();
    ArrayList<String> foodList = new ArrayList<String>();
    ArrayList<String> countryList = new ArrayList<String>();
    ImageView pic;
    ImageButton back;
    Button confirm;
    String city1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager25);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");

        pic = findViewById(R.id.pic);
        name = findViewById(R.id.name);
        businesshours = findViewById(R.id.businesshours);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        price = findViewById(R.id.price);
        meal = findViewById(R.id.meal);
        /*style = findViewById(R.id.style);
        payment = findViewById(R.id.payment);*/
        delivery = findViewById(R.id.delivery);
        wifi = findViewById(R.id.wifi);
        accessibility = findViewById(R.id.accessibility);
        fanpage = (TextView) findViewById(R.id.fanpage);
        url = (TextView) findViewById(R.id.url);
        picurl = findViewById(R.id.picurl);
        //food = findViewById(R.id.food);

        getalllocation();
        getcity();
        getmeal();
        citylist.add("請選擇修改後縣市");
        districtlist.add("請選擇修改後行政區");
        meallist.add("請選擇修改後用餐方式");

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
        Thread thread = new Thread(mutiThread);
        thread.start();
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
                districtlist.add("請選擇修改後行政區");
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

        String[] pricelist = {"請選擇修改後價格區間", "$", "$$", "$$$", "$$$$", "$$$$$"};
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pricelist);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, meallist);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        String[] wifilist = {"請選擇修改後有無Wi-Fi", "有", "無"};
        ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, wifilist);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        String[] accessibilitylist = {"請選擇修改後有無無障礙", "有", "無"};
        ArrayAdapter adapter6 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, accessibilitylist);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        String[] deliverylist = {"請選擇修改後有無外送", "有", "無"};
        ArrayAdapter adapter7 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, deliverylist);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner4.getSelectedItemPosition() == 0){
                    manager_reviserestaurant2();
                }
                else {
                    manager_reviserestaurant();
                }
                Toast toast = Toast.makeText(manager25.this, "修改成功!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent();
                /*intent.setClass(manager25.this, manager33.class);
                String ID1 = ID.toString();
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID1);
                intent.putExtras(bundle);*/
                intent.setClass(manager25.this, manager1.class);
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
            intent.setClass(manager25.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Runnable mutiThread = new Runnable() {
        public void run() {
            try {
                URL url = new URL(ApiConfig.url("restaurant.php"));
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
                String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                // 建立取得回應的物件
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream = connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String line = null; // 宣告讀取用的字串
                    while ((line = bufReader.readLine()) != null) {
                        JSONArray dataJson = new JSONArray(line);
                        for (int i = 0; i < dataJson.length(); i++) {
                            JSONObject info = dataJson.getJSONObject(i);
                            String Name = info.getString("Name");
                            String Businesshours = info.getString("BusinessHours");
                            String Address = info.getString("Address");
                            String Phone = info.getString("Phone");
                            String Price = info.getString("Price");
                            String Meal = info.getString("Meal");
                            String Style = info.getString("style");
                            String Payment = info.getString("payment");
                            String Delivery = info.getString("Delivery");
                            String Wifi = info.getString("WiFi");
                            String Accessibility = info.getString("Accessibility");
                            String Fanpage = info.getString("FanPage");
                            String URL = info.getString("URL");
                            String pictureURL = info.getString("pictureURL");
                            String City = info.getString("City");
                            String District = info.getString("District");
                            String Food = info.getString("food");
                            String Country = info.getString("country");
                            result1 = Name.toString();
                            result2 = Businesshours.toString();
                            result3 = Address.toString();
                            result4 = Phone.toString();
                            result5 = Price.toString();
                            result6 = Meal.toString();
                            result7 = Style.toString();
                            result8 = Payment.toString();
                            result9 = Delivery.toString();
                            result10 = Wifi.toString();
                            result11 = Accessibility.toString();
                            result12 = Fanpage.toString();
                            result13 = URL.toString();
                            result14 = pictureURL.toString();
                            result15 = City.toString();
                            result16 = District.toString();
                            result17 = Food.toString();
                            result18 = Country.toString();
                            styleList.add(result7);
                            paymentList.add(result8);
                            foodList.add(result17);
                            countryList.add(result18);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch (Exception e) { // 如果出事，回傳錯誤訊息
                result1 = e.toString();
                result2 = e.toString();
                result3 = e.toString();
                result4 = e.toString();
                result5 = e.toString();
                result6 = e.toString();
                result7 = e.toString();
                result8 = e.toString();
                result9 = e.toString();
                result10 = e.toString();
                result11 = e.toString();
                result12 = e.toString();
                result13 = e.toString();
                result14 = e.toString();
                result15 = e.toString();
                result16 = e.toString();
                result17 = e.toString();
                result18 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    if(result2.equals("") || result2.equals("null")){
                        result2 = "暫無資料";
                    }
                    if(result4.equals("") || result4.equals("null")){
                        result4 = "暫無資料";
                    }
                    if(result5.equals("") || result5.equals("null")){
                        result5 = "暫無資料";
                    }
                    if(result6.equals("") || result6.equals("null")){
                        result6 = "暫無資料";
                    }
                    if(result9.equals("") || result9.equals("null")){
                        result9 = "暫無資料";
                    }
                    if(result10.equals("") || result10.equals("null")){
                        result10 = "暫無資料";
                    }
                    if(result11.equals("") || result11.equals("null")){
                        result11 = "暫無資料";
                    }
                    if(result12.equals("") || result12.equals("null")){
                        result12 = "暫無資料";
                    }
                    if(result13.equals("") || result13.equals("null")){
                        result13 = "暫無資料";
                    }
                    if(result14.equals("") || result14.equals("null")){
                        result14 = ApiConfig.placeholderImage();
                    }
                    /*String styleStr = "";
                    if(styleList.get(0).equals("null")){
                        styleStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < styleList.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (styleList.get(i).equals(styleList.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                if (i != 0) {
                                    styleStr += "/";
                                }
                                styleStr += styleList.get(i);
                            }
                        }
                    }
                    String paymentStr = "";
                    if(paymentList.get(0).equals("null")){
                        paymentStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < paymentList.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (paymentList.get(i).equals(paymentList.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                if (i != 0) {
                                    paymentStr += "/";
                                }
                                paymentStr += paymentList.get(i);
                            }
                        }
                    }
                    String foodStr = "";
                    if (foodList.get(0).equals("null")){
                        foodStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < foodList.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (foodList.get(i).equals(foodList.get(j)) && countryList.get(i).equals(countryList.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                foodStr = foodStr + "\n" + foodList.get(i) + "<-" + countryList.get(i);
                            }
                        }
                    }*/
                    name.setText("餐廳名稱:" + result1);
                    businesshours.setText("營業時間:\n" + result2);
                    address.setText("地址:" + result15 + result16 + result3);
                    phone.setText("電話:" + result4);
                    price.setText("價格分級:" + result5);
                    meal.setText("用餐方式:" + result6);
                    //style.setText("風格:" + styleStr);
                    //payment.setText("付款方式:" + paymentStr);
                    delivery.setText("外送:" + result9);
                    wifi.setText("WI-FI:" + result10);
                    accessibility.setText("無障礙:" + result11);
                    fanpage.setText("粉絲專頁:" + result12);
                    fanpage.setAutoLinkMask(Linkify.WEB_URLS);
                    fanpage.setMovementMethod(LinkMovementMethod.getInstance());
                    url.setText("google map:" + result13);
                    url.setAutoLinkMask(Linkify.WEB_URLS);
                    url.setMovementMethod(LinkMovementMethod.getInstance());
                    picurl.setText("餐廳照片網址:" + result14);
                    Picasso.with(manager25.this).load(result14).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).resize(1240, 1563).centerInside().into(pic);
                    //food.setText("食材來源:" + foodStr);
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
                result19 = city.toString();
                citylist.add(result19);
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
                result20 = city.toString();
                result21 = district.toString();
                allcitylist.add(result20);
                alldistrictlist.add(result21);
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
                result22 = meal.toString();
                meallist.add(result22);
            }
        }
        return sb.toString();
    }

    private void manager_reviserestaurant() {
        String ID1 = ID.toString();
        String name1;
        if(nameET.getText().toString().equals("")){
            name1 = result1;
        }
        else {
            name1 = nameET.getText().toString();
        }
        String city1;
        if(spinner1.getSelectedItemPosition() == 0){
            city1 = result15;
        }
        else {
            city1 = spinner1.getSelectedItem().toString();
        }
        String district1;
        if(spinner2.getSelectedItemPosition() == 0){
            district1 = result16;
        }
        else {
            district1 = spinner2.getSelectedItem().toString();
        }
        String address1;
        if(addressET.getText().toString().equals("")){
            address1 = result3;
        }
        else {
            address1 = addressET.getText().toString();
        }
        String phone1;
        if(phoneET.getText().toString().equals("")){
            phone1 = result4;
        }
        else {
            phone1 = phoneET.getText().toString();
        }
        String price1;
        if(spinner3.getSelectedItemPosition() == 0){
            price1 = result5;
        }
        else {
            price1 = spinner3.getSelectedItem().toString();
        }
        String businesshours1;
        if(businesshoursET.getText().toString().equals("")){
            businesshours1 = result2;
        }
        else{
            businesshours1 = businesshoursET.getText().toString();
        }
        String meal1;
        meal1 = spinner4.getSelectedItem().toString();
        String wifi1;
        if(spinner5.getSelectedItemPosition() == 0){
            wifi1 = result10;
        }
        else {
            wifi1 = spinner5.getSelectedItem().toString();
        }
        String accessibility1;
        if(spinner6.getSelectedItemPosition() == 0){
            accessibility1 = result11;
        }
        else {
            accessibility1 = spinner6.getSelectedItem().toString();
        }
        String delivery1;
        if(spinner7.getSelectedItemPosition() == 0){
            delivery1 = result9;
        }
        else {
            delivery1 = spinner7.getSelectedItem().toString();
        }
        String url1;
        if(urlET.getText().toString().equals("")){
            url1 = result13;
        }
        else {
            url1 = urlET.getText().toString();
        }
        String fanpage1;
        if(fanpageET.getText().toString().equals("")){
            fanpage1 = result12;
        }
        else {
            fanpage1 = fanpageET.getText().toString();
        }
        String picurl1;
        if(picurlET.getText().toString().equals("")){
            picurl1 = result14;
        }
        else {
            picurl1 = picurlET.getText().toString();
        }
        try {
            jsonObject.put("ID1", ID1);
            jsonObject.put("Name1", name1);
            jsonObject.put("City1", city1);
            jsonObject.put("District1", district1);
            jsonObject.put("Address1", address1);
            jsonObject.put("Phone1", phone1);
            jsonObject.put("Price1", price1);
            jsonObject.put("BusinessHours1", businesshours1);
            jsonObject.put("Meal1", meal1);
            jsonObject.put("WiFi1", wifi1);
            jsonObject.put("Accessibility1", accessibility1);
            jsonObject.put("Delivery1", delivery1);
            jsonObject.put("URL1", url1);
            jsonObject.put("FanPage1", fanpage1);
            jsonObject.put("picurl1", picurl1);
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
        String path = ApiConfig.url("manager_reviserestaurant.php");
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
    private void manager_reviserestaurant2() {
        String ID1 = ID.toString();
        String name1;
        if(nameET.getText().toString().equals("")){
            name1 = result1;
        }
        else {
            name1 = nameET.getText().toString();
        }
        String city1;
        if(spinner1.getSelectedItemPosition() == 0){
            city1 = result15;
        }
        else {
            city1 = spinner1.getSelectedItem().toString();
        }
        String district1;
        if(spinner2.getSelectedItemPosition() == 0){
            district1 = result16;
        }
        else {
            district1 = spinner2.getSelectedItem().toString();
        }
        String address1;
        if(addressET.getText().toString().equals("")){
            address1 = result3;
        }
        else {
            address1 = addressET.getText().toString();
        }
        String phone1;
        if(phoneET.getText().toString().equals("")){
            phone1 = result4;
        }
        else {
            phone1 = phoneET.getText().toString();
        }
        String price1;
        if(spinner3.getSelectedItemPosition() == 0){
            price1 = result5;
        }
        else {
            price1 = spinner3.getSelectedItem().toString();
        }
        String businesshours1;
        if(businesshoursET.getText().toString().equals("")){
            businesshours1 = result2;
        }
        else{
            businesshours1 = businesshoursET.getText().toString();
        }
        String wifi1;
        if(spinner5.getSelectedItemPosition() == 0){
            wifi1 = result10;
        }
        else {
            wifi1 = spinner5.getSelectedItem().toString();
        }
        String accessibility1;
        if(spinner6.getSelectedItemPosition() == 0){
            accessibility1 = result11;
        }
        else {
            accessibility1 = spinner6.getSelectedItem().toString();
        }
        String delivery1;
        if(spinner7.getSelectedItemPosition() == 0){
            delivery1 = result9;
        }
        else {
            delivery1 = spinner7.getSelectedItem().toString();
        }
        String url1;
        if(urlET.getText().toString().equals("")){
            url1 = result13;
        }
        else {
            url1 = urlET.getText().toString();
        }
        String fanpage1;
        if(fanpageET.getText().toString().equals("")){
            fanpage1 = result12;
        }
        else {
            fanpage1 = fanpageET.getText().toString();
        }
        String picurl1;
        if(picurlET.getText().toString().equals("")){
            picurl1 = result14;
        }
        else {
            picurl1 = picurlET.getText().toString();
        }
        try {
            jsonObject2.put("ID1", ID1);
            jsonObject2.put("Name1", name1);
            jsonObject2.put("City1", city1);
            jsonObject2.put("District1", district1);
            jsonObject2.put("Address1", address1);
            jsonObject2.put("Phone1", phone1);
            jsonObject2.put("Price1", price1);
            jsonObject2.put("BusinessHours1", businesshours1);
            jsonObject2.put("WiFi1", wifi1);
            jsonObject2.put("Accessibility1", accessibility1);
            jsonObject2.put("Delivery1", delivery1);
            jsonObject2.put("URL1", url1);
            jsonObject2.put("FanPage1", fanpage1);
            jsonObject2.put("picurl1", picurl1);
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
        String path = ApiConfig.url("manager_reviserestaurant2.php");
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
}