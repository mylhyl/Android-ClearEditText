package com.mylhyl.widget.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mylhyl.widget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final List<String> strUserNames = new ArrayList<>();
//        strUserNames.add("11111");
//        strUserNames.add("22222");
//        strUserNames.add("33333");
//        strUserNames.add("44444");
//        strUserNames.add("aaaaa");
//        strUserNames.add("bbbbb");
//        strUserNames.add("ccccc");
//        strUserNames.add("ddddd");
//        ClearEditText autoUser = (ClearEditText) findViewById(R.id.auto_user);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_dropdown_item_1line, android.R.id.text1, strUserNames);
//        autoUser.setAdapter(arrayAdapter);
    }
}
