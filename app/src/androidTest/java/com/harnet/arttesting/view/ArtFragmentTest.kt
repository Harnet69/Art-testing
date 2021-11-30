package com.harnet.arttesting.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.harnet.arttesting.R
import com.harnet.arttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.internal.artificialFrame
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class ArtFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtsFragmentToArtDetailsFragment(){

        // Mock Nav controller by Mockito
        val navController = Mockito.mock(NavController::class.java)

        //it doesn't work with Hilt without HiltExtension
        launchFragmentInHiltContainer<ArtsFragment>(factory = fragmentFactory){
            // here we can get components of the fragment
            Navigation.setViewNavController(requireView(), navController)
        }

        //tell the fragment to click float btn by Espresso
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        //check id a navigation has been changed
        Mockito.verify(navController).navigate(
            ArtsFragmentDirections.actionArtsFragmentToArtAddingFragment(null)
        )
    }
}