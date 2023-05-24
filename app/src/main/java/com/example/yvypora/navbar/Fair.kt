package com.example.yvypora.navbar

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.yvypora.ScreenClients.*
import com.example.yvypora.api.fairs.FairsAPIService
import com.example.yvypora.domain.location.LocationTracker
import com.example.yvypora.domain.models.Fair
import com.example.yvypora.domain.models.FairsMap
import com.example.yvypora.domain.models.dto.Coordinates
import com.example.yvypora.services.location.DefaultLocationTracker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint
import getUserLocation
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


private fun bindDataToFairCard(data: com.example.yvypora.domain.models.Fair): com.example.yvypora.domain.models.FairsMap {
    val dayOfWeek = data.fairDateHourOfWork.map { it.dates.dayOfWeek.abbr }
        .reduce { acc, string -> acc + string }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val openDate: Date =
        dateFormat.parse(data.fairDateHourOfWork.map { it.dates.openDatetime }.get(0))
    val closeDate: Date =
        dateFormat.parse(data.fairDateHourOfWork.map { it.dates.closeDatetime }.get(0))

    val openLocalDateTime = openDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    val closeLocalDateTime = closeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

    return com.example.yvypora.domain.models.FairsMap(
        id = data.id,
        name = data.name,
        photo = data.image.uri,
        aproxUserCloser = data.marketerCount,
        ratingMarketer = 0.0,
        dayOfWork = dayOfWeek,
        hourEndOfWork = closeLocalDateTime.hour,
        minuteEndOfWork = closeLocalDateTime.minute,
        hourStartOfWork = openLocalDateTime.hour,
        minuteStartOfWork = openLocalDateTime.minute,
    )
}


@Composable
fun Fair(
) {

    val context = LocalContext.current
    val coordinates = getUserLocation(context = context)
    var listFairsMap by remember { mutableStateOf(listOf<FairsMap>()) }

    Log.i("teste", coordinates.toString())


    LaunchedEffect(coordinates) {
        FairsAPIService.listByClose(
            longitude = coordinates.long.toString(),
            latitude = coordinates.lat.toString(),
        ) {
            val fairs = it.payload
            listFairsMap = fairs.map { bindDataToFairCard(it) }
            Log.i("feiras", listFairsMap.toString())
        }
    }

    val Fair = (LatLng(coordinates.lat, coordinates.long))

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