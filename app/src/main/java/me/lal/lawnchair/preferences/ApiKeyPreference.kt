package me.lal.lawnchair.preferences

import android.content.Context
import android.preference.EditTextPreference
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.AttributeSet

import me.lal.lawnchair.R

class ApiKeyPreference : EditTextPreference {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onAttachedToHierarchy(preferenceManager: PreferenceManager?) {
        super.onAttachedToHierarchy(preferenceManager)
        updateSummary()
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        super.onDialogClosed(positiveResult)
        if (positiveResult) {
            updateSummary()
        }
    }

    private fun updateSummary() {
        val apiKey = sharedPreferences.getString(PreferenceFlags.KEY_WEATHER_API_KEY, PreferenceFlags.PREF_DEFAULT_WEATHER_API_KEY)
        if (!TextUtils.isEmpty(apiKey))
            setSummary(apiKey.replace("[A-Za-z0-9]".toRegex(), "*"))
        else
            setSummary(R.string.weather_api_key_not_set)
    }

}
