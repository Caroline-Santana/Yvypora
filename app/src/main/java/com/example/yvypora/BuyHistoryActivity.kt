package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.models.MarketerCard
import com.example.yvypora.ui.theme.YvyporaTheme

class BuyHistory : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                BuyHistoryMain()
            }
        }

    }
}

@Composable
fun BuyHistoryMain() {
    Box {
        Column() {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp, bottom = 15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        modifier = Modifier
                            .height(45.dp)
                            .width(55.dp)
                            .clickable {
                                val intent = Intent(context, ProfileClient()::class.java)
                                context.startActivity(intent)
                            },
                        alignment = Alignment.BottomStart,
                        contentDescription = stringResource(id = R.string.back_screen)
                    )
                    Text(
                        text = stringResource(id = R.string.purchase_history),
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Blue)
                ) {
                    Row() {
                        ListOfMarketerCard(marketers = listMarketerCard)
                    }
                }
            }
        }
    }
}


val listMarketerCard = listOf<MarketerCard>(
    MarketerCard(
        name = "Barraca do Seu Zé do Alfácil",
        sub_name = "Vila Augusta",
        photo = "https://s2.glbimg.com/kp-KTfD-SKBXxwKd0-76aS0VTVE=/0x0:1280x853/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2017/0/S/BF1BCgRESb0CcCOfMIAA/feira2.jpg",
        date = "15/02/2022"
    ),
    MarketerCard(
        name = "Barraca do Seu Zé",
        sub_name = "Vila Madalena",
        photo = "https://s2.glbimg.com/kp-KTfD-SKBXxwKd0-76aS0VTVE=/0x0:1280x853/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2017/0/S/BF1BCgRESb0CcCOfMIAA/feira2.jpg",
        date = "11/02/2022"
    )
)

@Composable
fun ListOfMarketerCard(marketers: List<MarketerCard>) {
    LazyColumn() {
        items(marketers) { marketer -> CardMarketer(marketer = marketer) }
    }
}


@Composable
fun CardMarketer(marketer: MarketerCard) {
    var nameCard = marketer.name
    var subnameCard = marketer.sub_name
//    tem que descomentar e usar o que o banco retorna
//    var photo = marketer.photo
    var photo = painterResource(id = R.drawable.buy_history_card_marketer)
    var date = marketer.date

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = photo,
                contentDescription = "",
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp)
            )
            Column(
                modifier =
                Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nameCard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Text(
                    text = subnameCard,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
                Text(
                    text = date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

            }

        }


    }

}

//@Preview(showBackground = true)
//@Composable
//fun BuyPreview() {
//    BuyHistoryMain()
//}
