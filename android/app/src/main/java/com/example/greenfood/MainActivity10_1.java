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

public class MainActivity10_1 extends AppCompatActivity {

    private ImageButton back;
    private Button confrim;
    private CheckBox ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13, ch14, ch15, ch16, ch17, ch18, ch19, ch20;
    int s1 = 0, s2 = 0, s3 = 0, s4 = 0, s5 = 0, s6 = 0, s7 = 0, s8 = 0, s9 = 0, s10 = 0, s11 = 0, s12 = 0, s13 = 0, s14 = 0, s15 = 0, s16 = 0, s17 = 0, s18 = 0, s19 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10_1);

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
        ch20 = findViewById(R.id.checkBox20);

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
                    s1 = 1;
                }
                if (ch2.isChecked()) {
                    result.append(ch2.getText().toString()).append("\n");
                    s2 = 1;
                }
                if (ch3.isChecked()) {
                    result.append(ch3.getText().toString()).append("\n");
                    s3 = 1;
                }
                if (ch4.isChecked()) {
                    result.append(ch4.getText().toString()).append("\n");
                    s4 = 1;
                }
                if (ch5.isChecked()) {
                    result.append(ch5.getText().toString()).append("\n");
                    s5 = 1;
                }
                if (ch6.isChecked()) {
                    result.append(ch6.getText().toString()).append("\n");
                    s6 = 1;
                }
                if (ch7.isChecked()) {
                    result.append(ch7.getText().toString()).append("\n");
                    s7 = 1;
                }
                if (ch8.isChecked()) {
                    result.append(ch8.getText().toString()).append("\n");
                    s8 = 1;
                }
                if (ch9.isChecked()) {
                    result.append(ch9.getText().toString()).append("\n");
                    s9 = 1;
                }
                if (ch10.isChecked()) {
                    result.append(ch10.getText().toString()).append("\n");
                    s10 = 1;
                }
                if (ch11.isChecked()) {
                    result.append(ch11.getText().toString()).append("\n");
                    s11 = 1;
                }
                if (ch12.isChecked()) {
                    result.append(ch12.getText().toString()).append("\n");
                    s12 = 1;
                }
                if (ch13.isChecked()) {
                    result.append(ch13.getText().toString()).append("\n");
                    s13 = 1;
                }
                if (ch14.isChecked()) {
                    result.append(ch14.getText().toString()).append("\n");
                    s14 = 1;
                }
                if (ch15.isChecked()) {
                    result.append(ch15.getText().toString()).append("\n");
                    s15 = 1;
                }
                if (ch16.isChecked()) {
                    result.append(ch16.getText().toString()).append("\n");
                    s16 = 1;
                }
                if (ch17.isChecked()) {
                    result.append(ch17.getText().toString()).append("\n");
                    s17 = 1;
                }
                if (ch18.isChecked()) {
                    result.append(ch18.getText().toString()).append("\n");
                    s18 = 1;
                }
                if (ch19.isChecked()) {
                    result.append(ch19.getText().toString()).append("\n");
                    s19 = 1;
                }
                if (ch20.isChecked()) {
                    result.append(ch20.getText().toString()).append("\n");
                    s1 = 1;
                    s2 = 1;
                    s3 = 1;
                    s4 = 1;
                    s5 = 1;
                    s6 = 1;
                    s7 = 1;
                    s8 = 1;
                    s9 = 1;
                    s10 = 1;
                    s11 = 1;
                    s12 = 1;
                    s13 = 1;
                    s14 = 1;
                    s15 = 1;
                    s16 = 1;
                    s17 = 1;
                    s18 = 1;
                    s19 = 1;
                }
                if(!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&!ch5.isChecked()&&!ch6.isChecked()&&!ch7.isChecked()&&!ch8.isChecked()&&!ch9.isChecked()&&!ch10.isChecked()&&!ch11.isChecked()&&!ch12.isChecked()&&!ch13.isChecked()&&!ch14.isChecked()&&!ch15.isChecked()&&!ch16.isChecked()&&!ch17.isChecked()&&!ch18.isChecked()&&!ch19.isChecked()&&!ch20.isChecked()){
                    Toast toast = Toast.makeText(MainActivity10_1.this, "請選擇至少一個風格!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences style = getSharedPreferences("style", MODE_PRIVATE);
                    SharedPreferences.Editor edit = style.edit();
                    edit.putInt("s1", s1);
                    edit.putInt("s2", s2);
                    edit.putInt("s3", s3);
                    edit.putInt("s4", s4);
                    edit.putInt("s5", s5);
                    edit.putInt("s6", s6);
                    edit.putInt("s7", s7);
                    edit.putInt("s8", s8);
                    edit.putInt("s9", s9);
                    edit.putInt("s10", s10);
                    edit.putInt("s11", s11);
                    edit.putInt("s12", s12);
                    edit.putInt("s13", s13);
                    edit.putInt("s14", s14);
                    edit.putInt("s15", s15);
                    edit.putInt("s16", s16);
                    edit.putInt("s17", s17);
                    edit.putInt("s18", s18);
                    edit.putInt("s19", s19);
                    edit.commit();
                    Toast.makeText(MainActivity10_1.this, result.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity10_1.this, MainActivity9.class);
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
            intent.setClass(MainActivity10_1.this, MainActivity4.class);
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