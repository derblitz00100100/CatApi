package com.example.ghibliapi;

import android.graphics.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CatService {
    String BASE_URL = "https://cat-fact.herokuapp.com/";

    @GET("facts/random")
    Call<CatFact> getFacts(@Query("animal_type") String type, @Query("amount") int amt);
}
