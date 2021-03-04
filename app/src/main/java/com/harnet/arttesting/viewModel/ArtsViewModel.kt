package com.harnet.arttesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harnet.arttesting.repository.ArtRepositoryInterface

class ArtsViewModel @ViewModelInject constructor(
    // in tests we fake this repository
    private val repository: ArtRepositoryInterface
): ViewModel() {
    val artsList = repository.getAllArts()
}