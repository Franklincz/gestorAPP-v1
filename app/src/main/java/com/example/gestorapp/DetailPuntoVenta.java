package com.example.gestorapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestorapp.Entities.PuntoVenta;
import com.example.gestorapp.UI.TabProductsActivity;
import com.example.gestorapp.UTILITIES.Helper;

import java.io.ByteArrayOutputStream;

public class DetailPuntoVenta extends AppCompatActivity {
    Bundle extras;
    ImageView imgView;
    Helper helper;
    DBManager DBManager;
    PuntoVenta puntoVenta;
    Button btnVisitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_punto_venta);
        puntoVenta = (PuntoVenta) getIntent().getSerializableExtra("puntoventa");
        DBManager = new DBManager(getApplicationContext());
        imgView = findViewById(R.id.imageFoto);
        helper = new Helper();
        Toolbar toolbar = findViewById(R.id.toolberr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnVisitar = findViewById(R.id.buttonvisitar);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });

        btnVisitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TabProductsActivity.class);
                startActivity(intent);
                Toast.makeText(DetailPuntoVenta.this, " toast" + puntoVenta.getName() + "-" + puntoVenta.getDireccion(), Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {


            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBmp = (Bitmap) extras.get("data");
                        imgView.setImageBitmap(imgBmp);
                        int l = helper.getBytes(imgBmp).length;
                        Toast.makeText(DetailPuntoVenta.this, "estes toast" + puntoVenta.getName() + "-" + puntoVenta.getDireccion() + " {{" + l, Toast.LENGTH_SHORT).show();

                        puntoVenta.setFoto(helper.getBytes(imgBmp));
                        try {
                            DBManager.update(puntoVenta);

                        } catch (Exception e) {
                            Toast.makeText(DetailPuntoVenta.this, "estes toast" + e, Toast.LENGTH_SHORT).show();

                        }


                    }


                }
            });




}