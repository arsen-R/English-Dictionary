package com.example.english_dictionary.di

import android.content.Context
import androidx.room.Room
import com.example.english_dictionary.data.database.DictionaryDatabase
import com.example.english_dictionary.data.database.dao.DictionaryDao
import com.example.english_dictionary.data.network.WordService
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.repository.WordRepository
import com.example.english_dictionary.domain.util.Constant
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
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()

            val requestBody = original.newBuilder()
                .header(Constant.APP_KEY_KEY, Constant.APP_KEY_VALUE)
                .header(Constant.APP_ID_KEY, Constant.APP_ID_VALUE)
                .method(original.method, original.body)
                .build()

            chain.proceed(requestBody)
        })
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    fun provideWordService(retrofit: Retrofit): WordService =
        retrofit.create(WordService::class.java)

    @Provides
    @Singleton
    fun provideDictionaryDatabase(@ApplicationContext context: Context): DictionaryDatabase =
        Room.databaseBuilder(
            context,
            DictionaryDatabase::class.java,
            Constant.DATABASE_NAME
        ).allowMainThreadQueries()
            .build()

    @Provides
    fun provideDictionaryDao(database: DictionaryDatabase): DictionaryDao = database.dictionaryDao()

    @Provides
    fun provideWordRepository(wordService: WordService, dao: DictionaryDao): WordRepository =
        WordRepositoryImpl(wordService = wordService, dao = dao)
}