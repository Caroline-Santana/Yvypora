@file:Suppress("NAME_SHADOWING")

package com.example.yvypora.ScreenClients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.api.fairs.FairsAPIService
import com.example.yvypora.domain.models.Fair
import com.example.yvypora.domain.models.FairsMap
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.jvm.internal.Intrinsics.Kotlin


class FairsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {




            }
        }
    }
}





fun listLocationFair() = listOf<LatLng>(
    LatLng(-23.55, -46.64),
    LatLng(-23.2167, -44.7179),
    LatLng(-21.55, -46.64),
    LatLng(-20.55, -46.64),
)

fun listMarketerFair() = listOf<com.example.yvypora.domain.models.FairsMap>(
    com.example.yvypora.domain.models.FairsMap(
        id = 1,
        photo = "feira.png",
        name = "Feira de Sampa",
        dayOfWork = "Domingo",
        hourStartOfWork = 11,
        minuteStartOfWork = 25,
        hourEndOfWork = 18,
        minuteEndOfWork = 30,
        aproxUserCloser = 2,
        ratingMarketer = 3.5
    ),
    com.example.yvypora.domain.models.FairsMap(
        id = 2,
        photo = "feira.png",
        name = "Feira de Paraty",
        dayOfWork = "Domingo",
        hourStartOfWork = 11,
        minuteStartOfWork = 25,
        hourEndOfWork = 18,
        minuteEndOfWork = 30,
        aproxUserCloser = 2,
        ratingMarketer = 5.0
    ),
    com.example.yvypora.domain.models.FairsMap(
        id = 3,
        photo = "feira.png",
        name = "Feira de Caconde",
        dayOfWork = "Domingo",
        hourStartOfWork = 11,
        minuteStartOfWork = 25,
        hourEndOfWork = 18,
        minuteEndOfWork = 30,
        aproxUserCloser = 2,
        ratingMarketer = 3.5
    ),
    com.example.yvypora.domain.models.FairsMap(
        id = 4,
        photo = "feira.png",
        name = "Feira de Capitólio",
        dayOfWork = "Domingo",
        hourStartOfWork = 11,
        minuteStartOfWork = 25,
        hourEndOfWork = 18,
        minuteEndOfWork = 30,
        aproxUserCloser = 2,
        ratingMarketer = 3.5
    )
)


@Composable
fun ListOfFairs(fairs: List<com.example.yvypora.domain.models.FairsMap>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(fairs) { Fairs -> FairsComponent(fair = Fairs) }
    }
}

@Composable
fun FairsComponent(fair: com.example.yvypora.domain.models.FairsMap) {

    val nameFair = fair.name
//    val photoFair = fair.photo
    val photoFair = painterResource(id = R.drawable.fair_photo)
    val dayOfWork = fair.dayOfWork
    val HourStartWork = fair.hourStartOfWork
    val minuteStartWork = fair.minuteStartOfWork
    val HourEndWork = fair.hourEndOfWork
    val minuteEndWork = fair.minuteEndOfWork
    val aproxUser = fair.aproxUserCloser
    val rating = fair.ratingMarketer
    val calendar = painterResource(id = R.drawable.calendartick)
    val clock = painterResource(id = R.drawable.clock)
    val user = painterResource(id = R.drawable.user)

    Card(
        Modifier
            .padding(6.dp)
            .height(350.dp),
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                ,
        ) {
            Image(
                painter = photoFair,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(157.dp)
            )
            Row() {
                Column(
                    Modifier
                        .fillMaxWidth(0.75f)
                        .padding(start = 20.dp)) {
                    Text(
                        text = nameFair,
                        color = colorResource(
                            id = R.color.green_text_dark
                        ),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline

                    )
                    Column(Modifier.fillMaxWidth()) {
                        Row() {
                            Image(
                                painter = calendar, contentDescription = "", modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                            )
                            Text(
                                text = dayOfWork, fontSize = 16.sp,
                                color = colorResource(
                                    id = R.color.green_text_dark
                                )
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            val timeStart = LocalTime.of(HourStartWork,minuteStartWork)
                            val timeEnd = LocalTime.of(HourEndWork,minuteEndWork)
                            val formatter = DateTimeFormatter.ofPattern("HH:mm")



                            Image(
                                painter = clock, contentDescription = "", modifier = Modifier
                                    .height(20.dp)
                                    .width(30.dp)
                            )
                            Text(
                                text = timeStart.format(formatter) + "-" + timeEnd.format(formatter),
                                color = colorResource(
                                    id = R.color.green_text_dark
                                )
                            )
                        }
                        Row(
                            Modifier.padding(start = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = user, contentDescription = "", modifier = Modifier
                                    .height(30.dp)
                                    .width(20.dp)
                            )
                            Text(
                                text = "Aprox $aproxUser feirantes da Yvy",
                                Modifier.padding(start = 2.dp),
                                color = colorResource(
                                    id = R.color.green_text_dark
                                )
                            )
                        }
                    }
                }

                Row(
                    Modifier.height(35.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "",
                        tint = colorResource(
                            id = R.color.yellow_star
                        )
                    )
                    Text(
                        text = rating.toString(),
                        color = colorResource(
                            id = R.color.green_text_dark
                        )
                    )
                }
            }
        }


    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(id = R.color.green_yvy))
            .padding(start = 100.dp)
    )

}


@Preview(showBackground = true)
@Composable
fun FairPreview() {
    YvyporaTheme() {
        FairsActivity()
    }
}