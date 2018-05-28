package com.example.alifian.if_vote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alifian.if_vote.Interface.ItemClickListener;
import com.example.alifian.if_vote.Model.Kandidat;
import com.example.alifian.if_vote.R;
import com.example.alifian.if_vote.ViewHolder.MenuVideoHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    DatabaseReference mDatabase;
    FirebaseDatabase database;
    DatabaseReference kandidat;
    RecyclerView recycler_video;
    RecyclerView.LayoutManager layoutManager;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        //init firebase
        database = FirebaseDatabase.getInstance();
        kandidat = database.getReference("kandidat");

        //load data
        recycler_video = (RecyclerView)view.findViewById(R.id.recycler_video);
        recycler_video.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_video.setLayoutManager(layoutManager);

        loaddata();
        return view;
    }

    private void loaddata(){
        FirebaseRecyclerAdapter<Kandidat, MenuVideoHolder> adapter = new FirebaseRecyclerAdapter<Kandidat, MenuVideoHolder>(Kandidat.class, R.layout.video_item, MenuVideoHolder.class, kandidat) {
            @Override
                protected void populateViewHolder(MenuVideoHolder viewHolder, Kandidat model, int position) {
                Picasso.with(getActivity().getBaseContext()).load(model.getImage()).into(viewHolder.image);
                final Kandidat clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //get text url
                        String send_url = clickItem.getVideo();

                        //launch activity media
                        Intent i = new Intent(getActivity(), PlayActivity.class);
                        i.putExtra(Intent.EXTRA_TEXT, send_url);
                        startActivity(i);
                    }
                });

            }
        };
        recycler_video.setAdapter(adapter);
    }

}
