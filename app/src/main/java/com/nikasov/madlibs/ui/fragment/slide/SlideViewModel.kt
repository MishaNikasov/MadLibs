package com.nikasov.madlibs.ui.fragment.slide

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.madlibs.data.room.repoository.DatabaseRepository

class SlideViewModel @ViewModelInject constructor(
    val databaseRepository: DatabaseRepository
):
    ViewModel()
{



}