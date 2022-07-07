package com.techarion.otploginuser


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

      @Singleton
      @Provides
      fun providesRetrofit():Retrofit.Builder{
      return Retrofit.Builder().baseUrl(Cons.BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
       }


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Singleton
    @Provides
    fun providesAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): UserAPi {
        return retrofitBuilder.client(okHttpClient).build().create(UserAPi::class.java)
    }





}