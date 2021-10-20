package com.kbank.search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kbank.search.data.local.media.MediaDao
import com.kbank.search.model.Media
import com.kbank.search.utils.Converters


@Database(entities = [Media::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}
