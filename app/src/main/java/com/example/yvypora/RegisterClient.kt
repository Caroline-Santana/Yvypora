package com.example.yvypora


import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.rememberImagePainter
import com.example.yvypora.service.buscarCep
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.MaskCep
import com.example.yvypora.utils.MaskCpf
import com.example.yvypora.utils.ValidationCpf


class RegisterClient : ComponentActivity() {
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

    val context = LocalContext.current

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(top = 170.dp, start = 24.dp, end = 24.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true),
        verticalArrangement = Arrangement.Center,

        ) {
        //Input nome
        NameInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        //Input Email
        EmailInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        // Input senha
        PassInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )
        //Input photo
        PhotoInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        //Input cpf
        CpfInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        //Input cep
        CepInput()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        //But??o de cadastro
        Button(
            onClick = {
//                val intent = Intent(context,RegisterClient()::class.java)
//                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(217.dp)
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.button_register),
                color = Color.White,
                fontSize = 20.sp
            )
        }
        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )
    }
}

@Composable
fun NameInput() {
    var nameState by rememberSaveable {
        mutableStateOf("")
    }
    var isNameError by remember {
        mutableStateOf(false)
    }
    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.name),
        modifier = Modifier.padding(top = 5.dp),
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
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            cursorColor = colorResource(id = R.color.darkgreen_yvy)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(inputsFocusRequest),
        isError = isNameError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        singleLine = true,

        )

    if (isNameError) {
        Text(
            text = stringResource(id = R.string.name_error),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun EmailInput() {
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
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
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

}

@Composable
fun PassInput() {
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
fun PhotoInput() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.adicionar_foto
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri: Uri? ->
        uri?.let {imageUri.value = it.toString()}
    }


    Column(
        modifier =
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(id = R.string.profile_picture),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(115.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 12.dp)
                    .clickable { launcher.launch("image/*")},
                contentScale = ContentScale.Crop
            )

        }
    }

}

@Composable
fun CpfInput() {
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
            .fillMaxSize()
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
fun CepInput() {
    var cepState by rememberSaveable {
        mutableStateOf("")
    }
    var isCepErrorEmpty by remember {
        mutableStateOf(false)
    }
    var isCepError by remember {
        mutableStateOf(false)
    }
    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    var cep = ""

    Text(
        text = stringResource(id = R.string.title_cep),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = cepState,
        onValueChange = { newCep ->
            isCepErrorEmpty = newCep.isEmpty()

            if (cepState.length > 8) newCep.dropLast(1)

            if (cepState.length == 8) {

                cep = buscarCep(cepState) {
                    cep = it
                }.toString()


                if (cep.isEmpty()) {
                    isCepError = true
                } else {
                    isCepError = false
                    isCepErrorEmpty = false
                }
            }

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
            .fillMaxSize()
            .focusRequester(inputsFocusRequest),
        isError = isCepErrorEmpty,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskCep(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
    if (isCepErrorEmpty) {
        Text(
            text = stringResource(id = R.string.cep_error_empty),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    } else if (isCepError) {
        Text(
            text = stringResource(id = R.string.cep_error_invalid),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)

@Composable
fun DefaultPreview() {
    YvyporaTheme {
        Inputs()
    }
}