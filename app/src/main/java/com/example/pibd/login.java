package com.example.pibd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    Toast mensaje;
    Intent i;
    EditText Email, Password;
    String email, password;
    Button Btn_login, Btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //obtener la instancia de mauth de firebase
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.password);
        Btn_login = (Button) findViewById(R.id.loginBtn);
        Btn_registro = (Button) findViewById(R.id.CreateAccount);

        Btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cambia entre pantallas y te manda un mensaje cuando creas una cuenta
                mensaje = Toast.makeText(login.this, "Bienvenido", Toast.LENGTH_LONG);
                i = new Intent(login.this, registro.class);
                startActivity(i);
            }
        });

        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString();
                password = Password.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                }
                else {
                    Toast.makeText(login.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            i = new Intent(login.this, principal.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(login.this, "Usuario Logueado", Toast.LENGTH_SHORT).show();
                        } else {
                            mensaje = Toast.makeText(login.this, "Ingrese contrase??a y correo de manera correcta", Toast.LENGTH_LONG);
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, principal.class));
            finish();
        }
    }
}