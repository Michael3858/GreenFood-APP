package com.example.greenfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity15 extends AppCompatActivity {

    ListView listView;
    ArrayList<String> restaurantIDList = new ArrayList<String>();
    ArrayList<String> restaurantNameList = new ArrayList<String>();
    ArrayList<String> restaurantRateList = new ArrayList<>();
    ArrayList<String> restaurantRateIDList = new ArrayList<>();
    ArrayList<String> restaurantRateNumList = new ArrayList<>();
    ArrayList<String> restaurantHistoryList = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    ArrayList<String> Title = new ArrayList<>();
    ArrayList<String> sec = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
    Date curDate;
    String Account, ID, str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        listView = findViewById(R.id.listview);

        SharedPreferences user = getSharedPreferences("account", MODE_PRIVATE);
        Account = user.getString("acc", "");

        SharedPreferences List1 = getSharedPreferences("ListString15", MODE_PRIVATE);
        int num1 = List1.getInt("num", 0);
        for(int i = 0; i < num1; i++){
            String item1 = List1.getString("item_"+i, null);
            restaurantIDList.add(item1);
        }
        SharedPreferences List2 = getSharedPreferences("ListString16", MODE_PRIVATE);
        int num2 = List2.getInt("num", 0);
        for(int i = 0; i < num2; i++){
            String item2 = List2.getString("item_"+i, null);
            restaurantNameList.add(item2);
        }
        SharedPreferences List3 = getSharedPreferences("ListString17", MODE_PRIVATE);
        int num3 = List3.getInt("num", 0);
        for(int i = 0; i < num3; i++){
            String item3 = List3.getString("item_"+i, null);
            restaurantHistoryList.add(item3);
        }
        SharedPreferences List4 = getSharedPreferences("ListString18", MODE_PRIVATE);
        int num4 = List4.getInt("num", 0);
        for(int i = 0; i < num4; i++){
            String item4 = List4.getString("item_"+i, null);
            restaurantRateList.add(item4);
        }
        SharedPreferences List5 = getSharedPreferences("ListString19", MODE_PRIVATE);
        int num5 = List5.getInt("num", 0);
        for(int i = 0; i < num5; i++){
            String item5 = List5.getString("item_"+i, null);
            restaurantRateIDList.add(item5);
        }
        SharedPreferences List6 = getSharedPreferences("ListString20", MODE_PRIVATE);
        int num6 = List6.getInt("num", 0);
        for(int i = 0; i < num6; i++){
            String item6 = List6.getString("item_"+i, null);
            restaurantRateNumList.add(item6);
        }
        for(int i = 0; i < restaurantIDList.size(); i++){
            time.add(restaurantHistoryList.get(i));
            Title.add(restaurantNameList.get(i));
            int a = 0;
            for(int j = 0; j < restaurantRateIDList.size(); j++){
                if(restaurantIDList.get(i).equals(restaurantRateIDList.get(j))){
                    sec.add(restaurantRateList.get(j)+"星("+restaurantRateNumList.get(j)+"人)");
                    a = 1;
                }
            }
            if(a == 0){
                sec.add("尚無資料");
            }
        }

        MyAdapter myAdapter = new MyAdapter(this,time,Title,sec);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                curDate = new Date(System.currentTimeMillis());
                str = formatter.format(curDate);
                ID = restaurantIDList.get(position);
                browse();
                Intent intent = new Intent();
                intent.setClass(MainActivity15.this, MainActivity12.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rtime;
        ArrayList<String> rTitle;
        ArrayList<String> rsec;

        MyAdapter (Context context,ArrayList<String> time, ArrayList<String> title,ArrayList<String> sec){
            super(context,R.layout.listview_style2,R.id.time,time);
            this.context = context;
            this.rtime = time;
            this.rTitle = title;
            this.rsec = sec;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.listview_style2,parent,false);
            TextView textView = row.findViewById(R.id.time);
            TextView textView1 = row.findViewById(R.id.otitle);
            TextView textView2 = row.findViewById(R.id.ttitle);
            textView.setText(rtime.get(position));
            textView1.setText(rTitle.get(position));
            textView2.setText(rsec.get(position));
            return row;
        }
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
            intent.setClass(MainActivity15.this, MainActivity4.class);
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

    private void browse() {
        try {
            jsonObject.put("account", Account);
            jsonObject.put("ID", ID);
            jsonObject.put("Time", str);
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
        String path = ApiConfig.url("browse.php");
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
}