package es.nlc.teammanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "cat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
