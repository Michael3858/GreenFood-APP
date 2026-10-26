package com.example.greenfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity{

    private ImageButton back;
    private RadioGroup city;
    private RadioButton rb;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity7.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        city = findViewById(R.id.radioGroup);
        city.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btn = (Button) findViewById(R.id.confrim);
                switch(checkedId){
                    case R.id.radioButton1:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_1.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton2:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：\n").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_2.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton3:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_3.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton4:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_4.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton5:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_5.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton6:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_6.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton7:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_7.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case R.id.radioButton8:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuffer result = new StringBuffer();
                                int c = city.getCheckedRadioButtonId();
                                rb = findViewById(c);
                                if (rb != null)
                                {
                                    result.append("您選擇了：").append(rb.getText().toString());
                                }
                                Toast.makeText(MainActivity7.this,result.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity7.this, MainActivity8_8.class);
                                startActivity(intent);
                            }
                        });
                        break;
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
            intent.setClass(MainActivity7.this, MainActivity4.class);
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