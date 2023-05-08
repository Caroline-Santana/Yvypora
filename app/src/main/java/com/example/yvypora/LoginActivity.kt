package com.example.yvypora

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.MarketerScreens.InicialMarketerActivity
import com.example.yvypora.ScreenClients.InicialScreen
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.api.commons.auth
import com.example.yvypora.api.commons.getDetailsOfUser
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.Token
import com.example.yvypora.models.dto.TypeOfUser
import com.example.yvypora.service.datastore.TokenStore
import com.example.yvypora.service.datastore.UserStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import kotlinx.coroutines.launch

//import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val auth = FirebaseAuth.getInstance()
//        if(auth.currentUser != null){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity((intent))
//        }
//        Log.i("ds3m", if(auth.currentUser == null) "Não tem usuário" else "")
        setContent {
            YvyporaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background2),
                        alignment = Alignment.TopStart,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                }

                }
                LoginLayout()
                }

            }
        }


@Composable
fun LoginLayout() {
    val context = LocalContext.current
    var passState by rememberSaveable {
        mutableStateOf("")
    }
    var token by rememberSaveable {
        mutableStateOf("")
    }
    var emailState by rememberSaveable {
        mutableStateOf("")
    }

    var isEmailError by remember {
        mutableStateOf(false)
    }
    var isPasswordErrorEmpty by remember {
        mutableStateOf(false)
    }

    val mMaxLength = 8
    //Objeto que controla a requisição de foco
    val inputsFocusRequest = FocusRequester()
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
//    fun isEmailValid(email: String): Boolean {
//        return EMAIL_REGEX.toRegex().matches(email);
//    }
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 24.dp, top = 320.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,

        ) {
//        Text(
//            text = stringResource(id = R.string.name),
//            modifier = Modifier.padding(bottom = 6.dp),
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Medium,
//            textAlign = TextAlign.Start,
//            color = colorResource(id = R.color.darkgreen_yvy)
//        )
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
//                } else if (isEmailValid(newEmail) == false) {
//                    isEmailError = true
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
                .focusRequester(inputsFocusRequest),

            trailingIcon = {
                if (passState.length <= 0) {
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
            modifier = Modifier.height(18.dp)
        )
        Text(
            text = stringResource(id = R.string.password),
            modifier = Modifier.padding(bottom = 6.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        OutlinedTextField(
            value = passState,
            onValueChange = { newPass ->
                if (newPass.isEmpty()) {
                    isPasswordErrorEmpty = true
                }
                passState = newPass
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedBorderColor = colorResource(id = R.color.transparentgreen_yvy),
                unfocusedBorderColor = colorResource(id = R.color.transparentgreen_yvy)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(inputsFocusRequest),

            trailingIcon = {
                if (passState.length <= 0) {
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

            isError = isPasswordErrorEmpty,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),

            )

        if (isPasswordErrorEmpty) {
            Text(
                text = stringResource(id = R.string.message_error_pass1),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(
            modifier = Modifier.height(44.dp)
        )

        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                val credentials = Credentials(emailState, passState)
                auth(credentials) {
                    if (it.token.isNotEmpty()) {
                        scope.launch {
                            val dataStore = TokenStore(context)

                            dataStore.saveToken(it.token)

                            scope.launch {
                                getDetailsOfUser(it.token) { user ->
                                    val userStore = UserStore(context)
                                    scope.launch {
                                        val gson = Gson()
                                        userStore.saveDetails(gson.toJson(user))
                                        if (user.typeOf == TypeOfUser.COSTUMER) {
                                            val intent = Intent(context, InicialScreen()::class.java)
                                            context.startActivity(intent)
                                        }
                                        if (user.typeOf == TypeOfUser.MARKETER) {
                                            val intent = Intent(context, InicialMarketerActivity()::class.java)
                                            context.startActivity(intent)
                                        }

                                    }
                                }
                            }

                        }
                        // OPEN NEXT Activity
//                        val intent = Intent(context, InicialScreen()::class.java)
//                        context.startActivity(intent)
                    }
                    else {
                        Toast.makeText(context, "Nao foi possivel fazer Login!", Toast.LENGTH_SHORT).show()
                    }
                }


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


