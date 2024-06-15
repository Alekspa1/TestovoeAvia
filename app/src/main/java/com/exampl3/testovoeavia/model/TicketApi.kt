package com.exampl3.testovoeavia.model

import com.exampl3.testovoeavia.data.OffersStart
import retrofit2.Response
import retrofit2.http.GET

interface TicketApi {
    @GET("v3/214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getTicketStart(): Response<OffersStart>
}