package com.harnet.arttesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harnet.arttesting.R
import com.harnet.arttesting.viewModel.ArtsViewModel

class ArtsFragment : Fragment() {

    private lateinit var viewModel: ArtsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.arts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArtsViewModel::class.java)
        clickAndAddArt(view.findViewById(R.id.fab))
    }

    private fun clickAndAddArt(button: FloatingActionButton){
        button.setOnClickListener {
            Navigation.findNavController(button).navigate(ArtsFragmentDirections.actionArtsFragmentToArtAddingFragment())
        }
    }

}