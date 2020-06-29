package com.nikasov.madlibs.ui.fragment.slide

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.madlibs.R
import com.nikasov.madlibs.common.Constants
import com.nikasov.madlibs.data.room.model.slide.SlideModel
import com.nikasov.madlibs.data.room.repoository.DatabaseRepository
import com.nikasov.madlibs.ui.utils.Resource
import com.nikasov.madlibs.ui.utils.ResourceProvider
import kotlinx.coroutines.launch

class SlideViewModel @ViewModelInject constructor(
    private val databaseRepository: DatabaseRepository,
    private val resourceProvider: ResourceProvider
):
    ViewModel()
{

    private lateinit var questionsList : Array<String>
    private lateinit var descriptionList : Array<String>

    private var currentNumberOfSlide = 0

    val currentQuestion : MutableLiveData<Resource<String>> = MutableLiveData()
    val currentDescription : MutableLiveData<String> = MutableLiveData()

    fun setListByType(typeOfGame: Int) {
        when (typeOfGame){
            Constants.TYPE_SINGLE_PLAYER -> {
                questionsList = resourceProvider.getStringArray(R.array.standard_questions)
                descriptionList = resourceProvider.getStringArray(R.array.standard_questions_description)
            }
            Constants.TYPE_TWO_PLAYERS -> {
                questionsList = resourceProvider.getStringArray(R.array.standard_questions)
                descriptionList = resourceProvider.getStringArray(R.array.standard_questions_description)
            }
        }
    }

    fun setQuestion() {
        if (currentNumberOfSlide != questionsList.size) {
            currentQuestion.postValue(Resource.InProgress(questionsList[currentNumberOfSlide]))
            currentDescription.postValue(descriptionList[currentNumberOfSlide])
        } else
            currentQuestion.postValue(Resource.Success())
    }

    fun saveAndGoNext (addText : String) {
        viewModelScope.launch {
            val slide = SlideModel(
                text = addText,
                number = currentNumberOfSlide
            )
            databaseRepository.insertSlide(slide)
            currentNumberOfSlide++
            setQuestion()
        }
    }

}