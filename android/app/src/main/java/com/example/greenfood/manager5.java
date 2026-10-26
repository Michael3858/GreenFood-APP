package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class manager5 extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3, spinner4;
    Button add1, add2, add3, confirm;
    String ID1;
    ArrayList<String> stylelist = new ArrayList<>();
    ArrayList<String> paymentlist = new ArrayList<>();
    ArrayList<String> foodlist = new ArrayList<>();
    ArrayList<String> countrylist = new ArrayList<>();
    String result1, result2, result3, result4, response1, response2, response3, response4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager5);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        confirm = findViewById(R.id.confirm);

        Bundle bundle = getIntent().getExtras();
        ID1 = bundle.getString("ID");

        stylelist.add("請選擇風格");
        paymentlist.add("請選擇付款方式");
        foodlist.add("請選擇食材");
        countrylist.add("請選擇來源");
        getstyle();
        getpayment();
        getfood();
        getcountry();

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stylelist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner1.getSelectedItemPosition() == 0) {
                    Toast toast = Toast.makeText(manager5.this, "請選擇新餐廳的風格", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager5.this, "新增成功!", Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(manager5.this, "請選擇新餐廳的付款方式", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager5.this, "新增成功!", Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(manager5.this, "請選擇新餐廳的食材來源", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(manager5.this, "新增成功!", Toast.LENGTH_SHORT);
                    toast.show();
                    manager_addfoodcountry();
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager5.this, manager14.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID1);
                intent.putExtras(bundle);
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
            intent.setClass(manager5.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
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