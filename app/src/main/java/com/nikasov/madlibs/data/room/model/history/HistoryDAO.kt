package com.nikasov.madlibs.data.room.model.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {

    @Insert
    suspend fun insertHistory(history : HistoryModel)
    @Delete
    suspend fun deleteHistory(history : HistoryModel)
    @Query("SELECT * FROM HISTORY_TABLE WHERE id = :id")
    suspend fun getHistoryById(id : Int) : HistoryModel
    @Query("SELECT * FROM HISTORY_TABLE")
    fun getAllHistory() : LiveData<List<HistoryModel>>
    @Query("DELETE FROM HISTORY_TABLE")
    fun deleteAllHistory()
}