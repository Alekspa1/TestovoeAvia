package com.exampl3.testovoeavia.model.retrofit

import com.exampl3.testovoeavia.data.ticketEnd.TicketEnd
import com.exampl3.testovoeavia.data.ticketSearch.TicketsSearch
import com.exampl3.testovoeavia.data.ticketStart.OffersStart
import retrofit2.Response
import retrofit2.http.GET

interface TicketApi {
    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getTicketStart(): Response<OffersStart>

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketSearch(): Response<TicketsSearch>

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTicketEnd(): Response<TicketEnd>
}