package com.abdulaziz.tala.home.data


import com.google.gson.annotations.SerializedName

data class LocaleData(
    @SerializedName("ke")
    var ke: LocaleItem? = null,
    @SerializedName("mx")
    var mx: LocaleItem? = null,
    @SerializedName("ph")
    var ph: LocaleItem? = null
) {
    companion object {
        val EMPTY = LocaleData(
            ke = LocaleItem.EMPTY,
            mx = LocaleItem.EMPTY,
            ph = LocaleItem.EMPTY
        )
    }
}