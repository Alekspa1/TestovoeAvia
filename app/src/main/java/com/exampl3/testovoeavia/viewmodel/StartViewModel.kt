package com.exampl3.testovoeavia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampl3.testovoeavia.data.OffersStart
import com.exampl3.testovoeavia.model.TicketApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val retrofit: TicketApi
): ViewModel() {

    init {
        getStartTicket()
    }

    private val _startTicket = MutableLiveData<OffersStart>()
    val startTicket: LiveData<OffersStart> = _startTicket

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

    fun openCloseSheet(flag: Boolean){
        _bottomSheet.value = flag
    }
}