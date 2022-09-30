package com.abdulaziz.tala.home.data


import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class LoanResponse : ArrayList<LoanResponseItem>(){

    companion object{
        val EMPTY: LoanResponse= LoanResponse()
    }
}