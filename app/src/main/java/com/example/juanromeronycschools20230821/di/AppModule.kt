package com.example.juanromeronycschools20230821.di

import com.example.juanromeronycschools20230821.repo.SchoolRepository
import com.example.juanromeronycschools20230821.repo.SchoolsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesTickersRepository(): SchoolRepository {
        return SchoolsRepoImpl()
    }
}