package com.example.thagadur.imdbmovieapp.Module;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.thagadur.imdbmovieapp.MovieDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thagadur on 11/4/2017.
 */

public class MovieDbJsonParse {

    public static List<MovieDB> parseMovieStringToJson(String movieResultData){
        List<MovieDB> movieDBList=new ArrayList<>();
        try {
            JSONObject movieResultJsonObject=new JSONObject(movieResultData);
            JSONArray movieResultJsonArray=movieResultJsonObject.getJSONArray("results");
            for (int i=0;i<=movieResultJsonArray.length();i++){
                MovieDB movieDB=new MovieDB();

                movieDB.setMovieTitle(movieResultJsonArray.getJSONObject(i).getString("original_title"));
                movieDB.setMovieRating(movieResultJsonArray.getJSONObject(i).getString("vote_average"));
                movieDB.setMovieDescription(movieResultJsonArray.getJSONObject(i).getString("overview"));
                movieDB.setMovieReleaseDate(movieResultJsonArray.getJSONObject(i).getString("release_date"));
                movieDB.setMoviePosters(movieResultJsonArray.getJSONObject(i).getString("poster_path"));
                movieDB.setMoviePopularity(movieResultJsonArray.getJSONObject(i).getString("popularity"));
                movieDB.setMovieVoteCount(movieResultJsonArray.getJSONObject(i).getString("vote_count"));
                movieDB.setMovieId(movieResultJsonArray.getJSONObject(i).getString("id"));

                movieDBList.add(movieDB);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDBList;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static List<MovieDetails> parseMovieDetailsStringToJson(String movieResultData){
        List<MovieDetails> movieDBList=new ArrayList<>();
        try {
            JSONObject movieResultJsonObject=new JSONObject(movieResultData);
            JSONArray movieResultJsonArray=new JSONArray(movieResultJsonObject);
            for (int i=0;i<=movieResultJsonArray.length();i++){
                MovieDetails movieDetails=new MovieDetails();
                movieDetails.setTitle(movieResultJsonArray.getJSONObject(i).getString("title"));
               /* movieDB.setMovieTitle(movieResultJsonArray.getJSONObject(i).getString("original_title"));
                movieDB.setMovieRating(movieResultJsonArray.getJSONObject(i).getString("vote_average"));
                movieDB.setMovieDescription(movieResultJsonArray.getJSONObject(i).getString("overview"));
                movieDB.setMovieReleaseDate(movieResultJsonArray.getJSONObject(i).getString("release_date"));
                movieDB.setMoviePosters(movieResultJsonArray.getJSONObject(i).getString("poster_path"));
                movieDB.setMoviePopularity(movieResultJsonArray.getJSONObject(i).getString("popularity"));
                movieDB.setMovieVoteCount(movieResultJsonArray.getJSONObject(i).getString("vote_count"));
                movieDB.setMovieId(movieResultJsonArray.getJSONObject(i).getString("id"));*/

                movieDBList.add(movieDetails);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDBList;

    }
}
