package com.keepcoding.imgram.ui.MoviesDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ActivityMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    companion object{
        val EXTRA_ID = "MovieDetailActivity:id"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getLongExtra(EXTRA_ID, -1)
        println("BITO ${id}")

        viewModel.getMoviesDetail(id)
        viewModel.getMovieSimilar(id)

        viewModel.images.observe(this) {
            binding.DetailTitle.text = it.title
            println("BITO ${it.title}")

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
                .placeholder(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
                .into(binding.DetailimageView)

        }

        viewModel.similar.observe(this) {

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${it[0].posterPath}")
                .placeholder(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
                .into(binding.similarImageView)

            binding.DetailOverview.text = it[0].overview
            binding.similarTitle.text = it[0].title
        }


    }


}