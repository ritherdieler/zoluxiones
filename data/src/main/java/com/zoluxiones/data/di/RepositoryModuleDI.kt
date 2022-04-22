package com.zoluxiones.data.di

import com.zoluxiones.data.database.dao.MovieDao
import com.zoluxiones.data.mapper.MoviesMapper
import com.zoluxiones.data.network.api.MoviesAPI
import com.zoluxiones.data.network.datasources.LocalDataSource
import com.zoluxiones.data.network.datasources.LocalDataSourceImpl
import com.zoluxiones.data.network.datasources.RemoteDataSource
import com.zoluxiones.data.network.datasources.RemoteDataSourceImpl
import com.zoluxiones.data.repository.RepositoryImpl
import com.zoluxiones.data.util.NetworkUtils
import com.zoluxiones.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
class RepositoryModuleDI {

    @Singleton
    @Provides
    fun providesNetworkUtils() = NetworkUtils()

    @Singleton
    @Provides
    fun providesMoviesMapper() = MoviesMapper()

    @Singleton
    @Provides
    fun providesMoviesAPI(retrofit: Retrofit) = retrofit.create(MoviesAPI::class.java)

    @Provides
    fun providesLocalDataSource(movieDao: MovieDao) = LocalDataSourceImpl(movieDao)

    @Provides
    fun providesRemoteDataSource(networkUtils: NetworkUtils, moviesAPI: MoviesAPI,) =
        RemoteDataSourceImpl(networkUtils, moviesAPI)

    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        moviesMapper: MoviesMapper
    ): Repository = RepositoryImpl(localDataSource, remoteDataSource,moviesMapper)

}