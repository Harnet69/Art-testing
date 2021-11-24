package com.harnet.arttesting.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.harnet.arttesting.MainCoroutineRule
import com.harnet.arttesting.getOrAwaitValueTest
import com.harnet.arttesting.repository.FakeArtRepository
import com.harnet.arttesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@ExperimentalCoroutinesApi
class ArtAddingViewModelTest {

    // Set only one, main thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Set only one, main thread
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtAddingViewModel

    @Before
    fun setup(){
        //Test Doubles - FakeArtRepository
        viewModel = ArtAddingViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.addArt("Mona Lisa", "Da Vinci", "")
        // convert LiveData in regular data
        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(isAdded.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error`(){
        viewModel.addArt("", "Da Vinci", "1503")
        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(isAdded.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert art without artistsName returns error`(){
        viewModel.addArt("", "Da Vinci", "1503")
        val isAdded = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(isAdded.status).isEqualTo(Status.ERROR)
    }
}