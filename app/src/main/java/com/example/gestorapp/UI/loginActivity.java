package com.example.gestorapp.UI;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gestorapp.DBManager;
import com.example.gestorapp.Entities.User;
import com.example.gestorapp.MainActivity;
import com.example.gestorapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class loginActivity extends AppCompatActivity {
    private TextInputEditText txtEmail, txtPass;
    Button btnIngresar,btncreat;
    DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnIngresar=(Button)findViewById(R.id.btnIngresar);
        txtEmail = findViewById(R.id.editTextEmail);
        txtPass = findViewById(R.id.editTextPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btncreat = findViewById(R.id.btncreat);
      dbManager=new DBManager(getApplicationContext());




        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userl;
                String Email = "";
                String passw= "";
                Email = txtEmail.getText().toString().trim();
                passw = txtPass.getText().toString().trim();
                try {
                    userl=dbManager.LoginUser(Email,passw);
                    if(userl.getCorreo().equals(Email) && userl.getPassword().equals(passw)){

                        Toast.makeText(getApplicationContext(),
                                "suscesfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                     intent.putExtra("user",userl);
                        startActivity(intent);


                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "user error", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e){

                    Toast.makeText(getApplicationContext(),
                            "error " + e ,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }










            }
        });


        btncreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                 startActivity(intent);
            }
        });

    }
}