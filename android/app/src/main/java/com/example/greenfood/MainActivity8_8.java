package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity8_8 extends AppCompatActivity {

    private ImageButton back;
    private Button confrim;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14, ch15, ch16, ch17, ch18, ch19;
    int d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0, d10 = 0, d11 = 0, d12 = 0, d13 = 0, d14 = 0, d15 = 0, d16 = 0, d17 = 0, d18 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_8);

        ch1 = findViewById(R.id.checkBox1);
        ch2 = findViewById(R.id.checkBox2);
        ch3 = findViewById(R.id.checkBox3);
        ch4 = findViewById(R.id.checkBox4);
        ch5 = findViewById(R.id.checkBox5);
        ch6 = findViewById(R.id.checkBox6);
        ch7 = findViewById(R.id.checkBox7);
        ch8 = findViewById(R.id.checkBox8);
        ch9 = findViewById(R.id.checkBox9);
        ch10 = findViewById(R.id.checkBox10);
        ch11 = findViewById(R.id.checkBox11);
        ch12 = findViewById(R.id.checkBox12);
        ch13 = findViewById(R.id.checkBox13);
        ch14 = findViewById(R.id.checkBox14);
        ch15 = findViewById(R.id.checkBox15);
        ch16 = findViewById(R.id.checkBox16);
        ch17 = findViewById(R.id.checkBox17);
        ch18 = findViewById(R.id.checkBox18);
        ch19 = findViewById(R.id.checkBox19);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });

        confrim = (Button) findViewById(R.id.confrim);
        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                result.append("您選擇了\n");
                if (ch1.isChecked()) {
                    result.append(ch1.getText().toString()).append("\n");
                    d1 = 1;
                }
                if (ch2.isChecked()) {
                    result.append(ch2.getText().toString()).append("\n");
                    d2 = 1;
                }
                if (ch3.isChecked()) {
                    result.append(ch3.getText().toString()).append("\n");
                    d3 = 1;
                }
                if (ch4.isChecked()) {
                    result.append(ch4.getText().toString()).append("\n");
                    d4 = 1;
                }
                if (ch5.isChecked()) {
                    result.append(ch5.getText().toString()).append("\n");
                    d5 = 1;
                }
                if (ch6.isChecked()) {
                    result.append(ch6.getText().toString()).append("\n");
                    d6 = 1;
                }
                if (ch7.isChecked()) {
                    result.append(ch7.getText().toString()).append("\n");
                    d7 = 1;
                }
                if (ch8.isChecked()) {
                    result.append(ch8.getText().toString()).append("\n");
                    d8 = 1;
                }
                if (ch9.isChecked()) {
                    result.append(ch9.getText().toString()).append("\n");
                    d9 = 1;
                }
                if (ch10.isChecked()) {
                    result.append(ch10.getText().toString()).append("\n");
                    d10 = 1;
                }
                if (ch11.isChecked()) {
                    result.append(ch11.getText().toString()).append("\n");
                    d11 = 1;
                }
                if (ch12.isChecked()) {
                    result.append(ch12.getText().toString()).append("\n");
                    d12 = 1;
                }
                if (ch13.isChecked()) {
                    result.append(ch13.getText().toString()).append("\n");
                    d13 = 1;
                }
                if (ch14.isChecked()) {
                    result.append(ch14.getText().toString()).append("\n");
                    d14 = 1;
                }
                if (ch15.isChecked()) {
                    result.append(ch15.getText().toString()).append("\n");
                    d15 = 1;
                }
                if (ch16.isChecked()) {
                    result.append(ch16.getText().toString()).append("\n");
                    d16 = 1;
                }
                if (ch17.isChecked()) {
                    result.append(ch17.getText().toString()).append("\n");
                    d17 = 1;
                }
                if (ch18.isChecked()) {
                    result.append(ch18.getText().toString()).append("\n");
                    d18 = 1;
                }
                if (ch19.isChecked()) {
                    result.append(ch19.getText().toString()).append("\n");
                    d1 = 1;
                    d2 = 1;
                    d3 = 1;
                    d4 = 1;
                    d5 = 1;
                    d6 = 1;
                    d7 = 1;
                    d8 = 1;
                    d9 = 1;
                    d10 = 1;
                    d11 = 1;
                    d12 = 1;
                    d13 = 1;
                    d14 = 1;
                    d15 = 1;
                    d16 = 1;
                    d17 = 1;
                    d18 = 1;
                }
                if(!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&!ch5.isChecked()&&!ch6.isChecked()&&!ch7.isChecked()&&!ch8.isChecked()&&!ch9.isChecked()&&!ch10.isChecked()&&!ch11.isChecked()&&!ch12.isChecked()&&!ch13.isChecked()&&!ch14.isChecked()&&!ch15.isChecked()&&!ch16.isChecked()&&!ch17.isChecked()&&!ch18.isChecked()&&!ch19.isChecked()){
                    Toast toast = Toast.makeText(MainActivity8_8.this, "請選擇至少一個行政區!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences area = getSharedPreferences("area", MODE_PRIVATE);
                    SharedPreferences.Editor edit = area.edit();
                    edit.putInt("city", 8);
                    edit.putInt("d1", d1);
                    edit.putInt("d2", d2);
                    edit.putInt("d3", d3);
                    edit.putInt("d4", d4);
                    edit.putInt("d5", d5);
                    edit.putInt("d6", d6);
                    edit.putInt("d7", d7);
                    edit.putInt("d8", d8);
                    edit.putInt("d9", d9);
                    edit.putInt("d10", d10);
                    edit.putInt("d11", d11);
                    edit.putInt("d12", d12);
                    edit.putInt("d13", d13);
                    edit.putInt("d14", d14);
                    edit.putInt("d15", d15);
                    edit.putInt("d16", d16);
                    edit.putInt("d17", d17);
                    edit.putInt("d18", d18);
                    edit.commit();
                    Toast.makeText(MainActivity8_8.this, result.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity8_8.this, MainActivity6.class);
                    startActivity(intent);
                }
            }
        });
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
            intent.setClass(MainActivity8_8.this, MainActivity4.class);
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
}