package com.example.songlist;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HorListFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hor_list, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Playlist playlist = Playlist.getInstance();

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        SongListAdapter songListAdapter = new SongListAdapter(this.getContext(),playlist, R.layout.horizontal_row_item);
        recyclerView.setAdapter(songListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        scrolling(recyclerView);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.change_list);
        floatingActionButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_horListFragment_to_vertListFragment));

        return view;
    }

    private void scrolling(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if(e.getAction() == MotionEvent.ACTION_UP){
                    rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if(newState == 0){
                                int scrollRange = rv.computeHorizontalScrollRange();
                                int scrollOffset = rv.computeHorizontalScrollOffset();
                                int scrollExtent = rv.computeHorizontalScrollExtent();

                                float countSong = (float)scrollRange / (float)scrollExtent;
                                float scrollItem = (float)scrollRange / (float)scrollOffset;
                                int itemSelect = Math.round(countSong / scrollItem);

                                recyclerView.removeOnScrollListener(this);
                                recyclerView.smoothScrollToPosition(itemSelect);
                            }
                        }
                    });
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

}