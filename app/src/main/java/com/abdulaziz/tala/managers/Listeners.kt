package com.abdulaziz.tala.managers

import androidx.lifecycle.MutableLiveData

object Listeners {
    val errorLiveData = MutableLiveData<Event<String>>()
}