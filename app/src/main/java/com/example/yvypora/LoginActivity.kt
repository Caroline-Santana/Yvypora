package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var nameState by rememberSaveable {
        mutableStateOf("")
    }
    var emailState by rememberSaveable {
        mutableStateOf("")
    }
    var isNameError by remember {
        mutableStateOf(false)
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    //Objeto que controla a requisição de foco
    val inputsFocusRequest = FocusRequester()
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 24.dp, top = 320.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = stringResource(id = R.string.name),
            modifier = Modifier.padding(bottom = 6.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        OutlinedTextField(
            value = nameState,
            onValueChange = { newName ->
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedBorderColor = colorResource(id = R.color.transparentgreen_yvy),
                unfocusedBorderColor = colorResource(id = R.color.transparentgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest)
                .padding(0.dp),

            trailingIcon = {
                if (nameState.length <= 0) {
                    Icon(
                        painter = painterResource(R.drawable.iconboxe),
                        contentDescription = stringResource(id = R.string.icon_content_description),
                        modifier = Modifier
                            .width(31.dp)
                            .height(32.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.icon_colorful),
                        contentDescription = stringResource(id = R.string.icon_content_description),
                        modifier = Modifier
                            .width(31.dp)
                            .height(32.dp),
                        tint = Color.Unspecified
                    )
                }
            },

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
            modifier = Modifier.height(18.dp)
        )
        Text(
            text = stringResource(id = R.string.email),
            modifier = Modifier.padding(bottom = 6.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        OutlinedTextField(
            value = emailState,
            onValueChange = { newEmail ->
                if (newEmail.isEmpty()) {
                    isEmailError = true
                } else if (isEmailValid(newEmail) == false) {
                    isEmailError = true
                } else {
                    newEmail.get(newEmail.length - 1)
                    isEmailError = false
                }

                emailState = newEmail
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedBorderColor = colorResource(id = R.color.transparentgreen_yvy),
                unfocusedBorderColor = colorResource(id = R.color.transparentgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest)
                .padding(0.dp),

            trailingIcon = {
                if (nameState.length <= 0) {
                    Icon(
                        painter = painterResource(R.drawable.iconboxe),
                        contentDescription = stringResource(id = R.string.icon_content_description),
                        modifier = Modifier
                            .width(31.dp)
                            .height(32.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.icon_colorful),
                        contentDescription = stringResource(id = R.string.icon_content_description),
                        modifier = Modifier
                            .width(31.dp)
                            .height(32.dp),
                        tint = Color.Unspecified
                    )
                }
            },

            isError = isEmailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),

            )

        if (isEmailError) {
            Text(
                text = stringResource(id = R.string.email_error),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )


        }
        Spacer(
            modifier = Modifier.height(44.dp)
        )

        Button(
            onClick = {

            },
            modifier = Modifier
                .width(257.dp)
                .height(54.dp)
                .padding(start = 100.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),


            ) {
            Text(
                text = stringResource(id = R.string.enter),
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}