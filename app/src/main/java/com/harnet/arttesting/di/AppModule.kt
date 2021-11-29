package com.harnet.arttesting.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.harnet.arttesting.R
import com.harnet.arttesting.repository.ArtRepository
import com.harnet.arttesting.repository.ArtRepositoryInterface
import com.harnet.arttesting.retrofit.RetrofitAPI
import com.harnet.arttesting.roomDb.ArtDao
import com.harnet.arttesting.roomDb.ArtDatabase
import com.harnet.arttesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
// provide LifeCircle for provider functions
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ArtDatabase::class.java, "ArtDB").build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectArtRepository(dao: ArtDao, api: RetrofitAPI) = ArtRepository(dao, api) as ArtRepositoryInterface


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_img_default)
                .error(R.drawable.ic_launcher_foreground)
        )
}