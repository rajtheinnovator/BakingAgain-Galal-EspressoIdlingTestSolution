package com.example.ga.bakingagain;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class VideoFragment extends Fragment {
    private Dish dish;
    private List<Steps> stepsList;
    private Steps steps;
    private Context c;
    private SimpleExoPlayer player;
    private PlayerView PlayerView;

    public VideoFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_fragment, container, false);
        PlayerView = rootView.findViewById(R.id.exo_player_view);
        Bundle bundle = this.getArguments();
        dish = bundle.getParcelable("DISH");
        int i =bundle.getInt("i");
        stepsList =dish.getSteps();
        steps =stepsList.get(i);
        String s = steps.getVideoURL();
        String m = steps.getThumbnailURL();
        TextView textView = rootView.findViewById(R.id.videoUrl);
        textView.setText(s);

        player = ExoPlayerFactory.newSimpleInstance(getContext(),new DefaultTrackSelector());
        PlayerView.setPlayer(player);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(),"BakingAgain"));
        if (m.isEmpty()&& s!=null){
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(s));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        } else {
            PlayerView.setVisibility(View.GONE);
        }


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        PlayerView.setPlayer(null);
        player.release();
        player = null;
    }
}
