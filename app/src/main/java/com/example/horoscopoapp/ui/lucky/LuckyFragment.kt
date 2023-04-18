package com.example.horoscopoapp.ui.lucky

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscopoapp.R
import com.example.horoscopoapp.databinding.FragmentLuckyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckyFragment : Fragment() {

    private val viewModel by viewModels<LuckyViewModel>()
    private var _binding : FragmentLuckyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

    private fun prepareCard() {
        val image = when (Random.nextInt(0, 5)) {
            0 -> R.drawable.ic_compatibility
            1 -> R.drawable.ic_compatibility
            2 -> R.drawable.ic_compatibility
            3 -> R.drawable.ic_compatibility
            4 -> R.drawable.ic_compatibility
            5 -> R.drawable.ic_compatibility
            else -> R.drawable.ic_compatibility
        }

    }

}