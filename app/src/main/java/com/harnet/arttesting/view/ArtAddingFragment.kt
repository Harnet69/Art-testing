package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harnet.arttesting.R
import com.harnet.arttesting.viewModel.ArtAddingViewModel

class ArtAddingFragment : Fragment(R.layout.art_adding_fragment) {
    private lateinit var viewModel: ArtAddingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtAddingViewModel::class.java)
    }

}