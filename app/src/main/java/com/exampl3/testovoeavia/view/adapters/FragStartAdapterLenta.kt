package com.exampl3.testovoeavia.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.data.ticketStart.Offer
import com.exampl3.testovoeavia.data.ticketStart.OffersStart
import com.exampl3.testovoeavia.databinding.ItemFragStartLentaBinding

class FragStartAdapterLenta(private val lenta: OffersStart): RecyclerView.Adapter<FragStartAdapterLenta.Holder>() {
    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemFragStartLentaBinding.bind(item)
        fun bind(item: Offer) = with(binding){
            val number = item.price.value.toString()
            val formattedNumber = "${number.substring(0, number.length - 3)} ${number.substring(number.length - 3)}"
            val price = "От $formattedNumber ₽"
            itemTvTitle.text = item.title
            itemTvCity.text = item.town
            itemTvPrice.text = price
            when(item.id){
                1-> imTitle.setImageResource(R.drawable.img_1)
                2-> imTitle.setImageResource(R.drawable.img_2)
                3-> imTitle.setImageResource(R.drawable.img_3)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_frag_start_lenta, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return lenta.offers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(lenta.offers[position])
    }
}