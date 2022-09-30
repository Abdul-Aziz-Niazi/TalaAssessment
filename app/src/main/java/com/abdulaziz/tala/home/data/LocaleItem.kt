package com.abdulaziz.tala.home.data


import com.google.gson.annotations.SerializedName

data class LocaleItem(
    @SerializedName("currency")
    var currency: String? = null,
    @SerializedName("loanLimit")
    var loanLimit: Int? = null,
    @SerializedName("timezone")
    var timezone: Int? = null
) {
    companion object{
        val EMPTY = LocaleItem(
            currency = "",
            loanLimit = 0,
            timezone = 0
        )
    }
}