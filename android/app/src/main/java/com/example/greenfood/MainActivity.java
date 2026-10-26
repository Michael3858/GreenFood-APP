package com.example.greenfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private Button button2, button3;
    private EditText AccountET, PasswordET;
    private CheckBox check;
    private String Account, Password;
    private String result1, result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccountET = findViewById(R.id.Account);
        PasswordET = findViewById(R.id.Password);
        check = findViewById(R.id.remember);
        output();

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    public void OnLogin(View view){
        Account=AccountET.getText().toString();
        Password=PasswordET.getText().toString();
        if(!BuildConfig.DEMO_ADMIN_ACCOUNT.isEmpty() && !BuildConfig.DEMO_ADMIN_PASSWORD.isEmpty()
                && Account.equals(BuildConfig.DEMO_ADMIN_ACCOUNT) && Password.equals(BuildConfig.DEMO_ADMIN_PASSWORD)){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,manager1.class);
            startActivity(intent);
        }
        else{
            Thread thread = new Thread(mutiThread);
            thread.start(); // 開始執行
        }
    }

    private Runnable mutiThread = new Runnable(){
        public void run()
        {
            try {
                String account = AccountET.getText().toString();
                String password = PasswordET.getText().toString();
                URL url = new URL(ApiConfig.url("login.php"));
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
                String post_data = URLEncoder.encode("account", "UTF-8")+"="+URLEncoder.encode(account, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
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
                        int i = dataJson.length()-1;
                        JSONObject info = dataJson.getJSONObject(i);
                        String acc = info.getString("account");
                        String pw = info.getString("password");
                        result1 = acc.toString();
                        result2 = pw.toString();
                    }
                    inputStream.close(); // 關閉輸入串流
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
                public void run() {
                    if (TextUtils.isEmpty(Account)){
                        Toast.makeText(MainActivity.this, "請輸入帳號", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(Password)) {
                        Toast.makeText(MainActivity.this, "請輸入密碼", Toast.LENGTH_SHORT).show();
                    }else if(Account.equals(result1) && Password.equals(result2)){
                        input(); ///登入成功的話才執行這個副程式
                        Toast toast = Toast.makeText(MainActivity.this, "登入成功!", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this ,MainActivity4.class);
                        MainActivity.this.startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "帳號或密碼錯誤!", Toast.LENGTH_LONG).show();
                        AccountET.setText(""); //清空
                        PasswordET.setText("");
                    }
                }
            });
        }
    };

    private void input() {

        SharedPreferences user = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        if (check.isChecked()) { //如果記住密碼那個選項有打勾就把這次成功登入的資料儲存起來
            edit.putString("account2",AccountET.getText().toString());
            edit.putString("password2",PasswordET.getText().toString());
            edit.putBoolean("remember",true);  //把我自己設的remember狀態設為true

        } else {
            edit.remove("account2"); //若上次沒打勾就把它清除記住的資料
            edit.remove("password2");
            edit.putBoolean("remember",false);
        }
        edit.commit();
        SharedPreferences user1 = getSharedPreferences("account", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = user1.edit();
        edit1.putString("acc", AccountET.getText().toString());
        edit1.commit();
    }


    private void output(){
        SharedPreferences user =getSharedPreferences("remember",MODE_PRIVATE);
        String account2=user.getString("account2","");  //若沒取得就是沒任何東西 " "
        String password2=user.getString("password2","");
        boolean remember=user.getBoolean("remember",false);
        //在input()我是寫成若打勾則讓它remember設為true，所以若沒取到則是false

        AccountET.setText(account2); //有取到先前給予的資料的話就會顯示出來
        PasswordET.setText(password2);
        check.setChecked(remember);//並且利用remember的狀態判斷上次是否打勾，有的話就顯示打勾
    }
}