package com.smart.shortfilms.fragment;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.smart.shortfilms.util.AppUtil;

public class VideoFragment extends YouTubePlayerFragment
        implements YouTubePlayer.OnInitializedListener,YouTubePlayer.PlaybackEventListener {

    private YouTubePlayer player;
    private String videoId;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(AppUtil.YOUTUBE_DATA_API_KEY, this);
    }

    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }

    public void setVideoId(String videoId) {
        if (videoId != null && !videoId.equals(this.videoId)) {
            this.videoId = videoId;
            if (player != null) {
                player.cueVideo(videoId);
            }
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean restored) {
        this.player = player;
        player.setPlaybackEventListener(this);

        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        if (!restored && videoId != null) {
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        this.player = null;
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onPlaying() {
    }

    @Override
    public void onSeekTo(int position) {

    }

    @Override
    public void onPaused() {
    }

    @Override
    public void onStopped() {}

    @Override
    public void onBuffering(boolean flag) {
    }

    public void setTitle(String title) {
        this.title = title;
    }
}