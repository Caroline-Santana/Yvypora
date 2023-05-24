package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yvypora.DecisionSreenActivity
import com.example.yvypora.R
import com.example.yvypora.models.User
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlin.math.round

class AvaliationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        HeaderAvaliation()
                }
            }
        }
    }
}

@Composable
fun HeaderAvaliation() {
    val context = LocalContext.current
    var userParsed by remember { mutableStateOf<User?>(null) }
    val profileImage = rememberImagePainter(data = userParsed?.picture_uri ?: "")
    val image = rememberImagePainter(product.value?.imageOfProduct?.get(0)?.image?.uri ?: "")
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .statusBarsPadding(),
        contentAlignment = Alignment.TopEnd
    ){
        Image(
            painter = painterResource(id = R.drawable.wave__6_),
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp),
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Image(
            painter = profileImage,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProfileClient()::class.java)
                    context.startActivity(intent)
                }
                .padding(25.dp)
                .border(BorderStroke(1.dp, color = Color.Magenta))
                .height(50.dp)
                .width(55.dp),
            contentDescription = "logo",
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 90.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Compra #109189",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            }
            ColumnAvaliations()
        }
    }

@SuppressLint("SuspiciousIndentation")
@Composable
fun ColumnAvaliations(){
    val context = LocalContext.current
    var comentarioState by rememberSaveable {
        mutableStateOf("")
    }
    val inputsFocusRequest = FocusRequester()
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 10.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(145.dp))
            Text(
                text = stringResource(id = R.string.avalie_order),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            OutlinedTextField(
                value = comentarioState,
                onValueChange = { comentarioState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .focusRequester(inputsFocusRequest),
                singleLine = false,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 10.dp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green_camps),
                    unfocusedBorderColor = colorResource(id = R.color.green_camps),
                    cursorColor = colorResource(id = R.color.darkgreen_yvy),
                    textColor = colorResource(id = R.color.green_text_comentario),
                    backgroundColor = colorResource(id = R.color.green_camps)
                ),
                textStyle = MaterialTheme.typography.body1,
                label = {
                    Text(
                        stringResource(id = R.string.ex_comentario),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.green_text_comentario),
                        fontSize = 18.sp
                    )
                }
            )
            Text(
                text = stringResource(id = R.string.nota_delivery),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            RatingClick()
            Text(
                text = stringResource(id = R.string.nota_products),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            RatingClick()
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.text_avaliation),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(end = 40.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End) {
                        ClickableText(
                            AnnotatedString(
                                text = stringResource(id = R.string.click_avaliation),
                                SpanStyle(
                                    fontSize = 20.sp,
                                    color = colorResource(id = R.color.green_camps),
                                    textDecoration = TextDecoration.Underline,
                                )
                            ), onClick = {
//                                val intent = Intent(context, DecisionSreenActivity()::class.java)
//                                context.startActivity(intent)

                            }
                        )
                    }
                }
            Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, StatusOrder()::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .height(58.dp)
                        .width(170.dp),
                    shape = RoundedCornerShape(
                        topStart = 13.dp,
                        topEnd = 0.dp,
                        bottomEnd = 13.dp,
                        bottomStart = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),
                ) {
                    Text(
                        text = stringResource(id = R.string.button_avaliation),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
            }
        }
}

@Composable
fun RatingClick() {
    val selectedStars = remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center) {
        repeat(5) {index ->
            val isSelected = index< selectedStars.value
            val starColor = if (isSelected) colorResource(id = R.color.yellow_star) else colorResource(id = R.color.gray_star)

            Icon(
                painter = painterResource(id = R.drawable.star),
                modifier = Modifier
                    .clickable { selectedStars.value = index + 1 }
                    .height(55.dp)
                    .width(48.dp),
                contentDescription = null,
                tint = starColor,
            )
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun AvaliationScreenPreview() {
    YvyporaTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        )
            {
            HeaderAvaliation()
            }
    }
}
