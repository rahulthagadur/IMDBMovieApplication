package com.example.thagadur.imdbmovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thagadur.imdbmovieapp.Contants.Constant;
import com.example.thagadur.imdbmovieapp.Module.MovieDB;
import com.example.thagadur.imdbmovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Thagadur on 11/4/2017.
 */

public  class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    List<MovieDB> MovieDBList;
    Context context;

    public MovieListAdapter(Context context, List<MovieDB> MovieDBList) {
        this.context = context;
        this.MovieDBList = MovieDBList;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieDB MovieDB = MovieDBList.get(position);
        String moviePoster = MovieDB.getMoviePosters();
        String movieTitle = MovieDB.getMovieTitle();
        String movieSynopsis = MovieDB.getMovieDescription();
        String movieReleaseDate = MovieDB.getMovieReleaseDate();
        String movieRating = MovieDB.getMovieRating();
        holder.movieTitle.setText(movieTitle);
        holder.moviePopularity.setText(movieRating);
        holder.movieReleaseDate.setText(movieReleaseDate);
        Picasso.with(context).load(Constant.POSTER_PATH + moviePoster).into(holder.imageViewMoviePoster);
    }

    @Override
    public int getItemCount() {
        return MovieDBList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMoviePoster,moviePopularityStar;
        TextView movieTitle, movieReleaseDate, moviePopularity,movieVoteCount;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageViewMoviePoster = itemView.findViewById(R.id.movie_image);
            movieTitle=itemView.findViewById(R.id.movie_title);
            movieReleaseDate=itemView.findViewById(R.id.movie_release_date);
            moviePopularity=itemView.findViewById(R.id.movie_popularity);
            //moviePopularityStar=itemView.findViewById(R.id.popularity_image_star);
            movieVoteCount=itemView.findViewById(R.id.movie_vote_count);
        }
    }
}
