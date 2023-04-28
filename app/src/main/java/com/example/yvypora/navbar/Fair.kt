package com.example.yvypora.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.*
import com.example.yvypora.models.Fairs
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Fair() {
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
        ListOfFairs(fairs = listMarketerFair())
    }
}




