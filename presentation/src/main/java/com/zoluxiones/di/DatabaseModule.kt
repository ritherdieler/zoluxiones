package com.zoluxiones.di

import android.content.Context
import com.zoluxiones.data.database.config.MyRoomDatabase
import com.zoluxiones.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDataBase(@ApplicationContext context: Context): MyRoomDatabase {
        return MyRoomDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providesMovieDao(myRoomDatabase: MyRoomDatabase): MovieDao {
        return myRoomDatabase.moviewDao()
    }

}