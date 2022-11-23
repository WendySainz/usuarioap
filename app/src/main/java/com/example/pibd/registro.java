package com.example.pibd;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //Declaramos las variables
    TextView mLoginBtn;
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    String email, password, Name, Mphone;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Buscamos el objeto mediante el id
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registro);
        mLoginBtn = findViewById(R.id.createText);
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);

        //Mediante el evento click del boton obtenemos los valore que vienen desde el edit text
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                Name = mFullName.getText().toString();
                Mphone = mPhone.getText().toString();
                //Mandamos a llamar a la funcion  registro usuario y le enviamos todos datos que se nececitan
                registroUsuario(Name, email, password, Mphone);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Otra manera de mandar a otra pantalla
                startActivity((new Intent(getApplicationContext(), login.class)));
            }
        });
    }
// Funcion para registrar uusarios con el metodo de createUserWithEmailAndPassword
    private void registroUsuario(String Name, String Email, String Password, String Phone) {
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener
                (this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // se creo una condicon para saber si la tarea se cumple guardar los datos en fireBase
                        if (task.isSuccessful()){
                            FirebaseUser User = mAuth.getCurrentUser();
                            //Mandamos llamar a la funcion de userData la cual senguardara en CloudFirestore
                            //Y se le envian todos los datos a los cuales se van a guardar
                            userData(Name, Email, Password, Phone);
                        } else {
                            //En caso de que falle el registro se mandara un mesaje de fallo
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            ///Toast.makeText(registro.this, "Authentication failed.",
                                  //  Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    // En esta funcion guardamos datos en firebase
    private void  userData(String Name, String Email, String Password, String Phone){

        Map<String, Object> User = new HashMap<>();
        User.put("Name", Name);
        User.put("Email", Email);
        User.put("Password", Password);
        User.put("Phone", Phone);
        db.collection("User").document(Email)
                .set(User)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "Se ha registrado correctamente!");
                        startActivity((new Intent((getApplicationContext()), login.class)));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error al registrarse", e);
                    }
                });
    }

}