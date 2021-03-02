package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtDetailsFragmentBinding
import com.harnet.arttesting.viewModel.ArtDetailsViewModel

class ArtDetailsFragment : Fragment(R.layout.art_details_fragment) {
    private lateinit var viewModel: ArtDetailsViewModel
    private lateinit var dataBinding: ArtDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.art_details_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtDetailsViewModel::class.java)
    }

}