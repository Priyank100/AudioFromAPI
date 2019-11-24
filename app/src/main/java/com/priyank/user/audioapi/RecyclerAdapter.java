package com.priyank.user.audioapi;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<Model> list;
    MediaPlayer mediaPlayer;
    MediaController mediaController;

    public RecyclerAdapter(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        return new RecyclerAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.MyViewHolder holder, final int position) {
        holder.trackName.setText(list.get(position).getTrackName());
        holder.collectionName.setText(list.get(position).getCollectionName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("trackName", list.get(position).getTrackName());
                i.putExtra("audioUrl", list.get(position).getAudioUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trackName, collectionName;
        ImageView musicSign;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = itemView.findViewById(R.id.track_name);
            collectionName = itemView.findViewById(R.id.collection_name);
            musicSign = itemView.findViewById(R.id.music_sign);
        }
    }
}
