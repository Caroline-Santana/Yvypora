package com.example.yvypora


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.yvypora.ui.theme.YvyporaTheme

class ScreenSearch : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

//@Composable
//fun OtherSearch() {
//    var searchState by remember {
//        mutableStateOf("")
//    }
//    val context = LocalContext.current
//    val userPreferences = UserSearches(context)
//
//    Column( modifier = Modifier
//        .padding(top = 10.dp, start = 20.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.arrow),
//            modifier = Modifier
//                .height(45.dp)
//                .width(50.dp)
//                .clickable {
//                    val intent = Intent(context, InicialScreen()::class.java)
//                    context.startActivity(intent)
//                },
//            alignment = Alignment.BottomStart,
//            contentDescription = stringResource(id = R.string.back_screen)
//        )
//        Row(modifier = Modifier.padding(top = 10.dp)) {
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp)
//                    .padding(start = 25.dp, end = 25.dp),
//                value = searchState,
//                onValueChange = { newText ->
//                    searchState = newText
//                },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = androidx.compose.ui.text.input.ImeAction.Search
//                ),
//                keyboardActions = KeyboardActions(
//                    onSearch = {
//                        userPreferences.searchHistory += searchState
//                    }
//                ),
//                colors = TextFieldDefaults.textFieldColors(
//                    textColor = Color.White,
//                    backgroundColor = colorResource(id = R.color.green_yvy),
//                    focusedIndicatorColor = colorResource(id = R.color.green_yvy),
//                    unfocusedIndicatorColor = colorResource(id = R.color.green_yvy),
//                    cursorColor = Color.White
//                ),
//                shape = RoundedCornerShape(20.dp),
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(R.drawable.lupa_icon),
//                        contentDescription = stringResource(id = R.string.lupa),
//                        modifier = Modifier
//                            .width(35.dp)
//                            .height(35.dp)
//                            .padding(end = 10.dp),
//                        tint = Color.White
//                    )
//                }
//            )
//        }
//        Column (modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxSize()) {
//            Text(
//                text = stringResource(id = R.string.latest_search),
//                modifier = Modifier
//                    .padding(top = 25.dp, start = 10.dp, bottom = 44.dp)
//                    .fillMaxWidth(),
//                fontSize = 30.sp,
//                color = colorResource(id = R.color.darkgreen_yvy)
//            )
//            for (search in userPreferences.searchHistory) {
//                Row(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(55.dp)
//                    .padding(start = 22.dp, end = 22.dp, bottom = 14.dp)
//                    .background(colorResource(id = R.color.light_gray), RoundedCornerShape(5.dp)),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = search,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = colorResource(id = R.color.dark_gray),
//                        modifier = Modifier.padding(start = 14.dp)
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.delete_search),
//                        modifier = Modifier
//                            .padding(end = 10.dp)
//                            .height(38.dp)
//                            .width(28.dp),
//                        contentDescription = stringResource(id = R.string.delete_search))
//                }
//
//            }
//        }
//    }
//
//
//
//
//}

@Preview(showBackground = true)
@Composable
fun SearchScreen() {
    YvyporaTheme {

    }
}