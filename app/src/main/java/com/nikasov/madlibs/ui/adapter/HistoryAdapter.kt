package com.nikasov.madlibs.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.madlibs.R
import com.nikasov.madlibs.data.room.model.history.HistoryModel
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryModel>() {

        override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_history,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<HistoryModel>) {
        differ.submitList(list)
    }

    class HistoryViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: HistoryModel) = with(itemView) {
            itemView.animation = AnimationUtils.loadAnimation(
                itemView.context,
                R.anim.btn_anim_enter
            )

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            //todo: add date to item
            itemView.date.text = item.date.toString()
            itemView.text.text = item.firstStoryText
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: HistoryModel)
    }
}

