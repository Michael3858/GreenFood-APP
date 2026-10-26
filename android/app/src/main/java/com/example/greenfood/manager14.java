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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class manager14 extends AppCompatActivity {

    TextView name, businesshours, address, phone, price, meal, style, payment, delivery, wifi, accessibility, fanpage, url, food, showstar;
    String ID, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15, result16, result17, result18;
    ArrayList<String> styleList = new ArrayList<String>();
    ArrayList<String> paymentList = new ArrayList<String>();
    ArrayList<String> foodList = new ArrayList<String>();
    ArrayList<String> countryList = new ArrayList<String>();
    ImageView pic;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager14);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");

        pic = findViewById(R.id.pic);
        name = findViewById(R.id.name);
        businesshours = findViewById(R.id.businesshours);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        price = findViewById(R.id.price);
        meal = findViewById(R.id.meal);
        style = findViewById(R.id.style);
        payment = findViewById(R.id.payment);
        delivery = findViewById(R.id.delivery);
        wifi = findViewById(R.id.wifi);
        accessibility = findViewById(R.id.accessibility);
        fanpage = (TextView) findViewById(R.id.fanpage);
        url = (TextView) findViewById(R.id.url);
        food = findViewById(R.id.food);
        confirm = findViewById(R.id.confirm);
        Thread thread = new Thread(mutiThread);
        thread.start();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager14.this, manager1.class);
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
            intent.setClass(manager14.this, manager1.class);
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
                    String styleStr = "";
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
                    }
                    name.setText("餐廳名稱:" + result1);
                    businesshours.setText("營業時間:\n" + result2);
                    address.setText("地址:" + result15 + result16 + result3);
                    phone.setText("電話:" + result4);
                    price.setText("價格分級:" + result5);
                    meal.setText("用餐方式:" + result6);
                    style.setText("風格:" + styleStr);
                    payment.setText("付款方式:" + paymentStr);
                    delivery.setText("外送:" + result9);
                    wifi.setText("WI-FI:" + result10);
                    accessibility.setText("無障礙:" + result11);
                    fanpage.setText("粉絲專頁:" + result12);
                    fanpage.setAutoLinkMask(Linkify.WEB_URLS);
                    fanpage.setMovementMethod(LinkMovementMethod.getInstance());
                    url.setText("google map:" + result13);
                    url.setAutoLinkMask(Linkify.WEB_URLS);
                    url.setMovementMethod(LinkMovementMethod.getInstance());
                    Picasso.with(manager14.this).load(result14).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).resize(1240, 1563).centerInside().into(pic);
                    food.setText("食材來源:" + foodStr);
                }
            });
        }
    };
}