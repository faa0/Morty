package com.fara.morty.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.fara.morty.R
import com.fara.morty.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(requireContext()).load(args.entity.image).into(poster)
            name.text = String.format("Name: ${args.entity.name}")
            gender.text = String.format("Gender: ${args.entity.gender}")
            species.text = String.format("Species: ${args.entity.species}")
            status.text = String.format("Status: ${args.entity.status}")
        }
    }

    companion object {

        private const val DETAIL_FRAGMENT_ENTITY = "entity"

        fun makeArgs(rickMorty: Parcelable): Bundle {
            return Bundle().apply {
                putParcelable(DETAIL_FRAGMENT_ENTITY, rickMorty)
            }
        }
    }
}