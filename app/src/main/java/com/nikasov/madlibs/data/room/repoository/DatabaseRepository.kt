package com.nikasov.madlibs.data.room.repoository

import androidx.lifecycle.LiveData
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.model.history.HistoryDAO
import com.nikasov.madlibs.data.room.model.slide.SlideDAO
import com.nikasov.madlibs.data.room.model.slide.SlideModel
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val historyDAO : HistoryDAO,
    private val slideDAO : SlideDAO
) {

    //history
    suspend fun insertHistory(history : HistoryModel){
        historyDAO.insertHistory(history)
    }
    suspend fun deleteHistory(history : HistoryModel){
        historyDAO.deleteHistory(history)
    }
    suspend fun getHistoryById(id : Int) : HistoryModel {
        return historyDAO.getHistoryById(id)
    }

    fun getAllHistory() : LiveData<List<HistoryModel>> {
        return historyDAO.getAllHistory()
    }

    fun deleteAllHistory()  {
        historyDAO.deleteAllHistory()
    }


    //slide
    suspend fun insertSlide(slideModel: SlideModel) {
        slideDAO.insertSlide(slideModel)
    }
    suspend fun getSlideByNumber(number : Int) : SlideModel{
        return slideDAO.getSlideByNumber(number)
    }
    suspend fun delAllSlides() {
        slideDAO.delAllSlides()
    }
    suspend fun getAllSlides() : List<SlideModel> {
        return slideDAO.getAllSlides()
    }

}