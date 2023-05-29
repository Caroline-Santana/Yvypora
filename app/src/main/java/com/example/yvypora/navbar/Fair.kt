package com.example.yvypora.navbar

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.requestLocationUpdates
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.yvypora.ScreenClients.*
import com.example.yvypora.api.fairs.FairsAPIService
import com.example.yvypora.domain.models.FairsMap
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.views.LocationViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import getUserLocation
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


@Composable
fun Fair(){
   BottomSheetFairs()
}

//class Fair : ComponentActivity()  {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            YvyporaTheme {
//                MainContent()
//            }
//        }
//
//    }
//
//    private fun bindDataToFairCard(data: com.example.yvypora.domain.models.Fair): com.example.yvypora.domain.models.FairsMap {
//        val dayOfWeek = data.fairDateHourOfWork.map { it.dates.dayOfWeek.abbr }
//            .reduce { acc, string -> acc + string }
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        val openDate: Date =
//            dateFormat.parse(data.fairDateHourOfWork.map { it.dates.openDatetime }.get(0))
//        val closeDate: Date =
//            dateFormat.parse(data.fairDateHourOfWork.map { it.dates.closeDatetime }.get(0))
//
//        val openLocalDateTime = openDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
//        val closeLocalDateTime = closeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
//
//        Log.i("teste", dayOfWeek)
//        Log.i("teste", data.toString())
//        Log.i("teste", openLocalDateTime.toString())
//        Log.i("teste", closeLocalDateTime.toString())
//
//        val image = data.image?.uri
//
//        return com.example.yvypora.domain.models.FairsMap(
//            id = data.id,
//            name = data.name,
//            photo = image ?: "",
//            aproxUserCloser = data.marketerCount,
//            ratingMarketer = 0.0,
//            dayOfWork = dayOfWeek,
//            hourEndOfWork = closeLocalDateTime.hour,
//            minuteEndOfWork = closeLocalDateTime.minute,
//            hourStartOfWork = openLocalDateTime.hour,
//            minuteStartOfWork = openLocalDateTime.minute,
//        )
//    }
//
//
//    @Composable
//    fun MainContent(
//
//    )  {
//        val locationViewModel : LocationViewModel  = viewModel<LocationViewModel>()
//        val context = LocalContext.current
//
//        askForPermissions(context, locationViewModel)
//
//        val coordinates by locationViewModel.getLocationLiveData(context).observeAsState()
//
//        var listFairsMap by remember { mutableStateOf(listOf<FairsMap>()) }
//
//
//        LaunchedEffect(coordinates) {
//            val long = coordinates?.long ?: "0.0"
//            val lat = coordinates?.long ?: "0.0"
//            FairsAPIService.listByClose(
//                longitude = long.toString(),
//                latitude = lat.toString(),
//            ) {
//                val fairs = it.payload
//                Log.i("teste", fairs.toString())
//                listFairsMap = fairs.map { bindDataToFairCard(it) }
//            }
//        }
//
//
//
//        val long = coordinates?.long ?: 0.0
//        val lat = coordinates?.long ?: 0.0
//
//        val Fair = (LatLng(lat, long))
//
//        val cameraPositionState = rememberCameraPositionState {
//            position = CameraPosition.fromLatLngZoom(Fair, 5f)
//        }
//
//        val combinedList = listLocationFair().zip(listMarketerFair())
//        Column {
//            Header()
//            Spacer(modifier = Modifier.padding(top = 10.dp))
//            GoogleMap(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(325.dp),
//                cameraPositionState = cameraPositionState
//            ) {
//                combinedList.forEach { item ->
//                    Marker(
//                        state = MarkerState(item.first),
//                        title = item.second.name,
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.padding(top = 20.dp))
//            ListOfFairs(fairs = listFairsMap)
//        }
//    }
//
//    private fun askForPermissions(context: Context, viewModel: LocationViewModel) {
//        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            requestLocationUpdates(viewModel)
//        } else {
//            requestSinglePermissionLauncher(viewModel)
//        }
//    }
//
//    private fun requestSinglePermissionLauncher(viewModel: LocationViewModel)
//    {
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
//                isGranted ->
//            if (isGranted) {
//                requestLocationUpdates(viewModel)
//            } else {
//                Toast.makeText(this, "Cannot work without the necessary permissions", Toast.LENGTH_SHORT).show()
//            }
//        }.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
//    }
//
//    private fun requestLocationUpdates(viewModel: LocationViewModel) {
//        viewModel.startLocationUpdates()
//    }
//}