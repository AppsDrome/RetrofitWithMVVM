package com.appsdrome.tmdbappwithmvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;


import com.appsdrome.tmdbappwithmvvm.R;
import com.appsdrome.tmdbappwithmvvm.databinding.ActivityMovieBinding;
import com.appsdrome.tmdbappwithmvvm.model.Movie;
import com.bumptech.glide.Glide;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieBinding  activityMovieBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");

            activityMovieBinding.setMovie(movie);

            getSupportActionBar().setTitle(movie.getTitle());


        }

    }


}
