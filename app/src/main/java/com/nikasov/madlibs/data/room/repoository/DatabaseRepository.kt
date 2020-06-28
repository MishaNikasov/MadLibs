package com.nikasov.madlibs.data.room.repoository

import androidx.lifecycle.LiveData
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.model.history.HistoryModelDAO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val historyDAO : HistoryModelDAO
) {

    suspend fun insertHistory(history : HistoryModel){
        historyDAO.insertHistory(history)
    }
    suspend fun deleteHistory(history : HistoryModel){
        historyDAO.deleteHistory(history)
    }

    fun getAllHistory() : LiveData<List<HistoryModel>> {
        return historyDAO.getAllHistory()
    }

    fun deleteAllHistory()  {
        historyDAO.deleteAllHistory()
    }

}