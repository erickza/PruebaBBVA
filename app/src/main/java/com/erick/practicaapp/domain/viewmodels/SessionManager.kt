package com.erick.practicaapp.domain.viewmodels

import android.content.Context

class SessionManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    companion object {
        const val IS_LOGGED_IN = "IsLoggedIn"
        const val USER_ID = "UserId"
        const val USER_NAME = "UserName"
        const val LAST_NAME = "LastName"
        const val GENDER = "Gender"
        const val AGE = "Age"
    }

    fun setUserData(name: String, lastName: String, gender: String, age: Int, id: String) {
        editor.apply {
            putBoolean(IS_LOGGED_IN, true)
            putString(USER_NAME, name)
            putString(LAST_NAME, lastName)
            putString(GENDER, gender)
            putInt(AGE, age)
            putString(USER_ID, id)
            apply()
        }
    }

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    fun getUserName(): String = sharedPreferences.getString(USER_NAME, "") ?: ""
    fun getLastName(): String = sharedPreferences.getString(LAST_NAME, "") ?: ""
    fun getGender(): String = sharedPreferences.getString(GENDER, "") ?: ""
    fun getAge(): Int = sharedPreferences.getInt(AGE, 0)
    fun getUserId(): String = sharedPreferences.getString(USER_ID, "") ?: ""

    fun clearSession() {
        editor.clear().apply()
    }
}