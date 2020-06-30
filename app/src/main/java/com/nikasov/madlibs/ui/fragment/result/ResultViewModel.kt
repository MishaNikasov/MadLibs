package com.nikasov.madlibs.ui.fragment.result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.madlibs.R
import com.nikasov.madlibs.common.Constants
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.repoository.DatabaseRepository
import com.nikasov.madlibs.common.ResourceProvider
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class ResultViewModel @ViewModelInject constructor(
    private val resourceProvider: ResourceProvider,
    private val databaseRepository: DatabaseRepository
):
    ViewModel()
{

    var firstStory : MutableLiveData<String> = MutableLiveData()
    var secondStory : MutableLiveData<String> = MutableLiveData()

    private val standardAnswer = resourceProvider.getStringArray(R.array.standard_answer)

    fun setStories(type : Int) {
        if (type == Constants.TYPE_SINGLE_PLAYER) {
            viewModelScope.launch {
                val list = databaseRepository.getAllSlides()

                val firstStoryTxt = StringBuilder().append(
                    standardAnswer[0], " ",
                    list[1].text, " ",
                    standardAnswer[2], " ",
                    list[3].text, " ",
                    standardAnswer[4], ", тут пришел ",
                    list[5].text, " и сказал - ",
                    standardAnswer[6], ", ему ответили - ",
                    list[7].text, ", и все закончилось, ",
                    standardAnswer[8]
                )

                val secondStoryTxt = StringBuilder().append(
                    list[0].text, " ",
                    standardAnswer[1], " ",
                    list[2].text, " ",
                    standardAnswer[3], " ",
                    list[4].text, ", тут пришел ",
                    standardAnswer[5], " и сказал - ",
                    list[6].text, ", мы ему ответили - ",
                    standardAnswer[7], ", и все закончилось, ",
                    list[8].text
                )

                firstStory.postValue(firstStoryTxt.toString())
                secondStory.postValue(secondStoryTxt.toString())
            }
        } else {

        }
    }

    fun setHistoryById(id : Int) {
        viewModelScope.launch {
            val history = databaseRepository.getHistoryById(id)
            firstStory.postValue(history.firstStoryText)
            secondStory.postValue(history.secondStoryText)
        }
    }

    fun addToHistoryList(firstStoryTxt : String, secondStoryTxt : String) {
        viewModelScope.launch {
            databaseRepository.insertHistory(HistoryModel(firstStoryTxt, secondStoryTxt, Calendar.getInstance().time))
        }
    }

    fun deleteAllSlides() {
        viewModelScope.launch {
            databaseRepository.delAllSlides()
        }
    }
}