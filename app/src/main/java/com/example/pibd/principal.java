package com.example.pibd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class principal extends AppCompatActivity {

    ImageView img;
    Intent select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        img = (ImageView) findViewById(R.id.xmlimg);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select = new Intent(principal.this, post.class);
                startActivity(select);
            }
        });
    }
}