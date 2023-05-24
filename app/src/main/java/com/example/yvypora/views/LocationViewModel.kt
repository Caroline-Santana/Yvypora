package com.example.yvypora.views

import LocationLiveData
import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class LocationViewModel() : ViewModel() {
    private var locationLiveData: LocationLiveData? = null;

    fun getLocationLiveData(context: Context): LocationLiveData {
        if (this.locationLiveData === null) {
            this.locationLiveData = LocationLiveData(context);
        }
        return this.locationLiveData!!
    }
    fun startLocationUpdates() {
        locationLiveData?.let {
            it.startLocationUpdates()
        }
    }
}