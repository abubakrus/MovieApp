package com.example.movieapp.di

import com.example.movieapp.data.cloud.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNTBjMTU5NWQxMDg5MWMzYTI0MGQ0MGQ1NzFjMWFjYiIsInN1YiI6IjY0ZjFkMzBkNWYyYjhkMDExYjRkNGU5MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.0RRqtA53mLHoTEoruckVumFZccd8_gWl4gsaZ5mjZDM"


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer ${API_KEY}").build()
                        return@Interceptor chain.proceed(request = request)
                    }).build()
            ).build()
    }

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}