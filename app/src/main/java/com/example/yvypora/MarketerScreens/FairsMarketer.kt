package com.example.yvypora.MarketerScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.HeaderProfile
import com.example.yvypora.ScreenClients.fetchDetails
import com.example.yvypora.models.FairsMap
import com.example.yvypora.ui.theme.YvyporaTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FairsMarketer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val user = fetchDetails();
                        HeaderProfile(user)
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        FairsMain()
                    }
                }
            }
        }
    }
}

@Composable
fun FairsMain() {
    Text(
        text = stringResource(id = R.string.myFair),
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        color = colorResource(id = R.color.darkgreen_yvy2)
    )
    ListOfFairsMarketer(fairs = listMarketerFair())

}

fun listMarketerFair() = listOf<FairsMap>(
    FairsMap(
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
    FairsMap(
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
    FairsMap(
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
    FairsMap(
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
fun ListOfFairsMarketer(fairs: List<FairsMap>) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(fairs) { Fairs -> FairsComponent(fair = Fairs) }
    }
}

@Composable
fun FairsComponent(fair: FairsMap) {

    val nameFair = fair.name
//    val photoFair = fair.photo
    val photoFair = painterResource(id = R.drawable.fair_photo)
    val dayOfWork = fair.dayOfWork
    val HourStartWork = fair.hourStartOfWork
    val minuteStartWork = fair.minuteStartOfWork
    val HourEndWork = fair.hourEndOfWork
    val minuteEndWork = fair.minuteEndOfWork
    val rating = fair.ratingMarketer
    val calendar = painterResource(id = R.drawable.calendartick)
    val clock = painterResource(id = R.drawable.clock)


    Card(
        Modifier
            .padding(6.dp)
            .height(350.dp),
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            var checkState by rememberSaveable {
                mutableStateOf(false)
            }
            Switch(
                checked = checkState, onCheckedChange = { checkState = it },
                Modifier
                    .width(100.dp)
                    .height(60.dp),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = colorResource(id = R.color.green_yvy),
                    uncheckedThumbColor = colorResource(id = R.color.green_yvy),
                    checkedTrackColor = colorResource(id = R.color.green_yvy),
                    uncheckedTrackColor = colorResource(id = R.color.gray_yvy),
                )
            )
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
                        .padding(start = 20.dp)
                ) {
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

                            val timeStart = LocalTime.of(HourStartWork, minuteStartWork)
                            val timeEnd = LocalTime.of(HourEndWork, minuteEndWork)
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
}


//@Composable
//fun FairsCard(fair:FairsMap) {
//    val nameFair = fair.name
////    val photoFair = fair.photo
//    val photoFair = painterResource(id = R.drawable.fair_photo)
//    val dayOfWork = fair.dayOfWork
//    val HourStartWork = fair.hourStartOfWork
//    val minuteStartWork = fair.minuteStartOfWork
//    val HourEndWork = fair.hourEndOfWork
//    val minuteEndWork = fair.minuteEndOfWork
//    val aproxUser = fair.aproxUserCloser
//    val rating = fair.ratingMarketer
//    val calendar = painterResource(id = R.drawable.calendartick)
//    val clock = painterResource(id = R.drawable.clock)
//    val user = painterResource(id = R.drawable.user)
//
//
//    }
//
//
//}

