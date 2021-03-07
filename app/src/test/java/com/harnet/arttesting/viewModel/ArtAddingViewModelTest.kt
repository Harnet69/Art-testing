package com.harnet.arttesting.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.harnet.arttesting.MainCoroutineRule
import com.harnet.arttesting.repository.FakeArtRepository
import com.harnet.arttesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class ArtAddingViewModelTest {
    private lateinit var viewModel: ArtAddingViewModel

    //runs all in the Main thread
    @get:Rule
        var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    //because in Test folder there is not an emulator and no Main thread
    @get:Rule
        var mainCoroutineRule: MainCoroutineRule = MainCoroutineRule()

    @Before
   fun setup(){
        viewModel = ArtAddingViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without art name returns error`(){
        viewModel.validateUserInput("", "Da Vinci", "1500")
        //this value is LiveData which should be converted to a regular data by Google class method
        val value = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without art author returns error`(){
        viewModel.validateUserInput("Mona Lisa", "", "1500")
        val value = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.validateUserInput("Mona Lisa", "Da Vinci", "")
        val value = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art with literal year returns error`(){
        viewModel.validateUserInput("Mona Lisa", "Da Vinci", "XXYY")
        val value = viewModel.mInsertArtMsg.getOrAwaitValueTest()
        assertThat(value.message).isEqualTo("Year should be a number")
    }

    @Test
    fun `set image URL set SelectedImgUrl`(){
        viewModel.setSelectedImg("http://www.someimage.png")
        val value = viewModel.selectedImgUrl.getOrAwaitValueTest()
        assertThat(value).isEqualTo("http://www.someimage.png")
    }

}