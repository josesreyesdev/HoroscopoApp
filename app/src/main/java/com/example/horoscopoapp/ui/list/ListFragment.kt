package com.example.horoscopoapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.horoscopoapp.databinding.FragmentListBinding
import com.example.horoscopoapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCapricornio.setOnClickListener { showDetail() }
        binding.btnAries.setOnClickListener { showDetail() }
        binding.btnLeo.setOnClickListener { showDetail() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showDetail() {
        //val intent = Intent( requireContext(), DetailActivity::class.java)
        startActivity( DetailActivity.create(requireContext()))
    }
}