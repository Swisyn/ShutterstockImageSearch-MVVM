package com.cuneytayyildiz.shutterstockassignment.di.modules

import com.cuneytayyildiz.shutterstockassignment.BuildConfig
import com.cuneytayyildiz.shutterstockassignment.ShutterstockApp
import com.cuneytayyildiz.shutterstockassignment.data.api.ShutterstockService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ShutterstockService {
        return retrofit.create(ShutterstockService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: ShutterstockApp): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesOkHttp(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()

        client.interceptors().add(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )
        )
        client.cache(cache)

        client.followRedirects(true)
        client.followSslRedirects(true)
        client.retryOnConnectionFailure(true)

        client.connectTimeout(35, TimeUnit.SECONDS)
        client.readTimeout(35, TimeUnit.SECONDS)
        client.writeTimeout(35, TimeUnit.SECONDS)

        client.authenticator { _, response ->
            response.request().newBuilder().header(
                "Authorization",
                Credentials.basic(BuildConfig.API_CONSUMER_KEY, BuildConfig.API_CONSUMER_SECRET)
            ).build()
        }

        return client.build()
    }

}