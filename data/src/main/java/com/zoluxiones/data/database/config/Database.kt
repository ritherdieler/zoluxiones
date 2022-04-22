package com.zoluxiones.data.database.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zoluxiones.data.database.dao.MovieDao
import com.zoluxiones.data.database.model.Movie


@Database(
    entities = [
        Movie::class
    ],
    version = 1, exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun moviewDao(): MovieDao


    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null


        fun getDatabase(context: Context): MyRoomDatabase {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MyRoomDatabase::class.java,
                "my_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }

}
