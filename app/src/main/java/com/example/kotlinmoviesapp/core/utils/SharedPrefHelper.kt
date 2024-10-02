package com.example.kotlinmoviesapp.core.utils

import android.content.Context

object SharedPrefHelper {
    private const val PREF_NAME = "app_prefs"
    private const val KEY_FIRST_TIME = "first_time"
    private const val KEY_SYNC_OPTION = "sync_option"
    private const val KEY_LAST_SYNC = "last_sync"

    fun setFirstTime(context: Context, isFirstTime: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply()
    }

    fun isFirstTime(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_FIRST_TIME, true)
    }

    fun setSyncOption(context: Context, syncOption: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_SYNC_OPTION, syncOption).apply()
    }

    fun getSyncOption(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_SYNC_OPTION, -1)
    }

    fun setLastSync(context: Context, lastSyncTime: Long) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putLong(KEY_LAST_SYNC, lastSyncTime).apply()
    }

    fun getLastSync(context: Context): Long {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(KEY_LAST_SYNC, 0L)
    }
}
