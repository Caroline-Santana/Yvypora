package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.ui.theme.YvyporaTheme

class AddAddressAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainAddAdress()
                }
            }
        }
    }


    @Composable
    fun MainAddAdress() {
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
                            val intent = Intent(context, AdressesActivity::class.java)
                            context.startActivity(intent)
                        },
                    alignment = Alignment.BottomStart,
                    contentDescription = stringResource(id = R.string.back_screen)
                )
                Text(
                    text = stringResource(id = R.string.choose_location),
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
            }
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.googlemaps),
                    modifier = Modifier
                        .height(341.dp)
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    alignment = Alignment.BottomStart,
                    contentDescription = stringResource(id = R.string.back_screen)
                )
                AddAdresses()
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
//                val intent = Intent(context, AddAddressAcitivity()::class.java)
//                context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),

                ) {
                Text(
                    text = stringResource(id = R.string.to_save),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }

    }

    @Composable
    fun AddAdresses() {
        Column(modifier = Modifier.padding(top = 15.dp)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 193.dp, start = 12.dp, end = 12.dp)
                    .height(360.dp),
                backgroundColor = colorResource(id = R.color.green_camps),
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 0.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 12.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(id = R.string.details_address),
                        modifier = Modifier
                            .padding(start = 6.dp),
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    NameInputAdress()
                    NameRecipterInputAdress()
                    PhoneInputAdress()
                    CepInputAdress()
                    Spacer(modifier = Modifier.height(15.dp))
                    ChangeForMain()
                }
            }
        }
    }

    @Composable
    fun NameInputAdress() {
        var nameState by rememberSaveable {
            mutableStateOf("")
        }

        val inputsFocusRequest = FocusRequester()

        Text(
            text = stringResource(id = R.string.name_address),
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = nameState,
            onValueChange = { newName ->
                var lastChar = newName.get(newName.length - 1)

                var newValue =
                    if (lastChar == '.' || lastChar == ',')
                        newName.dropLast(1)
                    else newName
                nameState = newValue
            },
            placeholder = {
                Text(
                    text = "Ex: Casa",
                    fontSize = 16.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .width(283.dp)
                .height(53.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,

            )

    }

    @Composable
    fun NameRecipterInputAdress() {
        var nameState by rememberSaveable {
            mutableStateOf("")
        }

        val inputsFocusRequest = FocusRequester()

        Text(
            text = stringResource(id = R.string.name_of_recipient),
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = nameState,
            onValueChange = { newName ->
                var lastChar = newName.get(newName.length - 1)

                var newValue =
                    if (lastChar == '.' || lastChar == ',')
                        newName.dropLast(1)
                    else newName
                nameState = newValue
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .width(283.dp)
                .height(53.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,

            )

    }

    @Composable
    fun PhoneInputAdress() {
        var phoneState by rememberSaveable {
            mutableStateOf("")
        }
        var isPhoneErrorEmpty by remember {
            mutableStateOf(false)
        }
        val inputsFocusRequest = FocusRequester()


        Text(
            text = stringResource(id = R.string.phone_address),
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = phoneState,
            onValueChange = { newPhone ->
                isPhoneErrorEmpty = newPhone.isEmpty()

                if (phoneState.length > 10) newPhone.dropLast(1)

                phoneState = newPhone
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .width(283.dp)
                .height(53.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,
        )
        if (isPhoneErrorEmpty) {
            Text(
                text = stringResource(id = R.string.phone_error_empty),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
    }

    @Composable
    fun CepInputAdress() {
        var phoneState by rememberSaveable {
            mutableStateOf("")
        }
        var isPhoneErrorEmpty by remember {
            mutableStateOf(false)
        }
        val inputsFocusRequest = FocusRequester()


        Text(
            text = stringResource(id = R.string.cep_address),
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        TextField(
            value = phoneState,
            onValueChange = { newPhone ->
                isPhoneErrorEmpty = newPhone.isEmpty()

                if (phoneState.length > 10) newPhone.dropLast(1)

                phoneState = newPhone
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            modifier = Modifier
                .width(283.dp)
                .height(53.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,
        )
        if (isPhoneErrorEmpty) {
            Text(
                text = stringResource(id = R.string.cep_error_empty),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun AddAdressAcitivityPreview() {
        YvyporaTheme {
            MainAddAdress()
        }
    }
}