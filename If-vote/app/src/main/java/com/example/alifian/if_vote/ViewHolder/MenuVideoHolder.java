package com.example.alifian.if_vote.ViewHolder;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.alifian.if_vote.Interface.ItemClickListener;
import com.example.alifian.if_vote.R;

public class MenuVideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView txturl;
    public VideoView videoView;
    public String url;
    public ImageView image;
    private ItemClickListener itemClickListener;

    public MenuVideoHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.thumbnail);

//        txturl = (TextView)itemView.findViewById(R.id.url_video);
//        url = txturl.toString();
//        videoView = (VideoView) itemView.findViewById(R.id.menu_video);
//        Uri uri = Uri.parse(url);
//
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
