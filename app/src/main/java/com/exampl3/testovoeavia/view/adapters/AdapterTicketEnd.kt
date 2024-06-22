package com.exampl3.testovoeavia.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exampl3.testovoeavia.R
import com.exampl3.testovoeavia.data.ticketEnd.Ticket
import com.exampl3.testovoeavia.data.ticketEnd.TicketEnd
import com.exampl3.testovoeavia.databinding.ItemTicketEndBinding
import java.text.SimpleDateFormat
import java.util.Locale

class AdapterTicketEnd(private val list: TicketEnd): RecyclerView.Adapter<AdapterTicketEnd.Holder>() {
    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemTicketEndBinding.bind(item)


        fun bind(item: Ticket){
            with(binding){
                var way: String
                val dateTimeOtp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.getDefault()).parse(item.departure.date)
                val dateTimePri = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.getDefault()).parse(item.arrival.date)
                val timeOtp = dateTimeOtp?.let {
                    SimpleDateFormat("HH:mm",
                        Locale.getDefault()).format(it)
                }
                val timePri = dateTimePri?.let {
                    SimpleDateFormat("HH:mm",
                        Locale.getDefault()).format(it)
                }
                val number = item.price.value.toString()
                val formattedNumber = "${number.substring(0, number.length - 3)} ${number.substring(number.length - 3)}"
                val price = "$formattedNumber ₽"
                val time = "$timeOtp - $timePri"
                val aero = "${item.departure.airport}        ${item.arrival.airport}"

                val hourOtp = dateTimeOtp!!.hours
                val hourPri = dateTimePri!!.hours

                way = if (hourPri> hourOtp){
                    "${hourPri-hourOtp}ч в пути"
                } else "${24 - hourOtp}ч в пути"

                if (!item.has_transfer) way+= " /без пересадок"


                tvPriceTicketEnd.text = price
                tvDateTicketEnd.text = time
                tvAeroTicketEnd.text = aero
                tvWayTicketEnd.text = way

                if (item.badge != null){
                    cardBage.visibility = View.VISIBLE
                    tvBage.text = item.badge
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_end, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
       return list.tickets.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list.tickets[position])
    }
}