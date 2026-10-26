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

public class MainActivity8_3 extends AppCompatActivity {

    private ImageButton back;
    private Button confrim;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10;
    int d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0, d9 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_3);

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
                    d1 = 1;
                    d2 = 1;
                    d3 = 1;
                    d4 = 1;
                    d5 = 1;
                    d6 = 1;
                    d7 = 1;
                    d8 = 1;
                    d9 = 1;
                }
                if(!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&!ch5.isChecked()&&!ch6.isChecked()&&!ch7.isChecked()&&!ch8.isChecked()&&!ch9.isChecked()&&!ch10.isChecked()){
                    Toast toast = Toast.makeText(MainActivity8_3.this, "請選擇至少一個行政區!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences area = getSharedPreferences("area", MODE_PRIVATE);
                    SharedPreferences.Editor edit = area.edit();
                    edit.putInt("city", 3);
                    edit.putInt("d1", d1);
                    edit.putInt("d2", d2);
                    edit.putInt("d3", d3);
                    edit.putInt("d4", d4);
                    edit.putInt("d5", d5);
                    edit.putInt("d6", d6);
                    edit.putInt("d7", d7);
                    edit.putInt("d8", d8);
                    edit.putInt("d9", d9);
                    edit.commit();
                    Toast.makeText(MainActivity8_3.this, result.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity8_3.this, MainActivity6.class);
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
            intent.setClass(MainActivity8_3.this, MainActivity4.class);
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