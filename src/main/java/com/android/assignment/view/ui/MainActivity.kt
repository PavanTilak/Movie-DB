package com.android.assignment.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.android.assignment.databinding.ActivityMainBinding
import com.android.assignment.service.model.Movie
import com.android.assignment.service.repository.MainRepository
import com.android.assignment.service.repository.RetrofitService
import com.android.assignment.view.adapter.MainAdapter
import com.android.assignment.viewmodel.MainViewModel
import com.android.assignment.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * ViewModelProvider handles device orientation.
         * It Checks for the viewModel instance inside its viewModelStore Hashmap
         * If viewModel already created for the specific scope then return the previous viewModel
         * instead of creating a new instance.
         */
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.movieList.observe(this) {
            Log.d(TAG, "onCreate: $it")
            /**
             * API response giving only one object but need to show list of items.
             * Because of that adding same object to list multiple times to show as list
             */
            val movieList = mutableListOf<Movie>()
            movieList.add(it)
            movieList.add(it)
            movieList.add(it)
            movieList.add(it)

            adapter.setMovieList(movieList)
        }

        viewModel.errorMessage.observe(this) {
            Log.d(TAG, "Error while fetching movie list: $it")
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllMovies()
    }
}