package com.kky.remote.di

import com.kky.remote.BuildConfig
import com.kky.remote.service.NaverSearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class AuthInterceptor

@Qualifier
annotation class LoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NaverRetrofitModule {

    @Provides
    fun providesBaseUrl() = NAVER_SEARCH_API_BASE_URL

    @AuthInterceptor
    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_SECRET_KEY)
                .build()

            return@Interceptor chain.proceed(newRequest)
        }

        return interceptor
    }

    @LoggingInterceptor
    @Singleton
    @Provides
    fun providesLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @AuthInterceptor authInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): NaverSearchApiService {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverSearchApiService::class.java)
    }
}