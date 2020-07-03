package com.appsdrome.tmdbappwithmvvm.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.appsdrome.tmdbappwithmvvm.R;
import com.appsdrome.tmdbappwithmvvm.service.MovieDbService;
import com.appsdrome.tmdbappwithmvvm.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movieArrayList= new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData() {

        MovieDbService movieDbService= RetrofitInstance.getMovieData();
        Call<MovieDbResponse> call = movieDbService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {

                MovieDbResponse  movieDbResponse = response.body();

                if(movieDbResponse!=null && movieDbResponse.getMovies()!=null){
                    movieArrayList = (ArrayList<Movie>) movieDbResponse.getMovies();
                    mutableLiveData.setValue(movieArrayList);
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
