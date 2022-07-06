package com.googleio.publicapi.app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.googleio.publicapi.R
import com.googleio.publicapi.app.models.EntryPresenter
import com.googleio.publicapi.databinding.EntrySingleDetailBinding


class EntryAdapter(private val entryList: List<EntryPresenter>) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    class EntryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = EntrySingleDetailBinding.bind(itemView)

        fun setup(data : EntryPresenter) {
            binding.entryApiName.text = data.API
            binding.entryApiDescription.text = data.Description
            binding.entryApiCategory.text = data.Category
            binding.entryApiHttps.text = data.HTTPS.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.entry_single_detail, parent, false)
        return EntryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entryItem = entryList[position]
        holder.setup(entryItem)
    }

    override fun getItemCount(): Int {
        return entryList.size
    }
}