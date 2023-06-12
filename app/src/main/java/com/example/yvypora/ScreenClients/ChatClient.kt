package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Message
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.yvypora.api.norelational.NoRelationalService
import com.example.yvypora.domain.models.MessageFromSocket
import com.example.yvypora.domain.models.MessageReceivedFromSocket
import com.example.yvypora.domain.models.Order
import com.example.yvypora.domain.models.User
import com.example.yvypora.domain.models._Deliveryman
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.utils.getSocket
import com.google.gson.Gson
import io.socket.client.Socket
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class ChatClient : ComponentActivity() {

    private lateinit var user: User;
    private lateinit var deliveryman: _Deliveryman

    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val socket = getSocket()

                    val context = LocalContext.current
                    val intent = (context as ChatClient).intent
                    val _user = fetchDetails();

                    user = _user

                    val order : Order = Gson().fromJson(intent.getStringExtra("order"), Order::class.java)

                    deliveryman = order.orderDetails.deliveryman

                    MainChatClient(user = user, deliveryman, socket)
                }
            }
        }
    }

    private val BotChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)
    private val AuthorChatBubbleShape = RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp)


    @Composable
    fun MainChatClient(user: User, deliveryman: _Deliveryman, socket: Socket) {
        val image = rememberAsyncImagePainter(deliveryman.pictureUri)
        var chatMessages = remember { mutableStateListOf<com.example.yvypora.models.Message>() }

//        LaunchedEffect(Unit) {
//            NoRelationalService.listChat(user.id!!, deliveryman.id) {
//                val messages = it.data
//                Log.i("chat", "message $messages")
//                messages!!.forEach { message ->
//                    Log.i("chat", messages.toString())
//                    val isOut = message.senderName == user.name
//                    chatMessages.add(com.example.yvypora.models.Message(
//                        text = message.content,
//                        isOut = isOut,
//                        recipient_id = message.senderId.toString(),
//                    ))
//                }
//            }
//        }

        // Listener to changes
        appendMessageToSocket(socket, chatMessages)


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
                    ChatSection(Modifier.weight(1f), chatMessages)
                    MessageSection(socket, chatMessages)
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
        val isOut = message.senderName !== user.name
        return com.example.yvypora.models.Message(
            text = message.content,
            recipient_id = message.senderId.toString() ?: "0",
            isOut = isOut
        )
    }

    @Composable
    fun parseMessageToSocket(message: com.example.yvypora.models.Message ): MessageFromSocket {
        return MessageFromSocket(
            fromName = user.name!!,
            fromId = user.id!!,
            from = user.id!!,
            content = message.text!!,
            toId = deliveryman.id,
            to = deliveryman.id,
            toName = deliveryman.name
        )
    }


    @Composable
    fun sendMessageToSocket(socket: Socket, message: com.example.yvypora.models.Message, chatMessages: MutableList<com.example.yvypora.models.Message>) {
        chatMessages.add(0, message)
        val messageToSocket = parseMessageToSocket(message = message)
        LaunchedEffect(socket) {
            Log.i("chat", message.toString())
            Log.i("chat", gson.toJson(messageToSocket))
            socket.emit("send_message", gson.toJson(messageToSocket).toString())
        }
    }

    @Composable
    fun appendMessageToSocket(socket: Socket, chatMessages: MutableList<com.example.yvypora.models.Message>) {
        var addMessageToList by remember { mutableStateOf(false)}
        var newMessage by remember { mutableStateOf<MessageReceivedFromSocket?>(null)}
        LaunchedEffect(socket) {
            socket.on("chat_message") {args ->
                val message = args[0].toString()
                newMessage = gson.fromJson(message, MessageReceivedFromSocket::class.java)
                addMessageToList = true
            }
        }
        if (addMessageToList) {
            chatMessages.add(0, com.example.yvypora.models.Message(
                text = newMessage!!.content,
                isOut = false,
                recipient_id = newMessage!!.from.toString()
            ))
            addMessageToList = false
        }

    }

    @Composable
    fun listAllMessages(socket: Socket) {
        LaunchedEffect(socket) {
            // do the req in retrofit
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun MessageSection(socket: Socket, chatMessages: MutableList<com.example.yvypora.models.Message>) {
        var message by remember { mutableStateOf("") }
        var send by remember { mutableStateOf<Boolean>(false) }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            OutlinedTextField(
                placeholder = {
                    Text(text = "Message...")
                },
                value = message,
                onValueChange = {
                    message = it
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
                        modifier = Modifier.clickable {
                            send = true
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            )
        }
        if (send) {
            sendMessageToSocket(socket = socket, message = com.example.yvypora.models.Message(
                text = message,
                user.id.toString(),
                isOut = true,
            ), chatMessages)
            send = false
        }
    }

    @Composable
    fun ChatSection(modifier: Modifier = Modifier, chatMessages: MutableList<com.example.yvypora.models.Message>) {
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