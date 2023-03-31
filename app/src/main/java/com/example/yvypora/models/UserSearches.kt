package com.example.yvypora.models

import android.content.Context


class UserSearches(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    var searchHistory: Set<String>
        get() = sharedPreferences.getStringSet("search_history", emptySet()) ?: emptySet()
        set(value) = sharedPreferences.edit().putStringSet("search_history", value).apply()

}