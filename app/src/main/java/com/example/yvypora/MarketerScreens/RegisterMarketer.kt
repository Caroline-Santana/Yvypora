package com.example.yvypora.MarketerScreens

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
import androidx.compose.ui.unit.Dp
import androidx.datastore.preferences.protobuf.Empty
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.api.commons.addPictureToUser
import com.example.yvypora.api.commons.auth
import com.example.yvypora.api.commons.createMarketer
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.dto.Location
import com.example.yvypora.models.marketer.Marketer
import com.example.yvypora.service.datastore.TokenStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

//import androidx.compose.ui.platform.ContextAmbient

class RegisterMarketer : ComponentActivity() {
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
            InputsMarketer()
        }
    }
}

@Composable
fun InputsMarketer() {
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
        var tentNameState by rememberSaveable {
            mutableStateOf("")
        }

        var nameState by rememberSaveable {
            mutableStateOf("")
        }

        var isTentNameError by remember {
            mutableStateOf(false)
        }

        var isNameError by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()



        NameInputMarketer(tentNameState, isTentNameError,{ newName ->
            var lastChar = if (newName.isEmpty()) {
                isTentNameError = true
                newName

            } else {
                newName.get(newName.length - 1)
                isTentNameError = false
            }
            var newValue = if (lastChar == '.' || lastChar == ',')
                newName.dropLast(1)
            else newName
            tentNameState = newValue
        })


        Spacer(
            modifier = Modifier.height(35.dp)
        )

        NameInputMarketer(nameState, isNameError, { newName ->
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
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        var emailState by rememberSaveable {
            mutableStateOf("")
        }

        var isEmailError by remember {
            mutableStateOf(false)
        }

        //Input Email
        EmailInputMarketer(emailState, isEmailError, { newEmail ->
            if (newEmail.isEmpty()) {
                isEmailError = true
            } else if (isEmailValid(newEmail) == false) {
                isEmailError = true
            } else {
                newEmail.get(newEmail.length - 1)
                isEmailError = false
            }

            emailState = newEmail
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )


        var passwordState by rememberSaveable {
            mutableStateOf("")
        }


        var isPasswordError by remember {
            mutableStateOf(false)
        }
        var isPasswordErrorEmpty by remember {
            mutableStateOf(false)
        }


        // Input senha
        PassInputMarketer(passwordState, isPasswordError, isPasswordErrorEmpty, { newPass ->
            val mMaxLength = 8
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
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )
        //Input photo
        PhotoInputMarketer()

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        var cpfState by rememberSaveable {
            mutableStateOf("")
        }
        var isCpfErrorEmpty by remember {
            mutableStateOf(false)
        }
        var isCpfError by remember {
            mutableStateOf(false)
        }

        //Input cpf
        CpfInputMarketer(cpfState, isCpfErrorEmpty, isCpfError, { newCpf ->
            isCpfErrorEmpty = newCpf.isEmpty()
            if (cpfState.length > 11) newCpf.dropLast(1)

            if (!ValidationCpf.myValidateCPF(newCpf)) {
                isCpfError = true
            } else {
                isCpfError = false
                isCpfErrorEmpty = false
            }

            cpfState = newCpf
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )


        var phoneState by rememberSaveable {
            mutableStateOf("")
        }
        var isPhoneErrorEmpty by rememberSaveable {
            mutableStateOf(false)
        }

        //Input telefone
        PhoneInputMarketer(phoneState, isPhoneErrorEmpty, onValueChange = { newPhone ->
            isPhoneErrorEmpty = newPhone.isEmpty()

            if (phoneState.length > 10) newPhone.dropLast(1)

            phoneState = newPhone
        })

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        //Input birth
        var birthState by rememberSaveable {
            mutableStateOf("")
        }
        var isBirthErrorEmpty by remember {
            mutableStateOf(false)
        }

        BirthMarketer(birthState, isBirthErrorEmpty,{ newBirth ->
            isBirthErrorEmpty = newBirth.isEmpty()

            if (birthState.length > 8) newBirth.dropLast(1)
            birthState = newBirth
        },)

        //*********************************************************************
        Spacer(
            modifier = Modifier.height(35.dp)
        )

        var gender by remember { mutableStateOf("") }
        //Input genero
        GenderInputMarketer(gender, onFemClick = { gender = "F" }, onManClick = { gender = "M" })

        Button(
            onClick = {
                      val marketer = Marketer(
                          name = nameState,
                          birthday = formatBirthday(birthState),
                          gender = gender[0].toString(),
                          cpf = cpfState,
                          phone = phoneState,
                          email = emailState,
                          tent_name = tentNameState,
                          password = passwordState,
                          location = Location(
                              latitude = 0.0,
                              longitude = 0.0
                          ),
                      )
                scope.launch {
                    createMarketer(marketer) {it ->
                        if (it.isNullOrEmpty()) {
                            Log.i("teste", "Erro no Cadastro")
                        } else {
                            scope.launch {
                                auth(credentials = Credentials(marketer.email, marketer.password)) { res ->
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
fun NameInputMarketer(nameState: String, isNameError: Boolean, onValueChange: (String) -> Unit) {

    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.name_tent),
        modifier = Modifier.padding(top = 5.dp),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = nameState,
        onValueChange = onValueChange,
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
fun EmailInputMarketer(emailState: String, isEmailError: Boolean, onValueChange: (String) -> Unit) {
    val inputsFocusRequest = FocusRequester()

    Text(
        text = stringResource(id = R.string.email),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = emailState,
        onValueChange = onValueChange,
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
fun PassInputMarketer(passwordState: String, isPasswordError: Boolean, isPasswordErrorEmpty: Boolean, onValueChange: (String) -> Unit) {


    val inputsFocusRequest = FocusRequester()


    Text(
        text = stringResource(id = R.string.password),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = passwordState,
        onValueChange = onValueChange,
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

var imageUri =  mutableStateOf("")

@Composable
fun PhotoInputMarketer() {
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
                    .padding(start = 5.dp, bottom = 15.dp, top = 12.dp, end = 5.dp)
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
    }

}

@Composable
fun CpfInputMarketer(cpfState: String, isCpfErrorEmpty: Boolean, isCpfError: Boolean, onValueChange: (String) -> Unit) {

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
        onValueChange = onValueChange,
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
fun PhoneInputMarketer(phoneState: String, isPhoneErrorEmpty: Boolean, onValueChange: (String) -> Unit) {
    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.title_phone),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )

    TextField(
        value = phoneState,
        onValueChange = onValueChange,
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
        isError = isPhoneErrorEmpty,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        visualTransformation = MaskPhone(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
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
fun CnpjInputMarketer() {

    var cnpjState by rememberSaveable {
        mutableStateOf("")
    }
    var isCnpjErrorEmpty by remember {
        mutableStateOf(false)
    }

    var isCnpjError by remember {
        mutableStateOf(false)
    }

    val inputsFocusRequest = FocusRequester()

    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.title_cnpj),
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    TextField(
        value = cnpjState,
        onValueChange = { newCnpj ->
            isCnpjError = newCnpj.isEmpty()

            if (cnpjState.length > 11) newCnpj.dropLast(1)

//
//            if (//!ValidationCpf.myValidateCPF(
//                    newCnpj
//            )) {
//                isCnpjError = true
//            } else {
//                isCnpjError = false
//                isCnpjErrorEmpty = false
//            }


            cnpjState = newCnpj
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
        isError = isCnpjErrorEmpty,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskCnpj(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
    if (isCnpjErrorEmpty) {
        Text(
            text = stringResource(id = R.string.cnpj_error_empty),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    } else if (isCnpjError) {
        Text(
            text = stringResource(id = R.string.cnpj_error_invalid),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            textAlign = TextAlign.End
        )
    }
}
@Composable
fun BirthMarketer(birthState: String, isBirthErrorEmpty: Boolean, onValueChange: (String) -> Unit) {

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
        onValueChange = onValueChange,
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
fun GenderInputMarketer(selected: String, onFemClick: () -> Unit, onManClick: () -> Unit){
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



