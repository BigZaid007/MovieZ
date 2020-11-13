package com.example.moviez

import com.example.moviez.api.GetMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {


    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }



    //now we will use the on Success instead of the log.d if the statement true,and we will use the on error when the statemnet is false
    fun getPopularMovies(page: Int = 1,
    onSuccess:(movies:List<Movie>) -> Unit,
                         onError:() -> Unit

    ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responsebody = response.body()

                        if (responsebody != null) {
                           onSuccess.invoke(responsebody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                    else
                        onError.invoke()

                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }

            })
    }
//the list is the responsebody
    fun getTopRatedMovies(page:Int=2, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit)
    {
        api.getTopRatedMovies(page = page)
            .enqueue(object :Callback<GetMoviesResponse>
            {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful)

                    {
                        val responsebody =response.body()


                        if (responsebody!=null)
                        {
                             onSuccess.invoke(responsebody.movies)
                        }
                        else
                            onError.invoke()
                    }
                    else
                        onError.invoke()
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                   onError.invoke()
                }


            }



            )




    }

    fun getUpComingMovies (page: Int=3, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit)

    {
        api.getUpComingMovies(page = page)
            .enqueue(

                object :Callback<GetMoviesResponse>
                {
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if (response.isSuccessful)
                        {
                            val responsebody=response.body()

                            if (responsebody!=null)
                            {

                                onSuccess.invoke(responsebody.movies)

                            }
                            else
                                onError.invoke()

                        }
                        else
                            onError.invoke()
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        onError.invoke()
                    }


                }



            )




    }



}



