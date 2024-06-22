package com.exampl3.testovoeavia.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.databinding.ItemTimeTicketBinding

class AdapterTimeTicket(private val list: List<String>): RecyclerView.Adapter<AdapterTimeTicket.Holder>() {
    class Holder(item: View): RecyclerView.ViewHolder(item)  {
        private val binding = ItemTimeTicketBinding.bind(item)
        fun bind(time: String){
            binding.textView7.text = time
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_time_ticket, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }
}