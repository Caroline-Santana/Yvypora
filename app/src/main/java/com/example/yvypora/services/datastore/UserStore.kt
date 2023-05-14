package com.example.yvypora.services.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.yvypora.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserStore(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("userDetails")
        val USER_DETAILS_TOKEN = stringPreferencesKey("user_details")
    }

    val getDetails: Flow<String?> = context.dataStoree.data.map { preferences -> preferences[USER_DETAILS_TOKEN] ?: "" }

    //save email into datastore
    suspend fun saveDetails(details: String) {
        context.dataStoree.edit { preferences ->
            preferences[USER_DETAILS_TOKEN] = details
        }
    }
}
