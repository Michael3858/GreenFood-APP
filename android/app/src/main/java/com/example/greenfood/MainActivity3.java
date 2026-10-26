package com.example.greenfood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.net.MalformedURLException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private ImageButton back;
    private Button btn;
    private EditText AccountET, PasswordET, PasswordET2, NameET, DateET;
    private RadioGroup Gender;
    ArrayList<String> accountlist = new ArrayList<>();
    String result, response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getallaccount();

        AccountET = findViewById(R.id.Account);
        PasswordET = findViewById(R.id.Password);
        PasswordET2 = findViewById(R.id.Password2);
        NameET = findViewById(R.id.Name);
        DateET = findViewById(R.id.date);
        Gender = findViewById(R.id.radioGroup);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Account = AccountET.getText().toString();
                String Password = PasswordET.getText().toString();
                String Password2 = PasswordET2.getText().toString();
                String Name = NameET.getText().toString();
                String Date = DateET.getText().toString();
                int same = 0;
                for(int i = 0; i < accountlist.size(); i++){
                    if(Account.equals(accountlist.get(i))){
                        same = 1;
                        break;
                    }
                }
                if(same == 1){
                    Toast.makeText(MainActivity3.this, "帳號已被使用過!請重新輸入", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Account)) {  //若沒輸入的話提示一下用戶
                    Toast.makeText(MainActivity3.this, "請輸入帳號", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(MainActivity3.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password2) || !Password.equals(Password2)) {
                    Toast.makeText(MainActivity3.this, "請重新輸入密碼", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(MainActivity3.this, "請輸入使用者名稱", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Date) || Date.length() != 10 || Date.charAt(4) != '/' || Date.charAt(7) != '/') {
                    Toast.makeText(MainActivity3.this, "請輸入出生年月日", Toast.LENGTH_SHORT).show();
                } else if (Gender.getCheckedRadioButtonId() != R.id.men && Gender.getCheckedRadioButtonId() != R.id.women) {
                    Toast.makeText(MainActivity3.this, "請選擇性別", Toast.LENGTH_SHORT).show();
                } else {
                    signup();
                    Toast toast = Toast.makeText(MainActivity3.this, "註冊成功!", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent();
                    finish();
                }
            }
        });

    }

    private void signup() {
        String account = AccountET.getText().toString();
        String password = PasswordET.getText().toString();
        String name = NameET.getText().toString();
        String date = DateET.getText().toString();
        String gender = "";
        if (Gender.getCheckedRadioButtonId() == R.id.men) {
            gender = "男";
        } else if (Gender.getCheckedRadioButtonId() == R.id.women) {
            gender = "女";
        }
        try {
            jsonObject.put("account", account);
            jsonObject.put("password", password);
            jsonObject.put("name", name);
            jsonObject.put("date", date);
            jsonObject.put("gender", gender);
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
        String path = ApiConfig.url("signup.php");
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
    private void getallaccount() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        response = executeHttpGet();
                    }
                }

        ).start();
    }

    private String executeHttpGet() {

        HttpURLConnection con=null;
        InputStream in=null;
        String path=ApiConfig.url("getallaccount.php"); //192.168.1.24需改成自己的ip
        try {
            con= (HttpURLConnection) new URL(path).openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            if(con.getResponseCode()==200){

                in=con.getInputStream();
                return parseInfo(in);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseInfo(InputStream in) throws IOException, JSONException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line=null;
        while ((line=br.readLine())!=null){
            sb.append(line+"\n");
            JSONArray dataJson = new JSONArray(line);
            for(int i = 0; i < dataJson.length(); i++){
                JSONObject info = dataJson.getJSONObject(i);
                String account = info.getString("account");
                result = account.toString();
                accountlist.add(result);
            }
        }
        return sb.toString();
    }
}