package com.example.pibd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class UserProfile extends AppCompatActivity {

    TextView datanombre, CorreoDato, TelefonoDato, EdadDato, UserInfo;
    FirebaseAuth mAuth;
    String idUser;
    ImageView clear;
    Toast mensaje;
    Button logout;
    Intent a;

    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        clear = findViewById(R.id.btnsalir);
        logout = findViewById(R.id.logout);

        datanombre = findViewById(R.id.datanombre);
        CorreoDato = findViewById(R.id.datacorreo);
        EdadDato = findViewById(R.id.dataedad);
        TelefonoDato = findViewById(R.id.datatelefono);
        UserInfo = findViewById(R.id.UserData);


        fStore = FirebaseFirestore.getInstance();
        idUser = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("User").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                UserInfo.setText(documentSnapshot.getString("Name"));
                datanombre.setText(documentSnapshot.getString("Name"));
                EdadDato.setText(documentSnapshot.getString("age"));
                CorreoDato.setText(documentSnapshot.getString("Email"));
                TelefonoDato.setText(documentSnapshot.getString("Phone"));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new Intent(UserProfile.this, principal.class);
                startActivity(a);
                mensaje = Toast.makeText(UserProfile.this, "Cambiando a inicio", Toast.LENGTH_LONG);
                mensaje.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(UserProfile.this, login.class);
                startActivity(intent);
                onStop();
            }
        });
    }
}