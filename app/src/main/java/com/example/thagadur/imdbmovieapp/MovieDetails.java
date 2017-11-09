package com.example.thagadur.imdbmovieapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.thagadur.imdbmovieapp.Module.MovieDbJsonParse;
import com.example.thagadur.imdbmovieapp.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;


/**
 * Created by Thagadur on 11/9/2017.
 */

public class MovieDetails extends AppCompatActivity {

    public static String movieId= null;
    public static String movieDetailsUrl=null;
    public static String apiKey="?api_key=8496be0b2149805afa458ab8ec27560c";
    List<MovieDetails> movieDBList;
    Context context;
    TextView movieTitleText,movieDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_screen);

        Bundle intent=getIntent().getExtras();
        movieId=intent.getString("movieId");

        movieDetailsUrl="http://api.themoviedb.org/3/movie/"+movieId+apiKey;
        loadMovieDetailsData(movieDetailsUrl);

        /*String moviePoster=intent.getString("moviePoster");
        String movieTitle=intent.getString("movieTitle");
        String movieReleaseDate=intent.getString("movieReleaseDate");
        String movieRating=intent.getString("movieRating");
        String movieDescription=intent.getString("movieDescription");*/

        initialisationOfId();
        /*movieTitleText.setText(movieTitle);
        movieDescriptionText.setText(movieDescription);
*/
    }

    public void loadMovieDetailsData(String movieDetailsUrl){
        URL url= NetworkUtils.buildUrl(movieDetailsUrl);
        new RequestMovieDetailsdata().execute(url);
    }
    public void initialisationOfId(){
        context=this;
        movieTitleText=(TextView)findViewById(R.id.title);
        movieDescriptionText=(TextView)findViewById(R.id.description);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadMovieAdapter(String movieResponseData) {
        movieDBList = MovieDbJsonParse.parseMovieDetailsStringToJson(movieResponseData);
        movieTitleText.setText(movieDBList.get(0).getTitle().toString());
       /* movieListAdapter = new MovieListAdapter(context, movieDBList);
        movieRecyclerView.setAdapter(movieListAdapter);*/
    }


    class RequestMovieDetailsdata extends AsyncTask<URL,Void,String> {

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

            return movieDetailsResponseData ;
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
