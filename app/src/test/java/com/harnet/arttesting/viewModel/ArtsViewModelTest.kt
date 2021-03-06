package com.harnet.arttesting.viewModel

import com.harnet.arttesting.repository.FakeArtRepository
import org.junit.Before

class ArtsViewModelTest {
    private lateinit var viewModel: ArtsViewModel

    @Before
    fun setup(){
        //Test Doubles - FakeArtRepository
        viewModel = ArtsViewModel(FakeArtRepository())
    }
}