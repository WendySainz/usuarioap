package com.example.pibd;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragment extends Fragment {

    private TextInputEditText NombreDato, CorreoDato, TelefonoDato;
    private Button Actualizar;
    FirebaseAuth mAuth;
    private String idUser;

    FirebaseFirestore fStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        mAuth = FirebaseAuth.getInstance();

        NombreDato = root.findViewById(R.id.datanombre);
        CorreoDato = root.findViewById(R.id.datacorreo);
        TelefonoDato = root.findViewById(R.id.datatelefono);


        fStore = FirebaseFirestore.getInstance();
        idUser = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("User").document(idUser);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                NombreDato.setText(documentSnapshot.getString("Name"));
                CorreoDato.setText(documentSnapshot.getString("Email"));
                TelefonoDato.setText(documentSnapshot.getString("Phone"));
            }
        });

        return root;
    }
}