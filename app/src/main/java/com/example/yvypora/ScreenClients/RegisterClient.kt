package com.example.yvypora.ScreenClients


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.api.cep.getCep
import com.example.yvypora.api.commons.addPictureToUser
import com.example.yvypora.api.commons.auth
import com.example.yvypora.api.commons.createCostumer
import com.example.yvypora.models.Address
import com.example.yvypora.models.Costumer
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.Token
import com.example.yvypora.service.datastore.TokenStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream


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





val imageUri = mutableStateOf("")

@Composable
fun Inputs() {
    var birthState by rememberSaveable {
        mutableStateOf("")
    }
    var isBirthErrorEmpty by remember {
        mutableStateOf(false)
    }

    var cpfState by rememberSaveable {
        mutableStateOf("")
    }
    var isCpfErrorEmpty by remember {
        mutableStateOf(false)
    }
    var isCpfError by remember {
        mutableStateOf(false)
    }

    var cepState by remember {
        mutableStateOf("")
    }
    var isCepErrorEmpty by remember {
        mutableStateOf(false)
    }
    var isCepError by remember {
        mutableStateOf(false)
    }

    var emailState by remember {
        mutableStateOf("")
    }
    var passState by remember {
        mutableStateOf("")
    }
    val clientName by remember {
        mutableStateOf(TextFieldValue())
    }
    val response = remember {
        mutableStateOf("")
    }
    var nameState by rememberSaveable {
        mutableStateOf("")
    }
    var isNameError by remember {
        mutableStateOf(false)
    }
    var passwordState by rememberSaveable {
        mutableStateOf("")
    }
    var isPasswordError by remember {
        mutableStateOf(false)
    }

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
        NameInput(nameState, isNameError, onNameChange =
        { newName ->
            val lastChar = if (newName.isEmpty()) {
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

        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        //Input Email
        EmailInput(emailState, onEmailChange = { newEmail ->
            emailState = newEmail
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        // Input senha
        PassInput(passwordState, isPasswordError, onPasswordChange = { newPass ->
            val mMaxLength = 24
            isPasswordError = if (newPass.length >= mMaxLength) {
                true
            } else {
                newPass[newPass.length - 1]
                false
            }

            if (isPasswordError) newPass.dropLast(1)

            passwordState = newPass
        })

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
        CpfInput(
            cpfState, isCpfErrorEmpty, isCpfError,
            onCpfChange = { newCpf ->
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
        )

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        //Input cep
        CepInput(
            cepState, isCepError, isCepErrorEmpty,
            onCepChange = { newCep ->
                isCepErrorEmpty = newCep.isEmpty()

                if (cepState.length > 8) newCep.dropLast(1)

                if (cepState.length == 8) {
//                    var cep = ""
//                    buscarCep(cepState) {
//                        cep = it
//                    }.toString()
//
//
//                    if (cep.isEmpty()) {
//                        isCepError = true
//                    } else {
//                        isCepError = false
//                        isCepErrorEmpty = false
//                    }
                }

                cepState = newCep
            },
        )

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )
        //Input birth
        BirthClient(
            birthState, isBirthErrorEmpty,
            onBirthdayChange = { newBirth ->
                isBirthErrorEmpty = newBirth.isEmpty()

                if (birthState.length > 8) newBirth.dropLast(1)

                birthState = newBirth
            },
        )
        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        var gender by remember { mutableStateOf("") }
        //Input Gender
        GenderInputClient(gender, onFemClick = { gender = "F" }, onManClick = { gender = "M" })
        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        val scope = rememberCoroutineScope()

        Button(
            onClick = {


                getCep(cepState) {
                            val cep = it
                            Log.i("teste", cep.toString())

                            val costumer = Costumer(
                                name = nameState,
                                email = emailState,
                                password = passwordState,
                                address = Address(
                                    cep = cep.cep,
                                    addressTypeId = 1,
                                    city = cep.localidade,
                                    uf = cep.uf,
                                    number = 0,
                                    complemento = "",
                                    logradouro = cep.logradouro,
                                    neighborhood = cep.bairro,
                                ),
                                cpf = cpfState,
                                birthday = formatBirthday(birthState),
                                gender = gender[0].toString()
                            )

                            createCostumer(costumer) { _costumer ->
                                auth(credentials = Credentials(costumer.email, costumer.password)) { res ->
                                    if (!res.error) {
                                        val tokenStore = TokenStore(context)
                                        scope.launch {
                                            tokenStore.saveToken(res.token)
                                        }
                                        val uri = Uri.parse(imageUri.value)
                                        val inputStream =
                                            context.contentResolver.openInputStream(uri)

                                        if (inputStream != null) {
                                            val file = File(context.cacheDir, "image.jpg")
                                            val outputStream = FileOutputStream(file)

                                            inputStream.use { input ->
                                                outputStream.use { output ->
                                                    input.copyTo(output)
                                                }
                                            }

                                            val requestBody = RequestBody.create(
                                                "image/*".toMediaTypeOrNull(),
                                                file
                                            )

                                            val imagePart = MultipartBody.Part.createFormData(
                                                "picture",
                                                file.name,
                                                requestBody
                                            )

                                            scope.launch {
                                                addPictureToUser(res.token, imagePart) { it ->
                                                    Log.i("teste", it)
                                                }
                                            }
                                        } else {
                                            Log.e("Error", "Cannot get input stream from URI") }
                                      }
                                    }
                                }
                            }
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
fun NameInput(nameState: String, isNameError: Boolean, onNameChange: (String) -> Unit) {
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
        onValueChange = onNameChange,
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
fun EmailInput(emailState: String, onEmailChange: (String) -> Unit) {

    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.email),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = emailState,
        onValueChange = onEmailChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Unspecified,
            focusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            unfocusedIndicatorColor = colorResource(id = R.color.darkgreen_yvy),
            cursorColor = colorResource(id = R.color.darkgreen_yvy)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(inputsFocusRequest),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )


}


@Composable
fun PassInput(passwordState: String, isPasswordError: Boolean, onPasswordChange: (String) -> Unit) {


    val inputsFocusRequest = FocusRequester()


    Text(
        text = stringResource(id = R.string.password),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = passwordState,
        onValueChange = onPasswordChange,
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

}

@Composable
fun PhotoInput() {
    val painter = rememberImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.adicionar_foto
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
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
                    .padding(start = 5.dp, bottom = 15.dp, top = 12.dp, end = 5.dp)
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
    }

}

@Composable
fun CpfInput(
    cpfState: String,
    isCpfErrorEmpty: Boolean,
    isCpfError: Boolean,
    onCpfChange: (String) -> Unit
) {

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
        onValueChange = onCpfChange,
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
fun CepInput(
    cepState: String,
    isCepErrorEmpty: Boolean,
    isCepError: Boolean,
    onCepChange: (String) -> Unit
) {
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
        onValueChange = onCepChange,
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

@Composable
fun BirthClient(
    birthState: String,
    isBirthErrorEmpty: Boolean,
    onBirthdayChange: (String) -> Unit
) {

    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.titleBirth),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = birthState,
        onValueChange = onBirthdayChange,
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
        isError = isBirthErrorEmpty,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskBirth(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
    if (isBirthErrorEmpty) {
        Text(
            text = stringResource(id = R.string.isBirthErrorEmpty),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun GenderInputClient(selected: String, onFemClick: () -> Unit, onManClick: () -> Unit) {
    Row {
        RadioButton(
            selected = selected == "F",
            onClick = onFemClick,
            colors = RadioButtonDefaults.colors(colorResource(id = R.color.green_yvy))
        )
        Text(
            text = stringResource(id = R.string.gender_f),
            modifier = Modifier
                .clickable(onClick = onFemClick)
                .padding(top = 12.dp, start = 4.dp)
        )
        Spacer(modifier = Modifier.size(60.dp))

        RadioButton(
            selected = selected == "M",
            onClick = onManClick,
            colors = RadioButtonDefaults.colors(colorResource(id = R.color.green_yvy))
        )
        Text(
            text = stringResource(id = R.string.gender_m),
            modifier = Modifier
                .clickable(onClick = onManClick)
                .padding(top = 15.dp)
        )
    }
}


fun formatBirthday(birthday: String): String {
    val year = birthday.takeLast(4)
    val month = (birthday[2].toString() + birthday[3].toString()).toString()
    val day = (birthday[0].toString() + birthday[1].toString()).toString()

    return "$year-$month-$day"
}
@Preview
@Composable
fun InputsPriview() {
    Inputs()
}



