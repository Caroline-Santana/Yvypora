package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme

class DecisionSreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {

                Surface(
                    modifier = Modifier.fillMaxWidth(),

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(55.dp)
                            .padding(horizontal = 28.dp),
                        alignment = Alignment.BottomEnd,
                        contentDescription = "logo",

                    )
                    Spacer(
                        modifier = Modifier.height(36.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        TitleWithStyle()
                        Spacer(
                            modifier = Modifier.height(36.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.frutas_verduras),
                            modifier = Modifier
                                .height(238.dp)
                                .fillMaxWidth()
                                .padding(end = 18.dp)
                                ,
                            contentDescription = "background"
                        )
                    }
                }
                FildDecision()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleWithStyle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.title1_activity_decision_sreen),
                modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.darkgreen_yvy),
                fontSize = 24.sp
            )
            Text(
                text = "Yvyporã,",
                color = colorResource(id = R.color.green_yvy),
                modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Text(
            text = stringResource(id = R.string.title2_activity_decision_sreen),
            modifier = Modifier.padding(end = 110.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy),
            fontSize = 24.sp
        )
    }
}

@Composable
fun FildDecision() {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier.height(280.dp)
        )
        Text(
            text = stringResource(id = R.string.question_decision_sreen),
            modifier = Modifier.padding(start = 21.dp, top = 35.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.darkgreen_yvy),
            fontSize = 20.sp
        )
        Spacer(
            modifier = Modifier.height(13.dp)
        )
        ButtonDecision()
    }
}

@Composable
fun ButtonDecision(){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) Color.White else colorResource(id = R.color.green_yvy)

    Row() {
        Button(
            onClick = {

            },
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            modifier = Modifier
                .width(160.dp)
                .height(58.dp)
                .padding(start = 21.dp),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.enter),
                color = Color.White,
                fontSize = 24.sp
            )
        }
        Button(
            onClick = {

            },
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            modifier = Modifier
                .width(160.dp)
                .height(58.dp)
                .padding(start = 21.dp),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.enter),
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}


