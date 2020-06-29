package com.nikasov.madlibs.data.room.model.slide

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SlideDAO {

    @Insert
    suspend fun insertSlide(slideModel: SlideModel)
    @Query("SELECT * FROM SLIDE_TABLE WHERE number=:number")
    suspend fun getSlideByNumber(number : Int) : SlideModel
    @Query("DELETE FROM SLIDE_TABLE")
    suspend fun delAllSlides()
    @Query("SELECT * FROM SLIDE_TABLE")
    suspend fun getAllSlides() : List<SlideModel>
}