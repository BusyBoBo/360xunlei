package com.czg.xunlei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.czg.xunlei.activity.ThumbActivity;


public class MainActivity extends AppCompatActivity {
    private EditText ed_search;
    private RecyclerView rec;
    private Button btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, ThumbActivity.class));
    }
}


