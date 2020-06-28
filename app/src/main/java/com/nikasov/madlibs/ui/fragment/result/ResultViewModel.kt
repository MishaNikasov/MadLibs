package com.nikasov.madlibs.ui.fragment.result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.repoository.DatabaseRepository
import kotlinx.coroutines.launch

class ResultViewModel @ViewModelInject constructor(
    private val databaseRepository: DatabaseRepository
):
    ViewModel()
{

    fun addToHistoryList(historyModel: HistoryModel) {
        viewModelScope.launch {
            databaseRepository.insertHistory(historyModel)
        }
    }

}