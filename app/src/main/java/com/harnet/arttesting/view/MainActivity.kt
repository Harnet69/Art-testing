package com.harnet.arttesting.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harnet.arttesting.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    private lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make sure that supportFragment manager knows about your fragmentFactory
        supportFragmentManager.fragmentFactory = fragmentFactory

        setContentView(R.layout.activity_main)
    }
}