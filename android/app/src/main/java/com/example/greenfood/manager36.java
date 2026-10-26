package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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

public class manager36 extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3, spinner4;
    Button add1, add2, add3, confirm;
    ImageButton back;
    TextView style, payment, food;
    String ID1;
    ArrayList<String> stylelist = new ArrayList<>();
    ArrayList<String> paymentlist = new ArrayList<>();
    ArrayList<String> foodlist = new ArrayList<>();
    ArrayList<String> countrylist = new ArrayList<>();
    ArrayList<String> styleList2 = new ArrayList<String>();
    ArrayList<String> paymentList2 = new ArrayList<String>();
    ArrayList<String> foodList2 = new ArrayList<String>();
    ArrayList<String> countryList2 = new ArrayList<String>();
    String result1, result2, result3, result4, result5, result6, result7, result8, response1, response2, response3, response4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager36);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        confirm = findViewById(R.id.confirm);
        style = findViewById(R.id.style);
        payment = findViewById(R.id.payment);
        food = findViewById(R.id.food);

        Bundle bundle = getIntent().getExtras();
        ID1 = bundle.getString("ID");

        Thread thread = new Thread(mutiThread);
        thread.start();

        stylelist.add("請選擇風格");
        paymentlist.add("請選擇付款方式");
        foodlist.add("請選擇食材");
        countrylist.add("請選擇來源");
        getstyle();
        getpayment();
        getfood();
        getcountry();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stylelist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner1.getSelectedItemPosition() == 0) {
                    Toast toast = Toast.makeText(manager36.this, "請選擇餐廳的風格", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager36.this, "新增成功!", Toast.LENGTH_SHORT);
                    toast.show();
                    manager_addstyle();
                }
            }
        });

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentlist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner2.getSelectedItemPosition() == 0) {
                    Toast toast = Toast.makeText(manager36.this, "請選擇餐廳的付款方式", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager36.this, "新增成功!", Toast.LENGTH_SHORT);
                    toast.show();
                    manager_addpayment();
                }
            }
        });

        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, foodlist);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countrylist);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner3.getSelectedItemPosition() == 0 || spinner4.getSelectedItemPosition() == 0) {
                    Toast toast = Toast.makeText(manager36.this, "請選擇餐廳的食材來源", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager36.this, "新增成功!", Toast.LENGTH_SHORT);
                    toast.show();
                    manager_addfoodcountry();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager36.this, manager1.class);
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
            intent.setClass(manager36.this, manager1.class);
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
                String post_data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID1, "UTF-8");
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
                            String Style = info.getString("style");
                            String Payment = info.getString("payment");
                            String Food = info.getString("food");
                            String Country = info.getString("country");
                            result5 = Style.toString();
                            result6 = Payment.toString();
                            result7 = Food.toString();
                            result8 = Country.toString();
                            styleList2.add(result5);
                            paymentList2.add(result6);
                            foodList2.add(result7);
                            countryList2.add(result8);
                        }
                    }
                    inputStream.close(); // 關閉輸入串流
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch (Exception e) { // 如果出事，回傳錯誤訊息
                result5 = e.toString();
                result6 = e.toString();
                result7 = e.toString();
                result8 = e.toString();
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    String styleStr = "";
                    if(styleList2.get(0).equals("null")){
                        styleStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < styleList2.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (styleList2.get(i).equals(styleList2.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                if (i != 0) {
                                    styleStr += "/";
                                }
                                styleStr += styleList2.get(i);
                            }
                        }
                    }
                    String paymentStr = "";
                    if(paymentList2.get(0).equals("null")){
                        paymentStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < paymentList2.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (paymentList2.get(i).equals(paymentList2.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                if (i != 0) {
                                    paymentStr += "/";
                                }
                                paymentStr += paymentList2.get(i);
                            }
                        }
                    }
                    String foodStr = "";
                    if (foodList2.get(0).equals("null")){
                        foodStr += "暫無資料";
                    }
                    else {
                        for (int i = 0; i < foodList2.size(); i++) {
                            int same = 0;
                            for (int j = 0; j < i; j++) {
                                if (foodList2.get(i).equals(foodList2.get(j)) && countryList2.get(i).equals(countryList2.get(j))) {
                                    same = 1;
                                }
                            }
                            if (same == 0) {
                                foodStr = foodStr + "\n" + foodList2.get(i) + "<-" + countryList2.get(i);
                            }
                        }
                    }
                    style.setText("現有風格:" + styleStr);
                    payment.setText("現有付款方式:" + paymentStr);
                    food.setText("現有食材來源:" + foodStr);
                }
            });
        }
    };

    private void getstyle() {
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

        HttpURLConnection con = null;
        InputStream in = null;
        String path = ApiConfig.url("getstylelist.php"); //192.168.1.24需改成自己的ip
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {

                in = con.getInputStream();
                return parseInfo1(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo1(InputStream in) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
            JSONArray dataJson = new JSONArray(line);
            for (int i = 0; i < dataJson.length(); i++) {
                JSONObject info = dataJson.getJSONObject(i);
                String style = info.getString("style");
                result1 = style.toString();
                stylelist.add(result1);
            }
        }
        return sb.toString();
    }

    private void getpayment() {
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

        HttpURLConnection con = null;
        InputStream in = null;
        String path = ApiConfig.url("getpaymentlist.php"); //192.168.1.24需改成自己的ip
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {

                in = con.getInputStream();
                return parseInfo2(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo2(InputStream in) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
            JSONArray dataJson = new JSONArray(line);
            for (int i = 0; i < dataJson.length(); i++) {
                JSONObject info = dataJson.getJSONObject(i);
                String payment = info.getString("payment");
                result2 = payment.toString();
                paymentlist.add(result2);
            }
        }
        return sb.toString();
    }

    private void getfood() {
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

        HttpURLConnection con = null;
        InputStream in = null;
        String path = ApiConfig.url("getfoodlist.php"); //192.168.1.24需改成自己的ip
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {

                in = con.getInputStream();
                return parseInfo3(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo3(InputStream in) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
            JSONArray dataJson = new JSONArray(line);
            for (int i = 0; i < dataJson.length(); i++) {
                JSONObject info = dataJson.getJSONObject(i);
                String food = info.getString("food");
                result3 = food.toString();
                foodlist.add(result3);
            }
        }
        return sb.toString();
    }

    private void getcountry() {
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

        HttpURLConnection con = null;
        InputStream in = null;
        String path = ApiConfig.url("getcountrylist.php"); //192.168.1.24需改成自己的ip
        try {
            con = (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {

                in = con.getInputStream();
                return parseInfo4(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo4(InputStream in) throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
            JSONArray dataJson = new JSONArray(line);
            for (int i = 0; i < dataJson.length(); i++) {
                JSONObject info = dataJson.getJSONObject(i);
                String country = info.getString("country");
                result4 = country.toString();
                countrylist.add(result4);
            }
        }
        return sb.toString();
    }

    private void manager_addstyle() {
        String ID = ID1.toString();
        String style = spinner1.getSelectedItem().toString();
        try {
            jsonObject.put("ID", ID);
            jsonObject.put("style", style);
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
        String path = ApiConfig.url("manager_addstyle.php");
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

    private void manager_addpayment() {
        String ID = ID1.toString();
        String payment = spinner2.getSelectedItem().toString();
        try {
            jsonObject2.put("ID", ID);
            jsonObject2.put("payment", payment);
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
        String path = ApiConfig.url("manager_addpayment.php");
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

    private void manager_addfoodcountry() {
        String ID = ID1.toString();
        String food = spinner3.getSelectedItem().toString();
        String country = spinner4.getSelectedItem().toString();
        try {
            jsonObject3.put("ID", ID);
            jsonObject3.put("food", food);
            jsonObject3.put("country", country);
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
        String path = ApiConfig.url("manager_addfoodcountry.php");
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
}