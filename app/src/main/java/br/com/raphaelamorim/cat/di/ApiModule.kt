package br.com.raphaelamorim.cat.di

import android.content.Context
import br.com.raphaelamorim.cat.BuildConfig.*
import br.com.raphaelamorim.cat.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(supportInterceptor: Interceptor):OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder().apply {

            addInterceptor(interceptor)
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(supportInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesSupportInterceptor(@ApplicationContext appContext: Context): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder().run {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
                addHeader("Authorization","Client-ID 1ceddedc03a5d71")
                build()
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit {
        return  Retrofit.Builder().run {
            client(client)
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            build()
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}