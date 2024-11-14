package com.kodiiiofc.urbanuniversity.moviedatabase.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kodiiiofc.urbanuniversity.moviedatabase.data.MovieRepositoryImpl
import com.kodiiiofc.urbanuniversity.moviedatabase.data.MovieService
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Provides
    fun baseUrl() = "https://api.kinopoisk.dev/"

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    fun gson() : Gson =
        GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson) : MovieService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient())
            .build()
            .create(MovieService::class.java)
}