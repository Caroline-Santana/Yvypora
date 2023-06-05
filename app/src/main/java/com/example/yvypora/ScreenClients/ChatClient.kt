package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.domain.models.Deliveryman
import com.example.yvypora.domain.models.MessageFromSocket
import com.example.yvypora.domain.models.User
import com.example.yvypora.services.datastore.DeliverymanStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class ChatClient : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    val user = fetchDetails();

                    val deliverymanAsString =
                        DeliverymanStore(context).getDeliverymanDetails.collectAsState(initial = "").value

                    val deliveryman: Deliveryman =
                        Gson().fromJson(deliverymanAsString, Deliveryman::class.java)

                    MainChatClient(user = user, deliveryman)
                }
            }
        }
    }

    private val BotChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)
    private val AuthorChatBubbleShape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp)

    val message = mutableStateOf("")
    val chatMessages = mutableStateListOf<com.example.yvypora.models.Message>()

    @Composable
    fun MainChatClient(user: User, deliveryman: Deliveryman) {
        val image = rememberAsyncImagePainter(deliveryman.picture_uri)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBarSection(
                username = deliveryman.name,
                profile = image,
                isOnline = true
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_no_name_transparent),
                    modifier = Modifier.height(239.dp),
                    contentDescription = null
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    ChatSection(Modifier.weight(1f))
                    MessageSection()
                }
            }

        }
    }

    @Composable
    fun TopBarSection(username: String, profile: ImagePainter, isOnline: Boolean) {
        val context = LocalContext.current
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(131.dp),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 25.dp,
                bottomStart = 25.dp
            ),
            backgroundColor = colorResource(id = R.color.green_width),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    modifier = Modifier
                        .height(45.dp)
                        .width(46.dp)
                        .clickable {
//                        val intent = Intent(context, ProfileClient()::class.java)
//                        context.startActivity(intent)
                        },
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.back_screen)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = profile,
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape),

                    )

                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = username,
                        fontSize = 20.sp
                    )
                    Row() {
                        Text(
                            text = if (isOnline) "Online" else "Ofline",
                            fontSize = 20.sp
                        )
                    }

                }
            }
        }
    }

    @Composable
    fun parseSocketToMessage(message: MessageFromSocket): com.example.yvypora.models.Message {
        return com.example.yvypora.models.Message(
            text = message.content,
            recipient_id = message.toName,
        )
    }

    @Composable
    fun sendMessageToSocket(message: com.example.yvypora.models.Message) {
        LaunchedEffect(message) {

        }
    }

    @Composable
    fun appendMessageToSocket() {

    }

    @Composable
    fun listAllMessages() {

    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun MessageSection() {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            OutlinedTextField(
                placeholder = {
                    Text(text = "Message...")
                },
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = colorResource(id = R.color.green_width),
                    unfocusedIndicatorColor = colorResource(id = R.color.green_width),
                    cursorColor = colorResource(id = R.color.green_width)
                ),
                shape = RoundedCornerShape(25.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = null,
                        tint = colorResource(id = R.color.green_width),
                        modifier = Modifier.clickable {}
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            )
        }
    }

    @Composable
    fun ChatSection(modifier: Modifier = Modifier) {
        val simpleDateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        LazyColumn(
            modifier = Modifier
//        .fillMaxSize()
                .padding(16.dp),
            reverseLayout = true,
            verticalArrangement = Arrangement.Bottom
        ) {
            items(chatMessages) { chat ->
                MessageItem(
                    chat.text,
                    isOut = chat.isOut,
                    time = simpleDateFormat.format(chat.time)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun MessageItem(message: String?, isOut: Boolean, time: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
        )
        {
            if (message != null) {
                Box(
                    modifier = Modifier
                        .background(
                            if (isOut)
                                colorResource(id = R.color.green_width)
                            else
                                Color.White,
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .border(
                            if (isOut)
                                BorderStroke(2.dp, colorResource(id = R.color.green_width))
                            else
                                BorderStroke(2.dp, colorResource(id = R.color.green_button)),
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape

                        )
                        .padding(
                            top = 8.dp,
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = message,
                        color = if (isOut) Color.White else colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
        Text(
            text = time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }


//    @Preview(showBackground = true)
//    @Composable
//    fun ChatClientPreview() {
//        YvyporaTheme {
//            val user = fetchDetails();
//            MainChatClient(user = user)
//        }
//    }

}


//
//@Composable
//fun HomeView(
//    homeViewModel: HomeViewModel = viewModel()
//) {
//    val message: String by homeViewModel.message.observeAsState(initial = "")
//    val messages: List<Map<String, Any>> by homeViewModel.messages.observeAsState(
//        initial = emptyList<Map<String, Any>>().toMutableList()
//    )
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Bottom
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(weight = 0.85f, fill = true),
//            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(4.dp),
//            reverseLayout = true
//        ) {
//            items(messages) { message ->
//                val isCurrentUser = message[Constants.IS_CURRENT_USER] as Boolean
//
//                SingleMessage(
//                    message = message[Constants.MESSAGE].toString(),
//                    isCurrentUser = isCurrentUser
//                )
//            }
//        }
//        OutlinedTextField(
//            value = message,
//            onValueChange = {
//                homeViewModel.updateMessage(it)
//            },
//            label = {
//                Text(
//                    "Type Your Message"
//                )
//            },
//            maxLines = 1,
//            modifier = Modifier
//                .padding(horizontal = 15.dp, vertical = 1.dp)
//                .fillMaxWidth()
//                .weight(weight = 0.09f, fill = true),
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text
//            ),
//            singleLine = true,
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        homeViewModel.addMessage()
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Send,
//                        contentDescription = "Send Button"
//                    )
//                }
//            }
//        )
//    }
//}