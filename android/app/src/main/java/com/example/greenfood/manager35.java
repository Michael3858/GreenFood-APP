package com.example.greenfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class manager35 extends AppCompatActivity {

    ListView listView;
    ArrayList<String> restaurantIDList = new ArrayList<>();
    ArrayList<String> restaurantNameList = new ArrayList<>();
    ArrayList<String> Title = new ArrayList<>();
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager35);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        restaurantIDList = bundle.getStringArrayList("restaurantIDList");
        restaurantNameList = bundle.getStringArrayList("restaurantNameList");
        for(int i = 0; i < restaurantIDList.size(); i++) {
            Title.add(restaurantNameList.get(i));
        }

        listView = findViewById(R.id.listview);

        manager35.MyAdapter myAdapter = new manager35.MyAdapter(this,Title);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ID = restaurantIDList.get(position);
                Intent intent = new Intent();
                intent.setClass(manager35.this, manager36.class);
                Bundle bundle = new Bundle();
                bundle.putString("ID", ID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rTitle;

        MyAdapter (Context context, ArrayList<String> title){
            super(context,R.layout.listview_style,R.id.otitle,title);
            this.context = context;
            this.rTitle = title;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.listview_style3,parent,false);
            TextView textView = row.findViewById(R.id.otitle);

            textView.setText(rTitle.get(position));

            return row;
        }
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
            intent.setClass(manager35.this, manager1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}