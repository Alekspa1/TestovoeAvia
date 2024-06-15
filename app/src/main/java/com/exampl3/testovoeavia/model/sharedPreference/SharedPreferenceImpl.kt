package com.exampl3.testovoeavia.model.sharedPreference

import android.content.SharedPreferences
import com.exampl3.testovoeavia.Const.KEY_SHARED_PREFERENCE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceImpl @Inject constructor(private val pref: SharedPreferences) {

    private val edit: SharedPreferences.Editor = pref.edit()

     fun getSP(): String? {
        return pref.getString(KEY_SHARED_PREFERENCE, "")
    }

     fun saveSP(text: String) {
        edit.putString(KEY_SHARED_PREFERENCE, text)
        edit.apply()
    }

}