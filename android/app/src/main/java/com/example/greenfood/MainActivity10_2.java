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

public class MainActivity10_2 extends AppCompatActivity {

    private ImageButton back;
    private Button confrim;
    private CheckBox ch1, ch2, ch3, ch4, ch5;
    int m1 = 0, m2 = 0, m3 = 0, m4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10_2);

        ch1 = findViewById(R.id.checkBox1);
        ch2 = findViewById(R.id.checkBox2);
        ch3 = findViewById(R.id.checkBox3);
        ch4 = findViewById(R.id.checkBox4);
        ch5 = findViewById(R.id.checkBox5);

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
                    m1 = 1;
                }
                if (ch2.isChecked()) {
                    result.append(ch2.getText().toString()).append("\n");
                    m2 = 1;
                }
                if (ch3.isChecked()) {
                    result.append(ch3.getText().toString()).append("\n");
                    m3 = 1;
                }
                if (ch4.isChecked()) {
                    result.append(ch4.getText().toString()).append("\n");
                    m4 = 1;
                }
                if (ch5.isChecked()) {
                    result.append(ch5.getText().toString()).append("\n");
                    m1 = 1;
                    m2 = 1;
                    m3 = 1;
                    m4 = 1;
                }
                if(!ch1.isChecked()&&!ch2.isChecked()&&!ch3.isChecked()&&!ch4.isChecked()&&!ch5.isChecked()){
                    Toast toast = Toast.makeText(MainActivity10_2.this, "請選擇至少一個用餐方式!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences meal = getSharedPreferences("meal", MODE_PRIVATE);
                    SharedPreferences.Editor edit = meal.edit();
                    edit.putInt("m1", m1);
                    edit.putInt("m2", m2);
                    edit.putInt("m3", m3);
                    edit.putInt("m4", m4);
                    edit.commit();
                    Toast.makeText(MainActivity10_2.this, result.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity10_2.this, MainActivity9.class);
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
            intent.setClass(MainActivity10_2.this, MainActivity4.class);
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