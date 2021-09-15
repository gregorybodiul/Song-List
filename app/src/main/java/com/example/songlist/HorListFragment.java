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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HorListFragment extends Fragment {
    private boolean oneItemScroll = true;
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

        FloatingActionButton modeScrollButton = view.findViewById(R.id.one_item);

        modeScrollButton.setOnClickListener(view12 -> {
            oneItemScroll = !oneItemScroll;
            if(oneItemScroll){
                Toast.makeText(getContext(), "scrolling mode 1", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "scrolling mode 2", Toast.LENGTH_SHORT).show();
            }
        });
        scrolling(recyclerView);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.change_list);
        floatingActionButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_horListFragment_to_vertListFragment));

        return view;
    }

    private void scrolling(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if(e.getAction() == MotionEvent.ACTION_UP){
                    if(oneItemScroll){
                        int itemSelect = getItemSelect(rv);
                        recyclerView.smoothScrollToPosition(itemSelect);
                    }else{
                        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if(newState == 0){
                                    int itemSelect = getItemSelect(recyclerView);
                                    recyclerView.removeOnScrollListener(this);
                                    recyclerView.smoothScrollToPosition(itemSelect);
                                }
                            }
                        });
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) { }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
    }

    private int getItemSelect(@NonNull RecyclerView rv) {
        int scrollRange = rv.computeHorizontalScrollRange();
        int scrollOffset = rv.computeHorizontalScrollOffset();
        int scrollExtent = rv.computeHorizontalScrollExtent();

        float countSong = (float)scrollRange / (float)scrollExtent;
        float scrollItem = (float)scrollRange / (float)scrollOffset;
        return Math.round(countSong / scrollItem);
    }

}