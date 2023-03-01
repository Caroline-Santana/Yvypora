package com.example.yvypora

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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

class RegisterClient : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),

                    ) {
                    Image(
                        painter =  painterResource(id =R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(55.dp)
                            .padding(horizontal = 28.dp),
                        alignment = Alignment.BottomStart,
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
                        Text(
                            text = stringResource(id = R.string.register),
                            modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.darkgreen_yvy),
                            fontSize = 48.sp
                        )
                    }
                }
            }
            Inputs()
        }
    }
}

@Composable
fun Inputs() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    var emailState by rememberSaveable {
        mutableStateOf("")
    }
    var passwordState by rememberSaveable {
        mutableStateOf("")
    }
    var isNameError by remember {
        mutableStateOf(false)
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    var isPasswordError by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val mMaxLength = 8
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
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,

        ) {
        //Input nome
        Text(
            text = stringResource(id = R.string.name),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy) ,
                unfocusedIndicatorColor =  colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest),
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

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        //Input Email
        Text(
            text = stringResource(id = R.string.email),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy) ,
                unfocusedIndicatorColor =  colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest),
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

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        // Input senha
        Text(
            text = stringResource(id = R.string.password),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = passwordState,
            onValueChange = { newPass ->
                if (newPass.isEmpty()) {
                    isPasswordError = true
                } else if (newPass.length >= mMaxLength) {
                    isPasswordError = true
                } else {
                    newPass.get(newPass.length - 1)
                    isPasswordError = false
                }

                if(isPasswordError) newPass.dropLast(1)

                passwordState = newPass
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy) ,
                unfocusedIndicatorColor =  colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest),
            isError = isPasswordError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
        )
        if (isPasswordError) {
            Text(
                text = stringResource(id = R.string.message_error_pass),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)

@Composable
fun DefaultPreview() {
   YvyporaTheme{
        Inputs()
    }
}