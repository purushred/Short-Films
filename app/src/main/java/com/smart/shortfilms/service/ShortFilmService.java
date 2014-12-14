package com.smart.shortfilms.service;

import com.smart.shortfilms.vo.ShortFilm;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by Purushotham on 04-11-2014.
 */
public interface ShortFilmService {

    @GET("/list")
    List<ShortFilm> getShortFilms();
}
