package com.abdulaziz.tala.managers

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.abdulaziz.network.ApiResult
import com.abdulaziz.tala.home.MainActivity
import com.abdulaziz.tala.home.data.LoanLevels
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.abdulaziz.tala.home.data.LoanStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible(show: Boolean, isGone: Boolean = true) {
    if (show) {
        show()
    } else {
        if (isGone) hide()
        else invisible()
    }
}

fun <T> unzip(block: suspend () -> ApiResult<T>, onSuccess: (T) -> Unit, onError: (error: String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        when (val response = block()) {
            is ApiResult.Success -> onSuccess(response.data)
            is ApiResult.Failure -> onError(response.error.message)
        }
    }
}


fun Fragment.navigate(navDirections: NavDirections) {
    (requireActivity() as MainActivity).getNavController().navigate(navDirections)
}


fun String.showError() {
    Listeners.errorLiveData.postValue(Event(this))
}


fun LoanResponseItem.getStatus(): LoanStatus {
    return when (this.loan?.status?.lowercase()) {
        "silver" -> LoanStatus.Approved
        "paid" -> LoanStatus.Paid
        "due" -> LoanStatus.Due
        else -> LoanStatus.Approved
    }
}

fun LoanResponseItem.getLoanLevel(): LoanLevels {
    return when (this.loan?.level?.lowercase()) {
        "silver" -> LoanLevels.Silver
        "gold" -> LoanLevels.Gold
        "bronze" -> LoanLevels.Bronze
        else -> LoanLevels.DEFAULT
    }
}
