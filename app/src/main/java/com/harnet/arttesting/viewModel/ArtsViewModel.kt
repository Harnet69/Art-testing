package com.harnet.arttesting.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.room.Art
import kotlinx.coroutines.launch

class ArtsViewModel @ViewModelInject constructor(
    // in tests we fake this repository
    private val repository: ArtRepositoryInterface
): ViewModel() {
    val mArtsList = repository.getAllArts()

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }
}