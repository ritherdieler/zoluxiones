package com.zoluxiones.di

import com.zoluxiones.domain.repository.Repository
import com.zoluxiones.domain.usecases.base.GetMoviesByPageUseCase
import com.zoluxiones.domain.usecases.base.SaveMoviewListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sergio Carrillo Diestra on 22/04/2022.
 * scarrillo.peruapps@gmail.com
 * Peru Apps
 * Huacho, Peru.
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModuleDI {
    @Singleton
    @Provides
    fun provideGetMoviesByPageUseCase(repository: Repository) = GetMoviesByPageUseCase(repository)
    @Singleton
    @Provides
    fun providesSaveMoviesUseCase(repository: Repository) = SaveMoviewListUseCase(repository)

}