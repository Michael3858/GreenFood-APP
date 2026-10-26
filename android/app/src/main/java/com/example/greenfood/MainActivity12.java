package com.example.greenfood;

import static java.lang.Float.valueOf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity12 extends AppCompatActivity {

    TextView name, businesshours, address, phone, price, meal, style, payment, delivery, wifi, accessibility, fanpage, url, food, showstar, review;
    String ID, result, result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, result13, result14, result15, result16, result17, result18, result19, result20, result21, result22, result23, result24;
    ArrayList<String> styleList = new ArrayList<String>();
    ArrayList<String> paymentList = new ArrayList<String>();
    ArrayList<String> foodList = new ArrayList<String>();
    ArrayList<String> countryList = new ArrayList<String>();
    ArrayList<String> accList = new ArrayList<String>();
    ArrayList<String> commenttimeList = new ArrayList<String>();
    ArrayList<String> commentaccList = new ArrayList<String>();
    ArrayList<String> commentList = new ArrayList<String>();
    ArrayList<String> staraccList = new ArrayList<String>();
    ArrayList<String> starList = new ArrayList<String>();
    RatingBar ratingBar;
    String Account, star;
    ImageButton back, favorite;
    ImageView pic;
    FloatingActionButton comment;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
    Date curDate;
    String str;
    EditText usercomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");

        SharedPreferences user = getSharedPreferences("account", MODE_PRIVATE);
        Account = user.getString("acc", "");

        back = (ImageButton) findViewById(R.id.back);
        favorite = (ImageButton) findViewById(R.id.favorite);
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
        ratingBar = findViewById(R.id.ratingBar);
        showstar = findViewById(R.id.showstar);
        comment = findViewById(R.id.comment);
        review = findViewById(R.id.review);
        Thread thread = new Thread(mutiThread);
        thread.start();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = String.valueOf(rating);
                showstar.setText("給予評分:"+star);
                evaluate();
                again();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread1 = new Thread(mutiThread1);
                thread1.start();
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
    }
    private void showCommentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity12.this).setTitle("評論");
        View view = View.inflate(MainActivity12.this, R.layout.comment_dialog, null);
        builder.setView(view);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String c = usercomment.getText().toString();
                if(c.contains("http")){
                    Toast toast = Toast.makeText(MainActivity12.this, "請勿輸入連結!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(MainActivity12.this, "留言成功", Toast.LENGTH_SHORT);
                    toast.show();
                    curDate = new Date(System.currentTimeMillis());
                    str = formatter.format(curDate);
                    try {
                        jsonObject.put("account", Account);
                        jsonObject.put("ID", ID);
                        jsonObject.put("comment", c);
                        jsonObject.put("time", str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ;
                    send();
                }
            }
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog comm = builder.create();
        comm.show();

        usercomment = (EditText) comm.findViewById(R.id.usercomment);

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

        String path = ApiConfig.url("comment.php");
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

    private Runnable mutiThread = new Runnable(){
        public void run()
        {
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
                String post_data = URLEncoder.encode("ID", "UTF-8")+"="+URLEncoder.encode(ID, "UTF-8");
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
                            String acc = info.getString("Account");
                            String commentTime = info.getString("time");
                            String commentAcc = info.getString("account");
                            String comment = info.getString("comment");
                            String starAcc = info.getString("Cacc");
                            String starNum = info.getString("StarNum");
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
                            result19 = acc.toString();
                            result20 = commentTime.toString();
                            result21 = commentAcc.toString();
                            result22 = comment.toString();
                            result23 = starAcc.toString();
                            result24 = starNum.toString();
                            styleList.add(result7);
                            paymentList.add(result8);
                            foodList.add(result17);
                            countryList.add(result18);
                            accList.add(result19);
                            commenttimeList.add(result20);
                            commentaccList.add(result21);
                            commentList.add(result22);
                            staraccList.add(result23);
                            starList.add(result24);
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
                result19 = e.toString();
                result20 = e.toString();
                result21 = e.toString();
                result22 = e.toString();
                result23 = e.toString();
                result24 = e.toString();
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
                    boolean f = false;
                    for(int i = 0; i < accList.size(); i++){
                        if(Account.equals(accList.get(i))){
                            f = true;
                            break;
                        }
                    }
                    if(f == false){
                        favorite.setImageResource(R.drawable.notfavorite);
                    }
                    else{
                        favorite.setImageResource(R.drawable.favorite);
                    }
                    String commentStr = "";
                    if(commentList.get(0).equals("null")){
                        commentStr += "\n暫無留言";
                    }
                    else {
                        for (int i = 0; i < commentList.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (commenttimeList.get(i).equals(commenttimeList.get(j)) && commentaccList.get(i).equals(commentaccList.get(j)) && commentList.get(i).equals(commentList.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                commentStr = commentStr + "\n\n" + commenttimeList.get(i) + "\n" + commentaccList.get(i) + ":\n" + commentList.get(i);
                            }
                        }
                    }
                    if(!starList.get(0).equals("null")){
                        for(int i = 0; i < staraccList.size(); i++){
                            if(staraccList.get(i).equals(Account)){
                                float sn = Float.parseFloat(starList.get(i));
                                ratingBar.setRating((float) sn);
                            }
                        }
                    }
                    else{
                        ratingBar.setRating((float) 0);
                    }
                    name.setText("餐廳名稱:"+result1);
                    businesshours.setText("營業時間:\n"+result2);
                    address.setText("地址:"+result15+result16+result3);
                    phone.setText("電話:"+result4);
                    price.setText("價格分級:"+result5);
                    meal.setText("用餐方式:"+result6);
                    style.setText("風格:"+styleStr);
                    payment.setText("付款方式:"+paymentStr);
                    delivery.setText("外送:"+result9);
                    wifi.setText("WI-FI:"+result10);
                    accessibility.setText("無障礙:"+result11);
                    fanpage.setText("粉絲專頁:"+result12);
                    fanpage.setAutoLinkMask(Linkify.WEB_URLS);
                    fanpage.setMovementMethod(LinkMovementMethod.getInstance());
                    url.setText("google map:"+result13);
                    url.setAutoLinkMask(Linkify.WEB_URLS);
                    url.setMovementMethod(LinkMovementMethod.getInstance());
                    Picasso.with(MainActivity12.this).load(result14).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).resize(1240, 1563).centerInside().into(pic);
                    food.setText("食材來源:"+foodStr);
                    review.setText("留言區:"+commentStr);
                }
            });
        }
    };
    private Runnable mutiThread1 = new Runnable(){
        public void run()
        {
            try {
                URL url = new URL(ApiConfig.url("searchfavorite.php"));
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
                String post_data = URLEncoder.encode("ID", "UTF-8")+"="+URLEncoder.encode(ID, "UTF-8")+"&"
                        +URLEncoder.encode("Account", "UTF-8")+"="+URLEncoder.encode(Account, "UTF-8");
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
                    String box = "";
                    String line = null; // 宣告讀取用的字串
                    while((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                    }
                    inputStream.close(); // 關閉輸入串流
                    result = box;
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch(Exception e) { // 如果出事，回傳錯誤訊息
                result = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Toast toast = Toast.makeText(MainActivity12.this, result, Toast.LENGTH_SHORT);
                    toast.show();*/
                    if(result.equals("yes\n")){
                        favorite.setImageResource(R.drawable.notfavorite);
                        Toast toast = Toast.makeText(MainActivity12.this, "已從我的最愛中刪除!", Toast.LENGTH_SHORT);
                        toast.show();
                        deletefavorite();
                    }
                    else if(result.equals("no\n")){
                        favorite.setImageResource(R.drawable.favorite);
                        Toast toast = Toast.makeText(MainActivity12.this, "已加入我的最愛!", Toast.LENGTH_SHORT);
                        toast.show();
                        favorite();
                    }
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
            intent.setClass(MainActivity12.this, MainActivity4.class);
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

    private void evaluate() {
        try {
            jsonObject1.put("account", Account);
            jsonObject1.put("ID", ID);
            jsonObject1.put("StarNum", star);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ;
        send1();
    }

    private void send1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                executeHttpPost1();
            }
        }).start();
    }

    JSONObject jsonObject1 = new JSONObject();

    private void executeHttpPost1() {
        String path = ApiConfig.url("evaluate.php");
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
            String Json = jsonObject1.toString();

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
    private void again() {
        try {
            jsonObject2.put("account", Account);
            jsonObject2.put("ID", ID);
            jsonObject2.put("StarNum", star);
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
        String path = ApiConfig.url("again.php");
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
    private void favorite() {
        try {
            jsonObject3.put("account", Account);
            jsonObject3.put("ID", ID);
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
        String path = ApiConfig.url("addfavorite.php");
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
    private void deletefavorite() {
        try {
            jsonObject4.put("account", Account);
            jsonObject4.put("ID", ID);
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
        String path = ApiConfig.url("deletefavorite.php");
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
}