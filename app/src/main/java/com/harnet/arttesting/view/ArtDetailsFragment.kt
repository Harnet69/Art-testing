package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harnet.arttesting.R
import com.harnet.arttesting.viewModel.ArtDetailsViewModel

class ArtDetailsFragment : Fragment(R.layout.art_details_fragment) {
    private lateinit var viewModel: ArtDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtDetailsViewModel::class.java)
    }

}