package com.nikasov.madlibs.data.room.model.slide

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikasov.madlibs.common.Constants

@Entity(tableName = Constants.SLIDE_TABLE_NAME)
data class SlideModel (
    var text : String,
    var number : Int
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}