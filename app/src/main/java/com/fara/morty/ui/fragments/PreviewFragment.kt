package com.fara.morty.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fara.morty.R
import com.fara.morty.adapter.CharacterAdapter
import com.fara.morty.databinding.FragmentPreviewBinding
import com.fara.morty.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        CharacterAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        initViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClickListener { _, rickMorty ->
            findNavController().navigate(
                R.id.action_previewFragment_to_detailFragment,
                DetailFragment.makeArgs(rickMorty)
            )
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.getCharacters().collectLatest { data ->
                adapter.submitData(data)
            }
        }
    }
}