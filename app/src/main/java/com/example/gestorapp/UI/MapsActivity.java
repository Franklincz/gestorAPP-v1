package com.example.gestorapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gestorapp.R;

public class MapsActivity extends AppCompatActivity {

   TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txtTitle=findViewById(R.id.txtTile);
        String value = getIntent().getStringExtra("name");
       txtTitle.setText(value);
    }






}