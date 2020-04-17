package com.owescm.services.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.owescm.utils.Constants

class SessionManager(context: Context) {

    var prefs: SharedPreferences = customPrefs(context, Constants.APP_NAME)

    fun defaultPrefs(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPrefs(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun customPrefs(): SharedPreferences = prefs

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * @return true if logged in
     */
//    val isLoggedIn: Boolean
//        get() = prefs[Constants.IS_LOGGED_IN] ?: false

    /**
     * Used to save Bearer token in prefs
     */
//    fun setToken(token: String) {
//        prefs[Constants.USER_TOKEN] = token
//    }

    /**
     * @return Bearer token
     */
    fun getToken(): String? = ""
    //prefs[Constants.USER_TOKEN]


//
//    fun saveProfileInPrefs(profileResponse: GetProfileResponse) {
//        prefs[Constants.USER_NAME] = profileResponse.data?.name?: ""
//        prefs[Constants.ID] = profileResponse.data?.id?: ""
//        prefs[Constants.MOBILE_NUMBER] = profileResponse.data?.phoneNumber?: ""
//        prefs[Constants.EMAIL_ID] = profileResponse.data?.email?: ""
//        prefs[Constants.WORK_EMAIL_ID] = profileResponse.data?.workEmail?: ""
//        prefs[Constants.PROFILE_IMG] = profileResponse.data?.profileImageUrl?: ""
//        prefs[Constants.ACCOUNT_SELECTED] = profileResponse.data?.accountSelected?: ""
//        prefs[Constants.EMAIL_VERIFIED] = profileResponse.data?.emailVerified?: false
//        prefs[Constants.WORK_EMAIL_VERIFIED] = profileResponse.data?.workEmailVerified?: false
////    }


//    fun saveFavDriversInPrefs(favDriverDetails: String) {
//        prefs[Constants.DRIVERDETAILS]  = favDriverDetails
//    }


    /**
     * Used to clear prefs on logout
     */
    fun logoutUser() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}


