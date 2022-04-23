package com.zoluxiones.di

import android.content.Context
import com.zoluxiones.data.database.dao.MovieDao
import com.zoluxiones.data.mapper.MoviesMapper
import com.zoluxiones.data.network.api.MoviesAPI
import com.zoluxiones.data.network.datasources.LocalDataSource
import com.zoluxiones.data.network.datasources.LocalDataSourceImpl
import com.zoluxiones.data.network.datasources.RemoteDataSource
import com.zoluxiones.data.network.datasources.RemoteDataSourceImpl
import com.zoluxiones.data.repository.RepositoryImpl
import com.zoluxiones.data.util.ConnectionUtils
import com.zoluxiones.data.util.ConnectionUtilsImpl
import com.zoluxiones.data.util.NetworkUtils
import com.zoluxiones.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesConnectionUtils(@ApplicationContext context: Context): ConnectionUtils =
        ConnectionUtilsImpl(context)

    @Singleton
    @Provides
    fun providesMoviesMapper() = MoviesMapper()

    @Singleton
    @Provides
    fun providesMoviesAPI(retrofit: Retrofit) = retrofit.create(MoviesAPI::class.java)

    @Singleton
    @Provides
    fun providesLocalDataSource(movieDao: MovieDao): LocalDataSource = LocalDataSourceImpl(movieDao)

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        networkUtils: NetworkUtils,
        moviesAPI: MoviesAPI
    ): RemoteDataSource =
        RemoteDataSourceImpl(networkUtils, moviesAPI)

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        moviesMapper: MoviesMapper,
        connectionUtils: ConnectionUtils
    ): Repository = RepositoryImpl(localDataSource, remoteDataSource, moviesMapper, connectionUtils)

}