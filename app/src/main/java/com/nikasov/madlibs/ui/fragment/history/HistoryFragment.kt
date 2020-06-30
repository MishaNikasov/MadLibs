package com.nikasov.madlibs.ui.fragment.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.nikasov.madlibs.R
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import com.nikasov.madlibs.ui.adapter.HistoryAdapter
import com.nikasov.madlibs.ui.utils.RecyclerViewSwipe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_history.*
import timber.log.Timber
import java.time.Duration

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history), HistoryAdapter.Interaction{

    private val historyViewModel : HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpList()
    }

    private fun setUpList() {

        val historyAdapter = HistoryAdapter(this)

        val swipeItem = ItemTouchHelper(RecyclerViewSwipe(historyAdapter))
        swipeItem.attachToRecyclerView(historyRecycler)

        historyRecycler.apply {
            adapter = historyAdapter
        }

        historyViewModel.getAllHistory().observe(viewLifecycleOwner, Observer {
            historyAdapter.submitList(it)
        })
    }

    private fun openHistory(id : Int?) {
        val action = HistoryFragmentDirections.actionHistoryFragmentToResultFragment(-1, true, id!!)
        findNavController().navigate(action)
    }

    override fun onItemSelected(position: Int, item: HistoryModel) {
        openHistory(item.id)
    }

    override fun onItemSwiped(item: HistoryModel) {
        historyViewModel.deleteFromHistoryList(item)
        showSnackbar(item)
    }

    private fun showSnackbar(item : HistoryModel) {
        val snackbar = Snackbar.make(root, R.string.delete, Snackbar.LENGTH_SHORT)
        snackbar.setAction(R.string.cancel) {
            historyViewModel.addToHistory(item)
        }
        snackbar.show()
    }
}