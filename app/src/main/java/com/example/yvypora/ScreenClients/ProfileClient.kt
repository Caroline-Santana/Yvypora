package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.yvypora.MainActivity
import com.example.yvypora.R
import com.example.yvypora.domain.models.User
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.services.datastore.UserStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import kotlinx.coroutines.launch

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class ProfileClient : ComponentActivity() {
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
                        val user = fetchDetails();
                        HeaderProfile(user)
                        JoiningFields()
                    }
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun fetchDetails(): User {
    val context = LocalContext.current
    val repository = UserStore(context)
    var userParsed by remember { mutableStateOf<User?>(null) }
    val scope = rememberCoroutineScope()

    scope.launch {
        repository.getDetails.collect {_user ->
            val gson = Gson()
            val parsed = gson.fromJson(_user, User::class.java)
            userParsed = parsed
        }
    }

    Log.i("teste", userParsed.toString())

    return userParsed ?: User()
}
@Composable
fun HeaderProfile(user: User) {
    val image = rememberAsyncImagePainter(user.picture_uri)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, top = 30.dp)

    ) {
        Image(
            painter = image,
            modifier = Modifier
                .height(66.dp)
                .width(75.dp),
            contentDescription = stringResource(id = R.string.photo_profile),

            )
        Column(
            modifier = Modifier
                .padding(start = 87.dp)
        ) {
            Text(
                text = user.name ?: "",
                modifier = Modifier.padding(bottom = 3.dp),
                fontSize = 20.sp

            )
            Text(
                text = user.email ?: ""
            )
        }

    }
}
@Composable
fun JoiningFields(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 75.dp)
    )
    {
        EditProfile()
        Spacer(modifier = Modifier.height(19.dp))
        Address()
        Spacer(modifier = Modifier.height(19.dp))
        PaymentMethods()
        Spacer(modifier = Modifier.height(19.dp))
        PurchaseHistory()
        Spacer(modifier = Modifier.height(19.dp))
        Logout()
    }
}

@Composable
fun EditProfile(){
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                val intent = Intent(context, EditProfileSreen()::class.java)
                context.startActivity(intent)
            }
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.user),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
            ,
            tint = colorResource(id = R.color.darkgreen_yvy),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.edit_account),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}

@Composable
fun Address(){
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .clickable {
                val intent = Intent(context, AdressesActivity()::class.java)
                context.startActivity(intent)
            }
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.map),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
            ,
            tint = colorResource(id = R.color.darkgreen_yvy),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.address),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}

@Composable
fun PaymentMethods() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                val intent = Intent(context, PaymentMethodsActivity()::class.java)
                context.startActivity(intent)
            }
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.card),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
            ,
            tint = colorResource(id = R.color.darkgreen_yvy),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.payment_methods),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}
@Composable
fun PurchaseHistory() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp)
            .clickable {
                val intent = Intent(context, BuyHistory()::class.java)
                context.startActivity(intent)
            },

        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.bag),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
            ,
            tint = colorResource(id = R.color.darkgreen_yvy),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.purchase_history),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}
@Composable
fun Logout() {
    var abrirDialog by remember { mutableStateOf(false) }
    var eraseData by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable { abrirDialog = true }
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.exportsquare),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            tint = colorResource(id = R.color.darkgreen_yvy),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.logout),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
        }

    }
    if (abrirDialog) {
        AlertDialog(
            onDismissRequest = { abrirDialog = false },
            title = {
                Text(
                    text = stringResource(id = R.string.logout2),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.darkgreen_yvy),
                    textAlign = TextAlign.Center

                )
            },
            backgroundColor = colorResource(id = R.color.green_camps),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 15.dp,
                bottomEnd = 0.dp,
                bottomStart = 15.dp
            ),
            text = {
                Text(
                    text = stringResource(id = R.string.logout_text),
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.darkgreen_yvy),
                    textAlign = TextAlign.Center
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            abrirDialog = false

                            eraseData = true;

                                  },
                        modifier = Modifier.width(80.dp)
                    )
                    {
                        Text(
                            text = "Sim",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    Button(
                        onClick = { abrirDialog = false },
                        modifier = Modifier.width(80.dp)

                        )
                    {
                        Text(
                            text = "Não",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                }
            }

        )

    }

    if (eraseData) {
        LaunchedEffect(Unit) {
            UserStore(context).saveDetails("")
            TokenStore(context).saveToken("")
            val intent = Intent(context, MainActivity::class.java)

            context.startActivity(intent)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileCliente() {
    YvyporaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val user = fetchDetails();
            HeaderProfile(user)
            JoiningFields()
        }
    }
}
