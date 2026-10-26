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

public class MainActivity8_5 extends AppCompatActivity {

    private ImageButton back;
    private Button confrim;
    private CheckBox ch1;
    int d1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_5);

        ch1 = findViewById(R.id.checkBox1);

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
                if(!ch1.isChecked()){
                    Toast toast = Toast.makeText(MainActivity8_5.this, "請選擇至少一個行政區!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    SharedPreferences area = getSharedPreferences("area", MODE_PRIVATE);
                    SharedPreferences.Editor edit = area.edit();
                    edit.putInt("city", 5);
                    edit.putInt("d1", d1);
                    edit.commit();
                    Toast.makeText(MainActivity8_5.this, result.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity8_5.this, MainActivity6.class);
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
            intent.setClass(MainActivity8_5.this, MainActivity4.class);
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