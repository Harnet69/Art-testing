package com.harnet.arttesting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.roomDb.Art
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(
    // in tests we fake this repository
    private val repository: ArtRepositoryInterface
): ViewModel() {
    val mArtsList = repository.getAllArts()

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }
}