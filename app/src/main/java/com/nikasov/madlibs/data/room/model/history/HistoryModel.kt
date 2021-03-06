package com.nikasov.madlibs.data.room.model.history

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.madlibs.common.Constants
import java.util.*

@Entity(tableName = Constants.HISTORY_TABLE_NAME)
data class HistoryModel (
    var firstStoryText : String,
    var secondStoryText : String,
    var date : Date
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}