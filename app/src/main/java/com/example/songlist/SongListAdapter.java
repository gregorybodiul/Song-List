package com.example.songlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.Viewholder>  {

    private Context context;
    private Playlist playlist;
    private int rowItemID;

    public SongListAdapter(Context context, Playlist playlist, int rowItemID){
        this.context = context;
        this.playlist = playlist;
        this.rowItemID = rowItemID;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(rowItemID, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.cover.setImageDrawable(playlist.getCover(position));
        holder.songName.setText(playlist.getSongName(position));
        holder.artistName.setText(playlist.getArtistName(position));
    }

    @Override
    public int getItemCount() {
        return playlist.getCountSong();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView artistName, songName;
        ImageView cover;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.tv_artist_name);
            songName = itemView.findViewById(R.id.tv_song_name);
            cover = itemView.findViewById(R.id.cover);
        }
    }
}
