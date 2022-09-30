package com.abdulaziz.tala.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulaziz.tala.databinding.ListItemLocaleBinding
import com.abdulaziz.tala.home.data.LoanResponse
import com.abdulaziz.tala.home.data.LoanResponseItem

class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    var data = arrayListOf<LoanResponseItem>()
    lateinit var onItemClick: (LoanResponseItem) -> Unit

    inner class ViewHolder(private val binding: ListItemLocaleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun hold(position: Int) {
            val item = data[position]
            binding.root.setOnClickListener {
                onItemClick(item)
            }
            binding.bodyText.text = String.format("%s %s %s",item.username, item.locale,item.loan?.level.orEmpty())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemLocaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hold(position)
    }

    override fun getItemCount() = 8
}