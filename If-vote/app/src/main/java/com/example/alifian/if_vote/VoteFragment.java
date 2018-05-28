package com.example.alifian.if_vote;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alifian.if_vote.Interface.ItemClickListener;
import com.example.alifian.if_vote.Model.Kandidat;
import com.example.alifian.if_vote.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment {

    DatabaseReference mDatabase;
    FirebaseDatabase database;
    DatabaseReference kandidat;
    RecyclerView recycler_view;
    RecyclerView.LayoutManager layoutManager;
    TextView txtnim,txtnama;

    public VoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
        //init firebase
        database = FirebaseDatabase.getInstance();
        kandidat = database.getReference("kandidat");

        //load data
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);

        loaddata();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loaddata(){
        FirebaseRecyclerAdapter<Kandidat,MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Kandidat, MenuViewHolder>(Kandidat.class, R.layout.list_item,MenuViewHolder.class,kandidat) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Kandidat model, int position) {
                viewHolder.txtnopas.setText(model.getNopas());
                Picasso.with(getActivity().getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Kandidat clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity().getBaseContext(), "Pasangan No: "+clickItem.getNopas(),Toast.LENGTH_SHORT).show();
                        mDatabase = FirebaseDatabase.getInstance().getReference("vote");

                    }
                });

            }
        };
        recycler_view.setAdapter(adapter);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }


}
