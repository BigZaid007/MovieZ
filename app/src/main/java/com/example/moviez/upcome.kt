package com.example.moviez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class upcome : AppCompatActivity() {




        private lateinit var UpComeMovies: RecyclerView
        private lateinit var UpComeMoviesAdapter: MoviesAdapter
        private lateinit var UpComeMoviesLayoutMgr: LinearLayoutManager

        private  var UpComeMoviesPage=1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcome)

        UpComeMovies = findViewById(R.id.recyclecoming)
        UpComeMoviesLayoutMgr = GridLayoutManager(
                this,3,
                GridLayoutManager.VERTICAL,
                false
            )


        UpComeMovies.layoutManager = UpComeMoviesLayoutMgr
        UpComeMoviesAdapter = MoviesAdapter(mutableListOf()){ movie -> showMovieDetails(movie) }
        UpComeMovies.adapter = UpComeMoviesAdapter


            //popularMoviesLayoutMgr=GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)


            getUpComeMovies()














        }

        private fun onUpComeMoviesFetched(movies: List<Movie>) {
            UpComeMoviesAdapter.appendMovies(movies)
            attachPopularMoviesOnScrollListener()
        }
        private fun onError() {
            Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()

        }

        private fun getUpComeMovies() {
            MoviesRepository.getUpComingMovies(
                UpComeMoviesPage,
                ::onUpComeMoviesFetched,
                ::onError
            )
        }


        private fun attachPopularMoviesOnScrollListener() {
            UpComeMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val totalItemCount = UpComeMoviesLayoutMgr.itemCount
                    val visibleItemCount = UpComeMoviesLayoutMgr.childCount
                    val firstVisibleItem = UpComeMoviesLayoutMgr.findFirstVisibleItemPosition()

                    if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                        UpComeMovies.removeOnScrollListener(this)
                        UpComeMoviesPage++
                        getUpComeMovies()
                    }
                }
            })
        }




        private fun showMovieDetails(movie: Movie) {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
            intent.putExtra(MOVIE_POSTER, movie.posterPath)
            intent.putExtra(MOVIE_TITLE, movie.title)
            intent.putExtra(MOVIE_RATING, movie.rating)
            intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
            intent.putExtra(MOVIE_OVERVIEW, movie.overview)
            startActivity(intent)
        }


    }
