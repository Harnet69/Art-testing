package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harnet.arttesting.R
import com.harnet.arttesting.databinding.ArtsFragmentBinding
import com.harnet.arttesting.viewModel.ArtsViewModel

class ArtsFragment : Fragment(R.layout.arts_fragment) {
    private lateinit var viewModel: ArtsViewModel
    private lateinit var viewBinding: ArtsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtsViewModel::class.java)
        viewBinding = ArtsFragmentBinding.bind(view)

        clickAndAddArt(viewBinding.fab)
    }

    private fun clickAndAddArt(button: FloatingActionButton){
        button.setOnClickListener {
            Navigation.findNavController(button).navigate(ArtsFragmentDirections.actionArtsFragmentToArtAddingFragment())
        }
    }

}