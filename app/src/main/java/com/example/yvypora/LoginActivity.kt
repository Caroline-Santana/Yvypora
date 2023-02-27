package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.Shapes
import com.example.yvypora.ui.theme.YvyporaTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .padding(top = 50.dp),

                ) {
                        Image(
                            painter = painterResource(id = R.drawable.background2),
                            alignment = Alignment.TopStart,
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth
                        )


                    }
                LoginLayout()
                }

            }
        }
    }


@Composable
fun LoginLayout() {

    var nameState by rememberSaveable{
        mutableStateOf("")
    }
    var emailState by rememberSaveable{
        mutableStateOf("")
    }
    var isNameError by remember{
        mutableStateOf(false)
    }
    //Objeto que controla a requisição de foco
    val inputsFocusRequest = FocusRequester()
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 24.dp, top = 48.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,

    ){
        Text(
            text = stringResource(id = R.string.name),
            modifier = Modifier.padding(bottom = 6.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        OutlinedTextField(
            value = nameState, onValueChange = { newName ->
                 var lastChar = if (newName.isEmpty()) {
                    isNameError = true
                    newName

                } else {
                    newName.get(newName.length - 1)
                    isNameError = false

                }
                var newValue = if (lastChar == '.' || lastChar == ',')
                    newName.dropLast(1)
                else newName
                nameState = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest)
                .background(color = colorResource(id = R.color.transparentgreen_yvy))
                .padding(0.dp),

//            leadingIcon = {
//
//            },
//
//            trailingIcon = {
////                if () Icon(imageVector = Icons.Rounded.Warning, contentDescription = "Oi")
//            },

            isError = isNameError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),

        )

        if (isNameError) {
            Text(
                text = stringResource(id = R.string.name_error),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}