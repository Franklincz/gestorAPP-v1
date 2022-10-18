package com.example.gestorapp.UI;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestorapp.DBManager;
import com.example.gestorapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity {
    private TextInputEditText edtNombre, edtcorreo, edtApellidos,
            edtPassword, edtUserName;
    ImageView register;
    Button btn;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = findViewById(R.id.edtNombre);
        edtcorreo = findViewById(R.id.edtEmail);
        edtApellidos = findViewById(R.id.edtApellidos);

        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUserName);


        register = (ImageView) findViewById(R.id.register);
        dbManager = new DBManager(this);

        String nombres = "";


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String nombres = edtNombre.getText().toString();
                    String apellidos = edtApellidos.getText().toString().trim();
                    String email = edtcorreo.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    String username = edtUserName.getText().toString().trim();

                    validate(nombres, apellidos, email, password, username);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void validate(String nombres, String apellidos, String email, String password, String username) {
        if (TextUtils.isEmpty(nombres)) {
            edtNombre.setError("Debe ingresar su nombre");


        } else {
            if (TextUtils.isEmpty(apellidos)) {
                edtApellidos.setError("Debe Ingresar  su apellido ");


            } else {
                if (TextUtils.isEmpty(email)) {
                    edtcorreo.setError("Debe ingresar su EMail ");

                } else {
                    if (TextUtils.isEmpty(username)) {
                        edtUserName.setError("Debe ingresar su usuario ");

                    } else {
                        if (TextUtils.isEmpty(password)) {
                            edtPassword.setError("Debe ingresar su password ");
                        } else {
                            dbManager.insert(username, password, nombres, apellidos, email);
                            dbManager.close();
                            Toast.makeText(getApplicationContext(),
                                    "suscesfull" + nombres, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Cuenta creada satisfactoriamente!!", Toast.LENGTH_SHORT).show();
                            setNull();
                            finish();

                        }
                    }
                }
            }
        }


    }

    public void setNull() {
        edtNombre.setText("");
        edtPassword.setText("");
        edtApellidos.setText("");
        edtcorreo.setText("");
        edtUserName.setText("");
    }
}