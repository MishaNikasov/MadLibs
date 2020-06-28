package com.nikasov.madlibs.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikasov.madlibs.common.Constants
import com.nikasov.madlibs.data.room.model.history.HistoryModel

@Database(entities = [HistoryModel::class], version = Constants.DATABASE_VERSION, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun getHistoryDAO()
}