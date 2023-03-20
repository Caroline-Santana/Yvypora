package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme

class InicialScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(55.dp)
                            .padding(horizontal = 28.dp),
                        alignment = Alignment.BottomStart,
                        contentDescription = "logo",

                        )
                    Image(
                        painter = painterResource(id = R.drawable.icon_user),
                        modifier = Modifier
                            .height(50.dp)
                            .width(55.dp)
                            .padding(top = 5.dp, end = 15.dp),
                        alignment = Alignment.BottomEnd,
                        contentDescription = "logo",

                        )
                }
                LayoutMain()
            }

        }
    }


}
@Composable
fun LayoutMain() {
        var searchState by remember {
        mutableStateOf("")
    }
    Column( modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(top = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(

            value = searchState,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 25.dp, end = 25.dp)
            ,
            onValueChange = {
                searchState = it
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedIndicatorColor = colorResource(id = R.color.transparentgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.transparentgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.lupa_icon),
                    contentDescription = stringResource(id = R.string.lupa),
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .padding(end = 10.dp),
                    tint = Color.White
                )
            }
        )
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.morango),
                modifier = Modifier.height(80.dp).width(70.dp),
                contentDescription = "Fruit" 
            )
            Image(
                painter = painterResource(id = R.drawable.morango),
                modifier = Modifier.height(80.dp).width(70.dp),
                contentDescription = "Fruit"
            )
            Image(
                painter = painterResource(id = R.drawable.morango),
                modifier = Modifier.height(80.dp).width(70.dp),
                contentDescription = "Fruit"
            )
            Image(
                painter = painterResource(id = R.drawable.morango),
                modifier = Modifier.height(80.dp).width(70.dp),
                contentDescription = "Fruit"
            )

        }
    }

}



