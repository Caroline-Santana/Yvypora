package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.MainActivity
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.*
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.services.datastore.UserStore
import com.example.yvypora.ui.theme.YvyporaTheme

class ProfileMarketer : ComponentActivity() {
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
                        val user = fetchDetails()
                        HeaderProfile(user)
                        JoiningFieldsMarketer()
                    }
                }
            }
        }
    }
}

@Composable
fun JoiningFieldsMarketer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 75.dp)
    )
    {
        EditProfileMarketer()
        Spacer(modifier = Modifier.height(19.dp))
        MyFair()
        Spacer(modifier = Modifier.height(19.dp))
        Gains()
        Spacer(modifier = Modifier.height(19.dp))
        SaleHistory()
        Spacer(modifier = Modifier.height(19.dp))
        Logout()
    }
}

@Composable
fun EditProfileMarketer() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                val intent = Intent(context, EditProfileScreen()::class.java)
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
                    .height(34.dp),
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}

@Composable
fun MyFair() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .clickable {
                val intent = Intent(context, FairsMarketer()::class.java)
                context.startActivity(intent)
            }
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.shop),
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
                text = stringResource(id = R.string.myFair),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp),
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}


@Composable
fun Gains() {
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
            painter = painterResource(id = R.drawable.dollarsquare),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.gains),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp),
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}

@Composable
fun SaleHistory() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp)
            .clickable {
                val intent = Intent(context, SalesHistory()::class.java)
                context.startActivity(intent)
            },

        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.bag),
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
                text = stringResource(id = R.string.sales_history),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp),
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
            val intent = Intent(context, MainActivity::class.java)

            UserStore(context).saveDetails("")
            TokenStore(context).saveToken("")

            context.startActivity(intent)
        }
    }
}