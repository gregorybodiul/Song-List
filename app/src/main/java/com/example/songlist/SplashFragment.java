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

        animateView(imageView, "alpha", 0, 1,0,2000);
        animateView(songName, "alpha", 0, 1,500,2000);
        animateView(artistName, "alpha", 0, 1,1000,2000);
        randomSong(playlist, imageView,songName,artistName);
        Handler handler = new Handler();
        handler.postDelayed(() -> Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_vertListFragment),6000);
        return view;
    }

    private void animateView(View view, String property, int valueMin, int valueMax, int startDelay, int duration){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, property, valueMin, valueMax);
        objectAnimator.setStartDelay(startDelay);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }

    private void randomSong(Playlist playlist,ImageView cover, TextView textArtist, TextView textSong){
        Random random = new Random();
        int rndIndex = random.nextInt(playlist.getCountSong());
        cover.setImageDrawable(playlist.getCover(rndIndex));
        textArtist.setText(playlist.getArtistName(rndIndex));
        textSong.setText(playlist.getSongName(rndIndex));
    }
}