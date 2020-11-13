package com.example.moviez

import com.example.moviez.api.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "9e0344d07ada49984d1eb012bcb516c7",
        @Query("page") page: Int
    ): retrofit2.Call<GetMoviesResponse>



   @GET("movie/top_rated")
   fun getTopRatedMovies (

       @Query("api_key") apiKey: String="9e0344d07ada49984d1eb012bcb516c7",
       @Query("page") page: Int

   ):retrofit2.Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies (


        @Query ("api_key") apiKey: String="9e0344d07ada49984d1eb012bcb516c7",
        @Query("page") page: Int
    ):retrofit2.Call<GetMoviesResponse>






}