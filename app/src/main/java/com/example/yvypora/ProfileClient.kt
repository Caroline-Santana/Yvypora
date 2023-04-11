package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yvypora.navbar.ItemsMenu
import com.example.yvypora.navbar.NavigationHost
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlinx.coroutines.CoroutineScope
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
                        HeaderProfile()
                        JoiningFields()
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderProfile() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, top = 30.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_user),
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
                text = "Carlos Arcanjo",
                modifier = Modifier.padding(bottom = 3.dp),
                fontSize = 20.sp

            )
            Text(
                text = "carlaoprof@gmail.com"
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
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
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
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.exportsquare),
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
                text = stringResource(id = R.string.logout),
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
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
            HeaderProfile()
            JoiningFields()
        }
    }
}
