package com.example.pibd.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pibd.R;
import com.example.pibd.model.PostU;
import com.example.pibd.post;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostUAdapter extends FirestoreRecyclerAdapter<PostU, PostUAdapter.ViewHolder>
{
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param activity
     */
    public PostUAdapter(@NonNull FirestoreRecyclerOptions<PostU> options, FragmentActivity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull PostU PostU) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();


    viewHolder.Nombre.setText(PostU.getNombre());
    viewHolder.Tipo.setText(PostU.getTipo());
    viewHolder.Precio.setText(PostU.getPrecio());

    //Ver publicacion por id
    viewHolder.cardView2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent i = new Intent(activity, post.class);
            i.putExtra("id_post",id);
            try {
                PostUAdapter.this.finalize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            activity.startActivity(i);
        }
    });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_post_single, parent, false);

        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Nombre, Ubicacion, Descripcion, Numero_baños, Numero_Habitaciones, Precio, Tipo;
        CardView cardView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.titulo1);
            Tipo = itemView.findViewById(R.id.estatus1);
            Precio = itemView.findViewById(R.id.precio1);
            cardView2 = itemView.findViewById(R.id.cardView2);


        }
    }
}
