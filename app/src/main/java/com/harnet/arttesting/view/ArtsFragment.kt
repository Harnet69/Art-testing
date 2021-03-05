package com.harnet.arttesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harnet.arttesting.R
import com.harnet.arttesting.adapter.ArtRecyclerAdapter
import com.harnet.arttesting.databinding.ArtsFragmentBinding
import com.harnet.arttesting.viewModel.ArtsViewModel
import javax.inject.Inject

class ArtsFragment @Inject constructor(
    val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.arts_fragment) {
    private lateinit var viewModel: ArtsViewModel
    private var viewBinding: ArtsFragmentBinding? = null

    //delete an item by swiping
    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        // delete the chosen art from a database
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // find the position
            val layoutPosition = viewHolder.layoutPosition
            // get the art from adapter
            val selectedArt = artRecyclerAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArtsViewModel::class.java)
        viewBinding = ArtsFragmentBinding.bind(view)

        observeViewModel()

        viewBinding?.artsListRecyclerView?.adapter = artRecyclerAdapter
        viewBinding?.artsListRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        // connect swipe callback to the RecyclerView
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(viewBinding?.artsListRecyclerView)

        viewBinding?.fab?.let { clickAndAddArt(it) }
    }

    private fun clickAndAddArt(button: FloatingActionButton){
        button.setOnClickListener {
            findNavController().navigate(ArtsFragmentDirections.actionArtsFragmentToArtAddingFragment())
        }
    }

    private fun observeViewModel(){
        viewModel.mArtsList.observe(viewLifecycleOwner, {artsList ->
            artRecyclerAdapter.arts = artsList
        })
    }

    override fun onDestroy() {
        viewBinding = null
        super.onDestroy()
    }

}