package com.exampl3.testovoeavia.data.ticketSearch

data class TicketsOffer(
    val id: Int,
    val price: Price,
    val time_range: List<String>,
    val title: String
)