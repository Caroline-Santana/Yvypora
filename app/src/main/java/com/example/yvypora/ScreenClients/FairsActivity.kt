@file:Suppress("NAME_SHADOWING")

package com.example.yvypora.ScreenClients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yvypora.models.Fairs
import com.example.yvypora.models.MarketerFairNear
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class FairsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                ListOfFairs(fairs = listMarketerFair())
            }
        }
    }
}

fun listMarketerFair() = listOf<Fairs>(
    Fairs(
        latLng = LatLng(-23.55, -46.64),
        title_marker = "Feira de Teste",
        subtitle_marker = "Feira de Teste",
        listMarketer = listOf(
            MarketerFairNear(
                photo = "feira.png",
                name = "Feira de Teste",
                dayOfWork = "Domingo",
                timeOfWork = 11,
                aproxUserCloser = 2,
                ratingMarketer = 3.5
            )
        )
    ),
    Fairs(
        latLng = LatLng(-22.55, -46.63),
        title_marker = "Feira de Teste",
        subtitle_marker = "Feira de Teste",
        listMarketer = listOf(
            MarketerFairNear(
                photo = "feira.png",
                name = "Feira de Teste",
                dayOfWork = "Domingo",
                timeOfWork = 11,
                aproxUserCloser = 2,
                ratingMarketer = 3.5
            )
        )
    ),
    Fairs(
        latLng = LatLng(-20.55, -46.63),
        title_marker = "Feira de Teste",
        subtitle_marker = "Feira de Teste",
        listMarketer = listOf(
            MarketerFairNear(
                photo = "feira.png",
                name = "Feira de Teste",
                dayOfWork = "Domingo",
                timeOfWork = 11,
                aproxUserCloser = 2,
                ratingMarketer = 3.5
            )
        )
    )
)

@Composable
fun ListOfFairs(fairs: List<Fairs>) {
    LazyColumn() {
        items(fairs) { Fairs -> FairsComponent(fair = Fairs) }
    }
}

@Composable
fun ListOfMarketerFair(marketers: List<MarketerFairNear>) {
    LazyColumn() {
        items(marketers) { MarketerFairNear ->
            MarketerFairNearComponent(marketer = MarketerFairNear)
        }
    }
}

@Composable
fun MarketerFairNearComponent(marketer: MarketerFairNear) {


}

@Composable
fun FairsComponent(fair: Fairs, locations: List<LatLng>) {

    /*
    * TODO :
    *    - o mapa tá retorndo duas vezes pq tem duas latLong sendo informadas, porém
    *    deveria retornar um mapa só e dois markers
    * */


    val LocalFair = fair.latLng
    val TitleMarker = fair.title_marker
    val SubTitleMarker = fair.subtitle_marker

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LocalFair, 7f)
    }
    Column() {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = LocalFair),
                title = TitleMarker,
                snippet = SubTitleMarker
            )


        }
    }

}


@Preview(showBackground = true)
@Composable
fun FairPreview() {
    YvyporaTheme() {
        FairsActivity()
    }
}