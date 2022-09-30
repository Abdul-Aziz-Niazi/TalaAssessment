package com.abdulaziz.tala.home.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoanResponseItem(
    @SerializedName("loan")
    var loan: Loan? = null,
    @SerializedName("locale")
    var locale: String? = null,
    @SerializedName("timestamp")
    var timestamp: Long? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("currency")
    var currency: String? = null
):Parcelable
