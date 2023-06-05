package com.example.yvypora.services.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DeliverymanStore(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("deliverymanDetails")
        val DELIVERYMAN_DETAILS_TOKEN = stringPreferencesKey("deliveryman_details")
    }

    val getDeliverymanDetails: Flow<String?> = context.dataStoree.data.map { preferences -> preferences[DELIVERYMAN_DETAILS_TOKEN] ?: "" }

    //save email into datastore
    suspend fun saveDeliverymanDetails(details: String) {
        context.dataStoree.edit { preferences ->
            preferences[DELIVERYMAN_DETAILS_TOKEN] = details
        }
    }
}

