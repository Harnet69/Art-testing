package com.harnet.arttesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        // check what class it is and add an appropriate dependency
        return when(className){
            ArtAddingFragment::class.java.name -> ArtAddingFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}