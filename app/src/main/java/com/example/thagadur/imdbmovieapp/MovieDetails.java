package com.example.thagadur.imdbmovieapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.thagadur.imdbmovieapp.Contants.Constant;
import com.example.thagadur.imdbmovieapp.Module.MovieDbJsonParse;
import com.example.thagadur.imdbmovieapp.Module.MovieDetailsDB;
import com.example.thagadur.imdbmovieapp.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Thagadur on 11/9/2017.
 */

public class MovieDetails extends AppCompatActivity {

    public static String movieId = null;
    public static String movieDetailsUrl = null;
    public static String moviePostersUrl=null;
    public static String apiKey = "?api_key=8496be0b2149805afa458ab8ec27560c";
    List<MovieDetailsDB> movieDetailsDBs;
    Context context;
    TextView movieTitleText, movieReleaseDateText, movieBudgetText, movieRevenueText, movieReleaseStatusText;
    TextView movieVoteAverageText, movieDescriptionText, movieTagLineText, movieVoteCountUsers;
    RatingBar movieRatingBar,movieSingleStarRatingBar;
    ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_screen);

        Bundle intent = getIntent().getExtras();
        movieId = intent.getString("movieId");
        initialisationOfId();
        movieDetailsUrl = "http://api.themoviedb.org/3/movie/" + movieId + apiKey;
        moviePostersUrl = "http://api.themoviedb.org/3/movie/"+movieId+"/images/"+apiKey;
        loadMovieDetailsData(movieDetailsUrl);
        //laodMoviePostersData(moviePostersUrl);

    }
  /*  public void laodMoviePostersData(String moviePostersUrl){
        URL url =NetworkUtils.buildUrl(moviePostersUrl);
        new RequestMoviePostersdata().execute(url);
    }*/

    public void loadMovieDetailsData(String movieDetailsUrl) {
        URL url = NetworkUtils.buildUrl(movieDetailsUrl);
        new RequestMovieDetailsdata().execute(url);
    }

    public void initialisationOfId() {
        context = this;
        movieTitleText = (TextView) findViewById(R.id.title);
        movieTagLineText = (TextView) findViewById(R.id.tag_line);
        movieReleaseDateText = (TextView) findViewById(R.id.release_date);
        movieBudgetText = (TextView) findViewById(R.id.budget);
        movieRevenueText = (TextView) findViewById(R.id.revenue);
        movieReleaseStatusText = (TextView) findViewById(R.id.status);
        movieVoteAverageText = (TextView) findViewById(R.id.vote_average);
        movieDescriptionText = (TextView) findViewById(R.id.description);
        movieVoteCountUsers = (TextView) findViewById(R.id.vote_count_users);
        movieRatingBar = (RatingBar) findViewById(R.id.ratingBar2);
        movieImage = (ImageView) findViewById(R.id.movieImage);
        movieSingleStarRatingBar=(RatingBar)findViewById(R.id.movie_single_star_rating_bar);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadMovieAdapter(String movieResponseData) {
        Log.i("Hi see the Json String ", movieResponseData);
        movieDetailsDBs = MovieDbJsonParse.parseMovieDetailsStringToJson(movieResponseData);
        Log.i("ArrayList Size", "" + movieDetailsDBs.size());
        setDataIntoLayoutFields(movieDetailsDBs);
    }

    private void loadMoviePostersAdapter(String movieResponsePosterData){

    }

    //    Stting Data into the Text fields From the movieDetailsDBs List
    public void setDataIntoLayoutFields(List<MovieDetailsDB> movieDetailsDBs) {
        //Formatting the Numbers into Readable Form
        int movieBudget = Integer.parseInt(movieDetailsDBs.get(0).getMovieBudget().toString());
        int movieRevenue = Integer.parseInt(movieDetailsDBs.get(0).getMovieRevenue().toString());
        int VoteCountUsers = Integer.parseInt(movieDetailsDBs.get(0).getMovieVoteCountUsers().toString());
        //float movieVoteAvg = Float.parseFloat(movieDetailsDBs.get(0).getMovieVoteAverage().toString());

        Picasso.with(context).load(Constant.POSTER_PATH + movieDetailsDBs.get(0).getMovieImage().toString()).into(movieImage);
        movieTitleText.setText(movieDetailsDBs.get(0).getMovieTitle().toString());
        movieTagLineText.setText(movieDetailsDBs.get(0).getMovieTagLine().toString());
        movieReleaseDateText.setText(movieDetailsDBs.get(0).getMovieRealeaseDate().toString());
        movieBudgetText.setText("Budget: " + NumberFormat.getIntegerInstance().format(movieBudget));
        movieRevenueText.setText("Revenue: " + NumberFormat.getIntegerInstance().format(movieRevenue));
        movieReleaseStatusText.setText("Staus: " + movieDetailsDBs.get(0).getMovieStatus().toString());
        movieVoteAverageText.setText(movieDetailsDBs.get(0).getMovieVoteAverage().toString() + "/10");
        movieDescriptionText.setText(movieDetailsDBs.get(0).getMovieDescription().toString());
        movieVoteCountUsers.setText(NumberFormat.getIntegerInstance().format(VoteCountUsers) + " users");
        //float d = movieVoteAvg * 10;
        movieRatingBar.setRating(Float.parseFloat(movieDetailsDBs.get(0).getMovieVoteAverage())/2);
        movieSingleStarRatingBar.setRating(Float.parseFloat(movieDetailsDBs.get(0).getMovieVoteAverage())/10);

        //movieRatingBar.setStepSize(d);
    }
/*    class  RequestMoviePostersdata extends  AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String moviePostersResponseData=null;
            URL url =urls[0];
            try {
                moviePostersResponseData=NetworkUtils.getResponseFromMovieDb(url);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return moviePostersResponseData;
        }

        protected void onPostExecute(String movieResponsePosterData) {
            super.onPostExecute(movieResponsePosterData);
            Log.d("Data", movieResponsePosterData);
            if (movieResponsePosterData!= null) {
                loadMoviePostersAdapter(movieResponsePosterData);
            }
        }
    }*/

    class RequestMovieDetailsdata extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            String movieDetailsResponseData = null;
            URL url = urls[0];
            try {
                movieDetailsResponseData = NetworkUtils.getResponseFromMovieDb(url);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ErrorMessage", e.getMessage());
            }
            return movieDetailsResponseData;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String movieResponseData) {
            super.onPostExecute(movieResponseData);
            Log.d("Data", movieResponseData);
            if (movieResponseData != null) {
                loadMovieAdapter(movieResponseData);
            }
        }
    }
}
