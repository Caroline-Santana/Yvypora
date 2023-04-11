package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.yvypora.ui.theme.YvyporaTheme

class AddCardAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        AddCard()
                }
            }
        }
    }
}

@Composable
fun AddCard () {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.wave1),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        alignment = Alignment.TopCenter,
        contentDescription = stringResource(id = R.string.back_screen)
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 25.dp, end = 25.dp, top = 190.dp)
        .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.add_card_pay_methods),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            color = colorResource(id = R.color.green_yvy),
            textAlign = TextAlign.Center
        )
        NameInputCard()
        Spacer(modifier = Modifier.height(15.dp))
        NumberInputCard()
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CvvInputCard()
            DateInputCard()
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                val intent = Intent(context, PaymentMethodsActivity()::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(217.dp)
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
fun NameInputCard() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.name_card_pay_methods),
        modifier = Modifier.padding(top = 35.dp),
        fontSize = 20.sp,
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
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(inputsFocusRequest),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        singleLine = true,

        )

}

@Composable
fun NumberInputCard() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.number_card_pay_methods),
        modifier = Modifier.padding(top = 35.dp),
        fontSize = 20.sp,
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
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(inputsFocusRequest),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            capitalization = KeyboardCapitalization.Sentences
        ),
        singleLine = true,

        )

}

@Composable
fun CvvInputCard() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    val inputsFocusRequest = FocusRequester()

    Column(modifier = Modifier.padding(15.dp)) {
        Text(
            text = "CVV",
            modifier = Modifier.padding(top = 35.dp),
            fontSize = 20.sp,
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
                .width(96.dp)
                .height(56.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,

            )
    }


}

@Composable
fun DateInputCard() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    val inputsFocusRequest = FocusRequester()

    Column(modifier = Modifier.padding(15.dp)) {

        Text(
            text = stringResource(id = R.string.validity_card_pay_methods),
            modifier = Modifier.padding(top = 35.dp),
            fontSize = 20.sp,
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
                .width(114.dp)
                .height(56.dp)
                .focusRequester(inputsFocusRequest),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.Sentences
            ),
            singleLine = true,

            )
    }
}

@Preview(showBackground = true)
@Composable
fun AddPayMethodsAcitivityPreview() {
    YvyporaTheme {
            AddCard()
    }
}