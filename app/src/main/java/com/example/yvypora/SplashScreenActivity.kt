package com.example.yvypora

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.yvypora.MarketerScreens.InicialMarketerActivity
import com.example.yvypora.ScreenClients.InicialScreen
import com.example.yvypora.animatedsplashscreendemo.navigation.SetupNavGraph
import com.example.yvypora.models.User
import com.example.yvypora.models.dto.TypeOfUser
import com.example.yvypora.service.datastore.TokenStore
import com.example.yvypora.service.datastore.UserStore


import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)


            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    var alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3500)
        navController.popBackStack()

    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = TokenStore(context)

    scope.launch {
        store.getToken.collect { it ->
            if (it.isNotEmpty()) {
                val userStore = UserStore(context)
                userStore.getDetails.collect {_user ->
                    try {
                        val gson = Gson()
                        val user = gson.fromJson(_user.toString(), User::class.java)

                        if (user?.typeOf === TypeOfUser.COSTUMER) {
                            val intent = Intent(context, InicialScreen::class.java)
                            context.startActivity(intent)
                        }
                        if (user?.typeOf === TypeOfUser.MARKETER) {
                            val intent = Intent(context, InicialMarketerActivity::class.java)
                            context.startActivity(intent)
                        }
                    } catch (err: Error) {
                        err.printStackTrace()
                    }
                }
            }
        }
    }
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
    Splash(alpha = alphaAnim.value)
}




@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            "",
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .alpha(alpha = alpha)
        )
    }

}


