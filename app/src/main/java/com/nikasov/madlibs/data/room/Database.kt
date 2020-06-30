package com.nikasov.madlibs.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikasov.madlibs.common.Constants
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.model.history.HistoryDAO
import com.nikasov.madlibs.data.room.model.slide.SlideDAO
import com.nikasov.madlibs.data.room.model.slide.SlideModel

@Database(entities = [HistoryModel::class, SlideModel::class], version = Constants.DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getHistoryDAO() : HistoryDAO
    abstract fun getSlideDAO() : SlideDAO
}