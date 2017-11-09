package com.example.thagadur.imdbmovieapp.Module;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

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
    public static List<MovieDetailsDB> parseMovieDetailsStringToJson(String movieResultData){
        Log.i("Movie result Data", movieResultData);
        List<MovieDetailsDB> movieDetailsList=new ArrayList<>();

        try {
            JSONObject movieResultJsonObject=new JSONObject(movieResultData);
            JSONArray movieResultJsonArray=new JSONArray(movieResultJsonObject);

            System.out.println("size of the Json is "+movieResultData.length());
            for (int i=0;i<=movieResultJsonArray.length();i++){

                MovieDetailsDB movieDetailsDB=new MovieDetailsDB();


                movieDetailsDB.setMovieTitle(movieResultJsonArray.getJSONObject(i).getString("title"));
                /* movieDB.setMovieTitle(movieResultJsonArray.getJSONObject(i).getString("original_title"));
                movieDB.setMovieRating(movieResultJsonArray.getJSONObject(i).getString("vote_average"));
                movieDB.setMovieDescription(movieResultJsonArray.getJSONObject(i).getString("overview"));
                movieDB.setMovieReleaseDate(movieResultJsonArray.getJSONObject(i).getString("release_date"));
                movieDB.setMoviePosters(movieResultJsonArray.getJSONObject(i).getString("poster_path"));
                movieDB.setMoviePopularity(movieResultJsonArray.getJSONObject(i).getString("popularity"));
                movieDB.setMovieVoteCount(movieResultJsonArray.getJSONObject(i).getString("vote_count"));
                movieDB.setMovieId(movieResultJsonArray.getJSONObject(i).getString("id"));*/

                movieDetailsList.add(movieDetailsDB);
                System.out.println("hi hhh"+movieDetailsList.get(0));
            }
            Log.i("movieDbList Size",""+movieDetailsList.size());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieDetailsList;

    }
}
