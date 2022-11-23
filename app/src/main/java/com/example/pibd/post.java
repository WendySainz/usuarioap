package com.example.pibd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class post extends AppCompatActivity {

    ImageView atras;
    Button contactar;
    Intent a;
    TextView T1;
    TextView T2;
    TextView T3;
    TextView T4;
    TextView T5;
    TextView T6;
    TextView T7;
    TextView T8;
    TextView T9;
    TextView T10;
    private FirebaseFirestore mFirestore;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        String id = getIntent().getStringExtra("id_post");
        mFirestore = FirebaseFirestore.getInstance();

        atras = (ImageView) findViewById(R.id.xmlimg);
        contactar = (Button) findViewById(R.id.btncontactar);

        T1 = findViewById(R.id.textView);
        T2 = findViewById(R.id.textView2);
        T3 = findViewById(R.id.textView3);
        T4 = findViewById(R.id.textView4);
        T5 = findViewById(R.id.textView6);
        T6 = findViewById(R.id.textView8);
        T7 = findViewById(R.id.textView9);
        T8 = findViewById(R.id.textView10);
        T9 = findViewById(R.id.textView11);

        //CONFIGURACION BOTON WHATSAPP
        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wpurl="https://wa.me/+523141471226?text= Quiero recibir información";

                Intent intent=new Intent (Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });

        getPost(id);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new Intent(post.this, principal.class);
                startActivity(a);
            }
        });
    }

    private void getPost(String id) {
        db.collection("PostP").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String Namepropiedad = documentSnapshot.getString("Nombre");
                String Tpropiedad= documentSnapshot.getString("Tipo");
                String Nbaños = documentSnapshot.getString("Numero_Baños");
                String Nhabitaccion = documentSnapshot.getString("Numero_Habitaciones");
                String Descripcion = documentSnapshot.getString("Descripcion");
                String Ubicacion = documentSnapshot.getString("Ubicacion");
                String Precio = documentSnapshot.getString("Precio");

                T1.setText(Namepropiedad);
                T3.setText(Tpropiedad);
                T6.setText(Nbaños);
                T7.setText(Nhabitaccion);
                T5.setText(Descripcion);
                T2.setText(Ubicacion);
                T9.setText(Precio);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}