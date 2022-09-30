package com.abdulaziz.tala.managers

import android.content.Context
import androidx.core.content.edit
import com.abdulaziz.tala.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface SharedPreferencesManager {
    fun getLocale(): String
    fun setLocale(locale: String)
}

class DefaultSharedPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val stringsResourceManager: StringsResourceManager,
) : SharedPreferencesManager {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            stringsResourceManager.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private const val KEY_LOCALE = "KEY_LOCALE"
    }

    override fun getLocale(): String {
        return sharedPreferences.getString(KEY_LOCALE, "PH").orEmpty()
    }

    override fun setLocale(locale: String) {
        sharedPreferences.edit {
            putString(KEY_LOCALE, locale)
        }
    }

}
