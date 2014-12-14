package com.smart.shortfilms.command;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.smart.shortfilms.R;
import com.smart.shortfilms.adapter.ImageListAdapter;
import com.smart.shortfilms.service.ShortFilmService;
import com.smart.shortfilms.util.AppUtil;
import com.smart.shortfilms.vo.ShortFilm;

import java.util.ArrayList;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Purushotham on 04-11-2014.
 */
public class ImageListCommand extends AsyncTask<Void, Void, List<ShortFilm>> {

    private final ImageListAdapter adapter;
    private final int sortBy;
    public static int pageNumber = 1;
    private final int pageSize = 25;

    public ImageListCommand(ImageListAdapter adapter, int sortBy) {
        this.adapter = adapter;
        this.sortBy = sortBy;
    }

    @Override
    protected void onPreExecute() {
        View progressBar = adapter.getContext().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Log.e("MovieListEndlessScrollListener", "Retrieving Pageq# "+pageNumber);
    }

    @Override
    protected void onPostExecute(List<ShortFilm> shortFilms) {
        adapter.setData(shortFilms);
        adapter.notifyDataSetChanged();
        pageNumber++;
        View progressBar = adapter.getContext().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected List<ShortFilm> doInBackground(Void... params) {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {

                request.addQueryParam("sortBy", String.valueOf(sortBy));
                request.addQueryParam("pageNo", String.valueOf(pageNumber));
                request.addQueryParam("pageSize", String.valueOf(pageSize));
            }
        };
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(AppUtil.APP_ENDPOINT_URL)
                .setRequestInterceptor(requestInterceptor)
                .build();
        ShortFilmService shortFilmService = adapter.create(ShortFilmService.class);
        List<ShortFilm> films = new ArrayList<ShortFilm>(pageSize);
        try {
            films = shortFilmService.getShortFilms();

        } catch (Exception ex) {
            Log.e("Server communication Error", ex.getMessage());
        }
        return films;
    }
}
