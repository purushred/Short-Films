package com.smart.shortfilms.activity;

/**
 * Created by Purushotham on 06-11-2014.
 */

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.smart.shortfilms.MainActivity;
import com.smart.shortfilms.R;
import com.smart.shortfilms.adapter.ImageListAdapter;
import com.smart.shortfilms.fragment.VideoFragment;
import com.smart.shortfilms.vo.ShortFilm;

final public class VideoActivity extends Activity {

    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ShortFilm shortFilm = (ShortFilm) getIntent().getExtras().getSerializable("ShortFilm");

        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setVideoId(shortFilm.getyId());
        videoFragment.setTitle(shortFilm.getTitle());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.video_player, videoFragment).commit();
        getActionBar().setTitle(shortFilm.getTitle());

        TextView durationView = (TextView) findViewById(R.id.mediaDuration);
        titleView = (TextView) findViewById(R.id.mediaTitle);
        titleView.setTypeface(MainActivity.typeface);
        durationView.setText(shortFilm.getDuration());
        titleView.setText(shortFilm.getTitle());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (titleView != null) {
                titleView.setVisibility(View.GONE);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (titleView != null) {
                titleView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
