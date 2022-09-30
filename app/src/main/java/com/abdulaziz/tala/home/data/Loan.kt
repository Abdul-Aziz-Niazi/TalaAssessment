package com.abdulaziz.tala.home.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Loan(
    @SerializedName("approved")
    var approved: Int? = null,
    @SerializedName("due")
    var due: Int? = null,
    @SerializedName("dueDate")
    var dueDate: Long? = null,
    @SerializedName("level")
    var level: String? = null,
    @SerializedName("status")
    var status: String? = null,
) : Parcelable


sealed class LoanLevels {
    object DEFAULT : LoanLevels()
    object Silver : LoanLevels()
    object Gold : LoanLevels()
    object Bronze : LoanLevels()
}

sealed class LoanStatus {
    object Approved : LoanStatus()
    object Paid : LoanStatus()
    object Due : LoanStatus()
    object DEFAULT : LoanStatus()
}