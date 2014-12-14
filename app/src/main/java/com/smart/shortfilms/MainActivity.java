package com.smart.shortfilms;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.SpinnerAdapter;

import com.smart.shortfilms.activity.VideoActivity;
import com.smart.shortfilms.adapter.ImageListAdapter;
import com.smart.shortfilms.command.ImageListCommand;
import com.smart.shortfilms.fragment.AboutFragment;
import com.smart.shortfilms.util.AppUtil;
import com.smart.shortfilms.util.ListEndlessScrollListener;
import com.smart.shortfilms.vo.ShortFilm;


public class MainActivity extends Activity implements ActionBar.OnNavigationListener, AdapterView.OnItemClickListener {

    private GridView gridView;
    private ImageListAdapter adapter;
    private int selectedIndex;
    private ShareActionProvider mShareActionProvider;
    public static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        typeface = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        if (!AppUtil.isNetworkOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
            setContentView(R.layout.network_error_layout);
            Button refreshButton = (Button)findViewById(R.id.refresh);
            refreshButton.setTypeface(typeface);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setHomeButtonEnabled(false);
        } else {
            displayMainScreen();
        }
    }

    /**
     * This method is event handler for network error button click.
     *
     * @param view
     */
    public void networkOnClickHandler(View view) {
        if (AppUtil.isNetworkOnline((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))) {
            displayMainScreen();
        }
    }

    private void displayMainScreen() {
        setContentView(R.layout.activity_main);

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActionBar().getThemedContext(),
                R.array.spinner_array, android.R.layout.simple_spinner_dropdown_item);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (gridView != null) {
                gridView.setNumColumns(2);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (gridView != null) {
                gridView.setNumColumns(1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);


        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);
        MenuItem about_item = menu.findItem(R.id.menu_item_about);
        about_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AboutFragment fragment = new AboutFragment();
                fragment.show(getFragmentManager(), "About Fragment");
                return false;
            }
        });
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_heading_str));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_str));
        intent.setType("text/plain");
        setShareIntent(intent);
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void startServerComm() {

        adapter = new ImageListAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnScrollListener(new ListEndlessScrollListener(adapter, selectedIndex));
        ImageListCommand.pageNumber = 1;
        ImageListCommand command = new ImageListCommand(adapter, selectedIndex);
        command.execute();
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        selectedIndex = itemPosition;
        startServerComm();
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShortFilm ShortFilm = adapter.getItem(position);
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("ShortFilm", ShortFilm);
        startActivity(intent);
    }
}
