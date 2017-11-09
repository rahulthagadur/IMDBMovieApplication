package com.example.thagadur.imdbmovieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thagadur.imdbmovieapp.Contants.Constant;
import com.example.thagadur.imdbmovieapp.Module.MovieDB;
import com.example.thagadur.imdbmovieapp.MovieDetails;
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
        final MovieDB MovieDB = MovieDBList.get(position);
        final String moviePoster = MovieDB.getMoviePosters();
        final String movieTitle = MovieDB.getMovieTitle();
        final String movieDescription = MovieDB.getMovieDescription();
        final String movieReleaseDate = MovieDB.getMovieReleaseDate();
        final String movieRating = MovieDB.getMovieRating();
        final String movieId = MovieDB.getMovieId();
        holder.movieTitle.setText(movieTitle);
        holder.moviePopularity.setText(movieRating);
        holder.movieReleaseDate.setText(movieReleaseDate);
        Picasso.with(context).load(Constant.POSTER_PATH + moviePoster).into(holder.imageViewMoviePoster);

        holder.imageViewMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MovieDetails.class);
                intent.putExtra("movieId",movieId);
//                intent.putExtra("moviePoster",moviePoster);
//                intent.putExtra("movieTitle",movieTitle);
//                intent.putExtra("movieReleaseDate",movieReleaseDate);
//                intent.putExtra("movieRating",movieRating);
//                intent.putExtra("movieDescription",movieDescription);
                context.startActivity(intent);
            }
        });
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
