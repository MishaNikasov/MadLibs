package com.nikasov.madlibs.data.room.model.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.madlibs.common.Constants

@Entity(tableName = Constants.HISTORY_TABLE_NAME)
data class HistoryModel (
    var text : String,
    var date : Long
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}