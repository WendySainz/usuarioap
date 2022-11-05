package com.example.pibd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class post extends AppCompatActivity {

    ImageView atras;
    Button contacto;
    Intent a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        atras = (ImageView) findViewById(R.id.xmlimg);
        contacto = (Button) findViewById(R.id.xmlcontacto);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = new Intent(post.this, principal.class);
                startActivity(a);
            }
        });
    }
}