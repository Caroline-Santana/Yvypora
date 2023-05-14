package com.example.yvypora.services.datastore

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenStore(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("userToken")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token")
    }


    val getToken: Flow<String> = context.dataStoree.data.map { preferences -> preferences[USER_TOKEN_KEY] ?: "" }



    //save email into datastore
    suspend fun saveToken(token: String) {
        context.dataStoree.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
}