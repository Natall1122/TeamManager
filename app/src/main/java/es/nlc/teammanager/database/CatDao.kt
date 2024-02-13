package es.nlc.teammanager.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: MutableList<CatEntity>)

    @Query("DELETE FROM cats")
    fun deleteAll()

    @Query("SELECT * FROM cats")
    fun getAll(): List<CatEntity>
}
