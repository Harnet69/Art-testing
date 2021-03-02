package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harnet.arttesting.R
import com.harnet.arttesting.viewModel.ArtAddingViewModel

class ArtAddingFragment : Fragment() {

    companion object {
        fun newInstance() = ArtAddingFragment()
    }

    private lateinit var viewModel: ArtAddingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.art_adding_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtAddingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}