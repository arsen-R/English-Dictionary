package com.example.english_dictionary.di

import com.example.english_dictionary.data.repository.SettingsRepositoryImpl
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.repository.SettingsRepository
import com.example.english_dictionary.domain.repository.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWordRepository(wordRepositoryImpl: WordRepositoryImpl): WordRepository

    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}