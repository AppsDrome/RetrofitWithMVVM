package com.appsdrome.tmdbappwithmvvm.service;



import com.appsdrome.tmdbappwithmvvm.model.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbService {

    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String api_key);


    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMoviesWithPaging(@Query("api_key") String api_key,@Query("page") long page);
}
