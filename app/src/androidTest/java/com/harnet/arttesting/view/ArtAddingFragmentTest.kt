package com.harnet.arttesting.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.harnet.arttesting.R
import com.harnet.arttesting.getOrAwaitValue
import com.harnet.arttesting.launchFragmentInHiltContainer
import com.harnet.arttesting.repository.FakeArtRepositoryAndroidTest
import com.harnet.arttesting.roomDb.Art
import com.harnet.arttesting.viewModel.ArtAddingViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtAddingFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtAddingToSearchFragment() {
        // Mock Nav controller by Mockito
        val navController = Mockito.mock(NavController::class.java)

        //it doesn't work with Hilt without HiltExtension
        launchFragmentInHiltContainer<ArtAddingFragment>(factory = fragmentFactory) {
            // here we can get components of the fragment
            Navigation.setViewNavController(requireView(), navController)
        }

        //tell the fragment to click to art details image by Espresso
        Espresso.onView(ViewMatchers.withId(R.id.artImage_artAdding)).perform(ViewActions.click())

        //check id a navigation has been changed
        Mockito.verify(navController).navigate(
            ArtAddingFragmentDirections.actionArtAddingFragmentToSearchFragment()
        )
    }

    @Test
    fun testOnBackPressed() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtAddingFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun testAddArt(){
        val testViewModel = ArtAddingViewModel(FakeArtRepositoryAndroidTest())

        launchFragmentInHiltContainer<ArtAddingFragment>(factory = fragmentFactory) {
            // make viewModel public in the related fragment and pass there the created one
            viewModel = testViewModel
        }

        Espresso.onView(withId(R.id.artName_artAdding)).perform(ViewActions.replaceText("Test name"))
        Espresso.onView(withId(R.id.artAuthor_artAdding)).perform(ViewActions.replaceText(  "Test author"))
        Espresso.onView(withId(R.id.artYear_artAdding)).perform(ViewActions.replaceText("1503"))
        Espresso.onView(withId(R.id.artAddBtn_artAdding)).perform(ViewActions.click())

        val artsList = testViewModel.mInsertArtMsg.getOrAwaitValue()
        assertThat(artsList.data).isEqualTo(
            Art("Test name", "Test author", "", 1503 )
        )
    }
}