package com.appsdrome.tmdbappwithmvvm.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.appsdrome.tmdbappwithmvvm.R;
import com.appsdrome.tmdbappwithmvvm.adapter.MovieAdapter;
import com.appsdrome.tmdbappwithmvvm.databinding.ActivityMainBinding;
import com.appsdrome.tmdbappwithmvvm.model.Movie;
import com.appsdrome.tmdbappwithmvvm.model.MovieDbResponse;
import com.appsdrome.tmdbappwithmvvm.service.MovieDbService;
import com.appsdrome.tmdbappwithmvvm.service.RetrofitInstance;
import com.appsdrome.tmdbappwithmvvm.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieArrayList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("TMBD Popular Movies");
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainActivityViewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
        getPopularMovies();

        swipeRefreshLayout= activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getPopularMovies();

            }
        });
    }

    private void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movieArrayList = (ArrayList<Movie>) moviesFromLiveData;
                showRecyclerView();
            }
        });


    }

    private void showRecyclerView() {
        recyclerView = activityMainBinding.rvMovies;
        movieAdapter = new MovieAdapter(this, movieArrayList);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}