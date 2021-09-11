package com.example.songlist;

import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SplashFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Playlist playlist = new Playlist();
        playlist.loadDataForTest(getResources());  // for test

        ImageView imageView = view.findViewById(R.id.cover);
        TextView artistName = view.findViewById(R.id.tv_artist_name);
        TextView songName = view.findViewById(R.id.tv_song_name);

        ObjectAnimator animCover = ObjectAnimator.ofFloat(imageView, "alpha", 0, 1);
        animCover.setDuration(2000);
        animCover.start();

        ObjectAnimator animTvSong = ObjectAnimator.ofFloat(songName, "alpha", 0, 1);
        animTvSong.setStartDelay(500);
        animTvSong.setDuration(2000);
        animTvSong.start();

        ObjectAnimator animTvArtist = ObjectAnimator.ofFloat(artistName, "alpha", 0, 1);
        animTvArtist.setStartDelay(1000);
        animTvArtist.setDuration(2000);
        animTvArtist.start();

        Random random = new Random();
        int rndIndex = random.nextInt(playlist.getCountSong());

        imageView.setImageDrawable(playlist.getCover(rndIndex));
        artistName.setText(playlist.getArtistName(rndIndex));
        songName.setText(playlist.getSongName(rndIndex));

        Handler handler = new Handler();
        handler.postDelayed(() -> Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_vertListFragment),6000);

        return view;
    }
}