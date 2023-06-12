package com.example.yvypora.composables

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.ProfileClient
import com.example.yvypora.domain.models.User
import com.example.yvypora.services.datastore.UserStore
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoilApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Header() {
    val context = LocalContext.current
    var scope = rememberCoroutineScope()
    val store = UserStore(context)
    var userParsed by remember { mutableStateOf<User?>(null) }
    scope.launch {
        store.getDetails.collect { _user ->
            val gson = Gson()
            val parsed = gson.fromJson(_user, User::class.java)
            userParsed = parsed
        }
    }

    val profileImage = rememberImagePainter(data = userParsed?.picture_uri ?: "")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 25.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_no_name),
            modifier = Modifier
                .height(55.dp)
                .width(55.dp),
            alignment = Alignment.BottomStart,
            contentDescription = "logo",

            )
        Image(
            painter = profileImage,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProfileClient()::class.java)
                    context.startActivity(intent)
                }
                .height(50.dp)
                .width(55.dp),
            contentDescription = "logo",
        )
    }
}
