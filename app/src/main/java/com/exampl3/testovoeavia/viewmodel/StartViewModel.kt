package com.exampl3.testovoeavia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampl3.testovoeavia.data.ticketEnd.TicketEnd
import com.exampl3.testovoeavia.data.ticketSearch.TicketsSearch
import com.exampl3.testovoeavia.data.ticketStart.OffersStart
import com.exampl3.testovoeavia.model.retrofit.TicketApi
import com.exampl3.testovoeavia.model.sharedPreference.SharedPreferenceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val retrofit: TicketApi,
    private val pref: SharedPreferenceImpl
): ViewModel() {

    init {
        getStartTicket()
    }

    private val _startTicket = MutableLiveData<OffersStart>()
    val startTicket: LiveData<OffersStart> = _startTicket

    private val _searchTicket = MutableLiveData<TicketsSearch>()
    val searchTicket: LiveData<TicketsSearch> = _searchTicket

    private val _endTicket = MutableLiveData<TicketEnd>()
    val endTicket: LiveData<TicketEnd> = _endTicket

    private val _bottomSheet = MutableLiveData<Boolean>()
    val bottomSheet: LiveData<Boolean> = _bottomSheet



    private fun getStartTicket() {
        viewModelScope.launch {
            try {
                val res = retrofit.getTicketStart()
                if (res.isSuccessful){
                    _startTicket.postValue(res.body())
                }

            } catch (e: Exception) {
                Log.d("MyLog", e.message.toString())
            }
        }
    }
    fun getSearchTicket(){
        viewModelScope.launch {
            try {
                val res = retrofit.getTicketSearch()
                if (res.isSuccessful){
                    _searchTicket.postValue(res.body())
                }

            } catch (e: Exception) {
                Log.d("MyLog", e.message.toString())
            }
        }
    }
    fun getEndTicket() {
        viewModelScope.launch {
            try {
                val res = retrofit.getTicketEnd()
                if (res.isSuccessful){
                    _endTicket.postValue(res.body())
                }

            } catch (e: Exception) {
                Log.d("MyLog", e.message.toString())
            }
        }
    }

    fun openCloseSheet(flag: Boolean){
        _bottomSheet.value = flag
    }

    fun getSP() = pref.getSP()
    fun saveSP(value: String) = pref.saveSP(value)
}