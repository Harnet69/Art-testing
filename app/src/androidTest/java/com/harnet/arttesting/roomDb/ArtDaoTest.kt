package com.harnet.arttesting.roomDb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.harnet.arttesting.getOrAwaitValue
import com.harnet.arttesting.room.Art
import com.harnet.arttesting.room.ArtDao
import com.harnet.arttesting.room.ArtDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
class ArtDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArtDatabase
    private lateinit var dao: ArtDao

    @Before
    fun setup(){
        // Create temporary database in RAM
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun insertArtTesting() = runBlockingTest{
        val exampleArt = Art("Test art", "Test author", "test.com", 1503)
        dao.insertArt(exampleArt)

        val artsList = dao.getAllArts().getOrAwaitValue()
        assertThat(artsList).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runBlockingTest{

    }

}