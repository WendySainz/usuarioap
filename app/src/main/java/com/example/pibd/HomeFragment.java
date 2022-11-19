package com.example.pibd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pibd.adapter.PostUAdapter;
import com.example.pibd.model.PostU;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class HomeFragment extends Fragment {

    ImageView img;
    Intent select;
    RecyclerView mRecycler;
    PostUAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = root.findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Query query = mFirestore.collection("PostP");

        FirestoreRecyclerOptions<PostU> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<PostU>().setQuery(query, PostU.class).build();

        mAdapter = new PostUAdapter(firestoreRecyclerOptions, getActivity());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        return root;




       // img=(ImageView) root.findViewById(R.id.xmlimg);
        //img.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
           //     select = new Intent(getActivity(), post.class);
          //      startActivity(select);
           // }
       // });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}