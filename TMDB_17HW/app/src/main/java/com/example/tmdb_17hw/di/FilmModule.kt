package com.example.tmdb_17hw.di

import android.content.Context
import androidx.room.Room
import com.example.tmdb_17hw.data.database.TmdbDataSource
import com.example.tmdb_17hw.data.database.TmdbDatabase
import com.example.tmdb_17hw.data.database.dao.FilmDao
import com.example.tmdb_17hw.data.network.remote.ApiFilm
import com.example.tmdb_17hw.data.network.remote.TmdbRemoteSource
import com.example.tmdb_17hw.data.network.remote.TmdbSource
import com.example.tmdb_17hw.data.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmModule {
    @Singleton
    @Provides
    fun provideInterceptor() : Interceptor {
        return Interceptor {
            val request = it.request()
            val url = request.url().newBuilder()
                .addQueryParameter("api_key","c1559a7d14066bd237f26b00ec904fe8")
                .addQueryParameter("language","en-US")
                .build()
            val new = request.newBuilder()
                .url(url)
                .build()
            it.proceed(new)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) : ApiFilm{
        return retrofit.create(ApiFilm::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiFilm: ApiFilm) : TmdbSource{
        return TmdbRemoteSource(apiFilm)
    }
    @Singleton
    @Provides
    fun provideLocalDataSource(filmDao: FilmDao) : TmdbDataSource{
        return TmdbDataSource(filmDao)
    }
    @Singleton
    @Provides
    fun provideFilmRepository(tmdbSource: TmdbSource,tmdbDataSource: TmdbDataSource) : FilmRepository {
        return FilmRepository(tmdbSource,tmdbDataSource)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : TmdbDatabase{
        return Room.databaseBuilder(context,TmdbDatabase::class.java,"Movie-Db").build()
    }
    @Singleton
    @Provides
    fun provideFilmDao(tmdbDatabase: TmdbDatabase) : FilmDao{
        return tmdbDatabase.filmDao()
    }
}