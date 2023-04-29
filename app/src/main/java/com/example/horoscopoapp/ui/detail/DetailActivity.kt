package com.example.horoscopoapp.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.horoscopoapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "DetailActivity"

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context): Intent = Intent(context, DetailActivity::class.java)
    }

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        viewModel.getHoroscope()
    }

    private fun initUI() {
        lifecycleScope.launch {
            //repeatOnLifecycle(lifecycle.State.STARTED) {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DetailUIState.Error -> {
                        //mostrar dialogo de error
                        binding.apply {
                            loading.isVisible = false
                            Toast.makeText(this@DetailActivity, "Error!!", Toast.LENGTH_SHORT).show()
                            Log.i(TAG, "Response error: $uiState")
                        }
                    }

                    DetailUIState.Loading -> {
                        //mostrar loading
                        binding.apply {
                            loading.isVisible = true
                        }
                        Toast.makeText(this@DetailActivity, "Loading!!", Toast.LENGTH_SHORT).show()
                        Log.i(TAG, "Response loading: $uiState")
                    }

                    is DetailUIState.Success -> {
                        //mostrar Info
                        binding.apply {
                            loading.isVisible = false
                        }
                        Toast.makeText(this@DetailActivity, uiState.horoscopeModel.horoscope, Toast.LENGTH_SHORT).show()
                        Log.i(TAG, "Response success: $uiState")
                    }
                }
            }
            //}
        }
    }
}