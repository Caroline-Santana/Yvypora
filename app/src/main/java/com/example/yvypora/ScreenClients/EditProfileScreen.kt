package com.example.yvypora.ScreenClients

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.MarketerScreens.imageUri
import com.example.yvypora.R
import com.example.yvypora.api.commons.addPictureToUser
import com.example.yvypora.api.commons.updateCostumerAccount
import com.example.yvypora.domain.models.EditProfile
import com.example.yvypora.domain.models.User
import com.example.yvypora.domain.dto.CostumerUpdateAccountBody
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.services.datastore.UserStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.MaskCep
import com.example.yvypora.utils.MaskCpf
import com.example.yvypora.utils.ValidationCpf
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import kotlin.reflect.full.memberProperties

class EditProfileScreen : ComponentActivity() {
    private var newData = mutableStateOf(EditProfile())
    private var newProfilePicture = mutableStateOf<String?>(null)
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
                        val context = LocalContext.current

                        val user = fetchDetails()

                        Log.i("teste", user.toString())

                        val dataEditUser = EditProfile(
                            name = user.name,
                            email = user.email,
                            password = "",
                            cpf = user.cpf ?: "",
                        )

                        InputsProfile(dataEditUser)
                    }

                }
            }
        }
    }


    @Composable
    fun InputsProfile(dataEditUser: EditProfile) {
        val context = LocalContext.current
        var update by remember { mutableStateOf(false) }
        val user: User = fetchDetails()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 23.dp, end = 23.dp, top = 20.dp),
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
                            val intent = Intent(context, ProfileClient()::class.java)
                            context.startActivity(intent)
                        },
                    alignment = Alignment.BottomStart,
                    contentDescription = stringResource(id = R.string.back_screen)
                )
                Text(
                    text = stringResource(id = R.string.edit_account),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.darkgreen_yvy),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.padding(top = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                NameInputAgain(user = dataEditUser)
                Spacer(modifier = Modifier.height(19.dp))
                EmailInputAgain(user = dataEditUser)
                Spacer(modifier = Modifier.height(19.dp))
                PassInputAgain(user = dataEditUser)
                Spacer(modifier = Modifier.height(19.dp))

                PhotoInput() {
                    newProfilePicture.value = it
                }

                Spacer(modifier = Modifier.height(19.dp))
                CpfInputAgain(user = dataEditUser)
                Spacer(modifier = Modifier.height(19.dp))
                CepInputAgain(user = dataEditUser)
                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                        update = true
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
        var updateToken by remember { mutableStateOf(false) }
        var token by remember { mutableStateOf("") }
        val previewToken = TokenStore(context).getToken.collectAsState(initial = "").value

        if (update) {
            LaunchedEffect(Unit) {
                val body = CostumerUpdateAccountBody(
                    name = newData.value.name,
                    email = newData.value.email,
                    cpf = newData.value.cpf,
                    password = newData.value.password,
                )

                if (!newProfilePicture.value.isNullOrEmpty()) {
                    val uri = Uri.parse(newProfilePicture.value)
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

                        addPictureToUser(previewToken, imagePart) {
                            Log.i("update", it)
                        }
                    }

                    updateCostumerAccount(user.id!!, body) {
                        if (it != null) {
                            token = it.data.newToken
                            updateToken = true
                        }
                    }
                }
            }
            if (updateToken) {
                LaunchedEffect(token) {
                    TokenStore(context).saveToken(token) // save new token
                }
            }
        }
    }

    @Composable
    fun NameInputAgain(user: EditProfile) {
        var nameState by rememberSaveable {
            mutableStateOf(user.name.toString())
        }
        var nome = user.name.toString()
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

                newData.value.name = newValue
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
    fun EmailInputAgain(user: EditProfile) {
        var emailState by rememberSaveable {
            mutableStateOf(user.email.toString())
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
                newData.value.email = newEmail
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
    fun PassInputAgain(user: EditProfile) {
        var passwordState by rememberSaveable {
            mutableStateOf(user.password.toString())
        }

        var isPasswordError by remember {
            mutableStateOf(false)
        }
        var isPasswordErrorEmpty by remember {
            mutableStateOf(false)
        }
        var passwordVisibility by rememberSaveable {
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
                newData.value.password = newPass
                passwordState = newPass
            },
            trailingIcon = {
                val img = if (passwordVisibility) {
                    Icons.Filled.Visibility
                } else Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = img,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
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
    fun CpfInputAgain(user: EditProfile) {
        var cpfState by rememberSaveable {
            mutableStateOf(user.cpf.toString())
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

                newData.value.cpf = newCpf
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
    fun CepInputAgain(user: EditProfile) {

        var cepState by rememberSaveable {
            mutableStateOf(user.cep.toString())
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
            onValueChange = { newCep ->

                if (cepState.length > 11) newCep.dropLast(1)

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
            visualTransformation = MaskCep(),
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
//                InputsProfile()
            }
        }
    }
}
