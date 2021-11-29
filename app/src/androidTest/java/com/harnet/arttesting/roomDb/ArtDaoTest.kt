package com.harnet.arttesting.roomDb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.harnet.arttesting.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/*
    Add to build.gradle to android section
    packagingOptions {
        exclude "win32-x86/attach_hotspot_windows.dll"
        exclude "win32-x86-64/attach_hotspot_windows.dll"
        exclude "META-INF/licenses/ASM"
        exclude "META-INF/AL2.0"
        exclude "META-INF/LGPL2.1"
    }
 */

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    // Create temporary database in RAM by injecting
    @Inject
    @Named("testDatabase")
    lateinit var database: ArtDatabase
//    private lateinit var database: ArtDatabase

    private lateinit var dao: ArtDao

    @Before
    fun setup() {
        // Create temporary database in RAM by usual way
        /*
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        */

        hiltRule.inject()

        dao = database.artDao()
    }

    @Test
    fun insertArtTesting() = runBlockingTest {
        val exampleArt = Art("Test art", "Test author", "test.com", 1503, 1)
        dao.insertArt(exampleArt)

        val artsList = dao.getAllArts().getOrAwaitValue()
        assertThat(artsList).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runBlockingTest {
        val exampleArt = Art("Test art", "Test author", "test.com", 1503, 1)
        dao.insertArt(exampleArt)
        dao.deleteArt(exampleArt)

        val artsList = dao.getAllArts().getOrAwaitValue()
        assertThat(artsList).doesNotContain(exampleArt)
    }

}