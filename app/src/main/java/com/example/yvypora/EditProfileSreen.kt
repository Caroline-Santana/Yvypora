package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.navbar.ItemsMenu
import com.example.yvypora.navbar.NavigationHost
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.MaskCep
import com.example.yvypora.utils.MaskCpf
import com.example.yvypora.utils.ValidationCpf

class EditProfileSreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        HeaderProfile()
                        InputsProfile()
                    }

                }
            }
        }
    }
}


@Composable
fun InputsProfile() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 23.dp, end = 23.dp, top = 20.dp),
    )
    {
        Text(
            text = stringResource(id = R.string.edit_account),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 25.sp,
            color = colorResource(id = R.color.darkgreen_yvy),
            textAlign = TextAlign.Center
        )
        NameInputAgain()
        Spacer(modifier = Modifier.height(19.dp))
        EmailInputAgain()
        Spacer(modifier = Modifier.height(19.dp))
        PassInputAgain()
        Spacer(modifier = Modifier.height(19.dp))
        CpfInputAgain()
        Spacer(modifier = Modifier.height(19.dp))
        CepInputAgain()
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                val intent = Intent(context, ProfileClient()::class.java)
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
        Spacer(modifier = Modifier.height(19.dp))

    }
}

@Composable
fun NameInputAgain() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }

    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.name),
        modifier = Modifier.padding(top = 20.dp),
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
        placeholder = {
            Text(
                text = "Carlos Arcanjo",
                fontSize = 20.sp
            )
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
fun EmailInputAgain() {
    var emailState by rememberSaveable {
        mutableStateOf("")
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    val inputsFocusRequest = FocusRequester()

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);

    }

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
        placeholder = {
            Text(
                text = "carlaoprof@gmail.com",
                fontSize = 20.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            cursorColor = colorResource(id = R.color.darkgreen_yvy)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
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
}
@Composable
fun PassInputAgain() {
    var passwordState by rememberSaveable {
        mutableStateOf("")
    }


    var isPasswordError by remember {
        mutableStateOf(false)
    }
    var isPasswordErrorEmpty by remember {
        mutableStateOf(false)
    }

    val mMaxLength = 8

    val inputsFocusRequest = FocusRequester()


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
                isPasswordErrorEmpty = true
            } else if (newPass.length >= mMaxLength) {
                isPasswordError = true
            } else {
                newPass.get(newPass.length - 1)
                isPasswordError = false
            }

            if (isPasswordError) newPass.dropLast(1)

            passwordState = newPass
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
        isError = isPasswordError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
    if (isPasswordError) {
        Text(
            text = stringResource(id = R.string.message_error_pass2),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
    if (isPasswordErrorEmpty) {
        Text(
            text = stringResource(id = R.string.message_error_pass1),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}
@Composable
fun CpfInputAgain() {
    var cpfState by rememberSaveable {
        mutableStateOf("")
    }
    var isCpfErrorEmpty by remember {
        mutableStateOf(false)
    }
    var isCpfError by remember {
        mutableStateOf(false)
    }
    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.title_cpf),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = cpfState,
        onValueChange = { newCpf ->
            isCpfErrorEmpty = newCpf.isEmpty()

            if (cpfState.length > 11) newCpf.dropLast(1)


            if (!ValidationCpf.myValidateCPF(newCpf)) {
                isCpfError = true
            } else {
                isCpfError = false
                isCpfErrorEmpty = false
            }


            cpfState = newCpf
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            cursorColor = colorResource(id = R.color.darkgreen_yvy)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .focusRequester(inputsFocusRequest),
        isError = isCpfErrorEmpty,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskCpf(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
    if (isCpfErrorEmpty) {
        Text(
            text = stringResource(id = R.string.cpf_error_empty),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    } else if (isCpfError) {
        Text(
            text = stringResource(id = R.string.cpf_error_invalid),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun CepInputAgain() {

    var cepState by rememberSaveable {
        mutableStateOf("")
    }
    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.title_cep),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = cepState,
        onValueChange = {  newCep ->

            if (cepState.length > 11)  newCep.dropLast(1)

            cepState = newCep
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            cursorColor = colorResource(id = R.color.darkgreen_yvy)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .focusRequester(inputsFocusRequest),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskCpf(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewEditProfileSreen() {
    YvyporaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderProfile()
            InputsProfile()
        }
    }
}