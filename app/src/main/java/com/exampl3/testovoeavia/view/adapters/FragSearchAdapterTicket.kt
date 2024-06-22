package com.exampl3.testovoeavia.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.data.ticketSearch.TicketsOffer
import com.exampl3.testovoeavia.data.ticketSearch.TicketsSearch
import com.exampl3.testovoeavia.databinding.ItemTicketBinding

class FragSearchAdapterTicket(private val list: TicketsSearch): RecyclerView.Adapter<FragSearchAdapterTicket.Holder>() {
    class Holder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemTicketBinding.bind(item)
        private val context = item.context

        fun bind(item: TicketsOffer){
        with(binding){
            val number = item.price.value.toString()
            val formattedNumber = "${number.substring(0, number.length - 3)} ${number.substring(number.length - 3)}"
            val price = "$formattedNumber ₽ >"
            tvTitleAir.text = item.title
            tvPriceAir.text = price
            val adapter = AdapterTimeTicket(item.time_range)
            rcViewAirTicket.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)
            rcViewAirTicket.adapter = adapter
            when(item.id){
                1-> imTitleAir.setImageResource(R.drawable.img_18)
                10-> imTitleAir.setImageResource(R.drawable.img_19)
                30-> imTitleAir.setImageResource(R.drawable.img_20)
            }
        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.tickets_offers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list.tickets_offers[position])
    }
}