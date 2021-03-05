package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtDetailsFragmentBinding
import com.harnet.arttesting.util.FragmentBindingProvider
import com.harnet.arttesting.viewModel.ArtDetailsViewModel

class ArtDetailsFragment : Fragment(R.layout.art_details_fragment) {
    private lateinit var viewModel: ArtDetailsViewModel
    private val binding: ArtDetailsFragmentBinding by FragmentBindingProvider(R.layout.art_details_fragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtDetailsViewModel::class.java)

        //handling onBackPress dispatcher
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}