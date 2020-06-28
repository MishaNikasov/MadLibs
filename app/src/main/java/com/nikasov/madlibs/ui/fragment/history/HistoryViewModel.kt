package com.nikasov.madlibs.ui.fragment.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.data.room.repoository.DatabaseRepository
import kotlinx.coroutines.launch

class HistoryViewModel @ViewModelInject constructor(
    private val databaseRepository: DatabaseRepository
):
    ViewModel()
{

    fun getAllHistory() : LiveData<List<HistoryModel>> = databaseRepository.getAllHistory()

    fun deleteFromHistoryList(historyModel: HistoryModel) {
        viewModelScope.launch {
            databaseRepository.deleteHistory(historyModel)
        }
    }

}