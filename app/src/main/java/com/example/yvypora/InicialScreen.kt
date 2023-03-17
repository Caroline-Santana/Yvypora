package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
                    modifier = Modifier.fillMaxWidth(),

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


                }
            }

        }
    }


}
@Composable
fun LayoutMain() {
    Text("teste")
}
//    var searchState by remember {
//        mutableStateOf("")
//    }
//    Column( modifier = Modifier
//        .fillMaxWidth()
//        .fillMaxSize()
//    ) {
//
//    }
//    OutlinedTextField(
//
//        value = searchState,
//        onValueChange = {
//            searchState = it
//        },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = Color.Unspecified,
//            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
//            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
//            cursorColor = colorResource(id = R.color.darkgreen_yvy)
//        ),
//    )


