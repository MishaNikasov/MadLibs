package com.nikasov.madlibs.ui.fragment.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikasov.madlibs.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val historyViewModel : HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpList()

    }

    private fun setUpList() {



        historyViewModel.getAllHistory().observe(viewLifecycleOwner, Observer {



        })

    }

}