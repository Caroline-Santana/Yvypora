package com.example.yvypora.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.yvypora.ScreenClients.Header
import com.example.yvypora.ScreenClients.ListOfFairs
import com.example.yvypora.ScreenClients.listLocationFair
import com.example.yvypora.ScreenClients.listMarketerFair
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun FairMarketer(){

    val Fair = (LatLng(-23.55, -46.64))
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(Fair, 5f)
    }
    val combinedList = listLocationFair().zip(listMarketerFair())
    Column {
        Header()
        Spacer(modifier = Modifier.padding(top = 10.dp))
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(325.dp),
            cameraPositionState = cameraPositionState
        ) {
            combinedList.forEach { item ->
                Marker(
                    state = MarkerState(item.first),
                    title = item.second.name,
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
//        ListOfFairs(fairs = listMarketerFair(), bottomSheetState =)
    }
}