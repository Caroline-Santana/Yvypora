package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.animatedsplashscreendemo.navigation.Screen
import com.example.yvypora.services.websocket.Websocket
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.socket.client.Socket
import org.json.JSONObject

class StatusOrder : ComponentActivity() {
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

                    StatusOrderMain(socket)
                }
            }
        }
    }

    @Composable
    fun getSocket(): Socket{
        val context = LocalContext.current
        val socket =  Websocket().getInstance(context)
        socket.connect()
        return socket
    }

    enum class StatusPedido {
        PAID, CONFIRMADO, AGUARDANDO_RETIRADA, RETIRADO, SOB_CONFIRMACAO
    }

    enum class Screen {
        Main,
        Other
    }

    var sended = mutableStateOf(false)
    @Composable
    fun FinishOrder(socket: Socket, data: String) {
        val pedido: Order = Gson().fromJson(data, Order::class.java)
        Log.i("finish_travel", "teste $pedido")
        LaunchedEffect(socket) {
            socket.emit("confirm_order_arrived", data)
            sended.value = true
        }
    }


    @Composable
    fun StatusOrderMain(socket: Socket) {
        var context = LocalContext.current

        var statusPedido by remember { mutableStateOf(StatusPedido.PAID) }
        var order by remember { mutableStateOf("") }

        LaunchedEffect(socket) {
            socket.on("travel_accepted") { args ->
                val message = args[0].toString()
                statusPedido = StatusPedido.AGUARDANDO_RETIRADA
                // TODO save delivery man here
                order = message
            }
            socket.on("updated_of_order") { args ->
                val message = args[0].toString()
                statusPedido = StatusPedido.RETIRADO
            }
            socket.on("order_arrived") { args ->
                val message = args[0].toString()
                statusPedido = StatusPedido.SOB_CONFIRMACAO
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .fillMaxSize()
                .scrollable(rememberScrollState(), Orientation.Vertical)
        )
        {
            Text(
                text = stringResource(id = R.string.status_order),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.green_options),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(60.dp))
            Timeline(statusAtual = statusPedido, socket, order)
            FloatingActionButton(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                ,
                onClick = {
                val intent = Intent(context, ChatClient()::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Chat", textAlign = TextAlign.Center)
            }
        }
    }

    @Composable
    fun CardConfirmDelivery(socket: Socket, order: String) {
        val context = LocalContext.current
        var finish by remember { mutableStateOf(false) }

        Card(
            Modifier
                .width(345.dp)
                .height(111.dp),
            backgroundColor = colorResource(id = R.color.green_width),
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 0.dp,
                bottomEnd = 10.dp,
                bottomStart = 0.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.question_confirm_delivery),
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .height(45.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(Color(36, 85, 1, 255)),
                        onClick = {
                            finish = true
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.yes),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .height(45.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(Color(202, 14, 14, 255)),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.no),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }

        if (finish) {
            FinishOrder(socket, order)
            if (sended.value) {
                val data : Order = Gson().fromJson(order, Order::class.java)

                val intent = Intent(context, AvaliationScreen::class.java)

                intent.putExtra("pedido", data.toString())

                context.startActivity(intent)
            }
        }

    }

    @Composable
    fun Timeline(statusAtual: StatusPedido, socket: Socket, order: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .height(600.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 30.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                color =
                                if (statusAtual >= StatusPedido.CONFIRMADO)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_circle),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.checkmark_outline_),
                            modifier = Modifier
                                .height(27.dp)
                                .width(24.dp),
                            contentDescription = null,
                            tint =
                            if (statusAtual >= StatusPedido.CONFIRMADO)
                                Color.White
                            else
                                Color.Black
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.size(35.dp),
                            shape = CircleShape,
                            backgroundColor =
                            if (statusAtual >= StatusPedido.CONFIRMADO)
                                Color.White
                            else
                                colorResource(id = R.color.gray_circle),

                            border = BorderStroke(
                                3.dp,
                                if (statusAtual >= StatusPedido.CONFIRMADO)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_stroke_circle)
                            )
                        ) {}
                        Box(modifier = Modifier.size(4.dp, 100.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(6.dp)
                                    .background(
                                        if (statusAtual <= StatusPedido.CONFIRMADO)
                                            colorResource(id = R.color.gray_line)
                                        else
                                            colorResource(id = R.color.green_button)

                                    )
                            ) {}
                        }
                    }
                    CardTimeLine(
                        title = stringResource(id = R.string.pedido_confirmado),
                        date = "03-02-2023",
                        hour = "10:15 AM",
                        atualStatus = statusAtual >= StatusPedido.CONFIRMADO || statusAtual == StatusPedido.CONFIRMADO
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                color =
                                if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_circle),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.array_down),
                                contentDescription = null,
                                tint =
                                if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                                    Color.White
                                else
                                    Color.Black
                            )
                            Image(
                                painter = painterResource(id = R.drawable.sacola),
                                modifier = Modifier
                                    .height(22.dp)
                                    .width(22.dp)
                                    .padding(bottom = 1.dp),
                                contentDescription = null,
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.size(35.dp),
                            shape = CircleShape,
                            backgroundColor =
                            if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                                Color.White
                            else
                                colorResource(id = R.color.gray_circle),
                            border = BorderStroke(
                                3.dp,
                                if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_stroke_circle)
                            )
                        ) {}
                        Box(modifier = Modifier.size(4.dp, 100.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(6.dp)
                                    .background(
                                        if (statusAtual <= StatusPedido.AGUARDANDO_RETIRADA)
                                            colorResource(id = R.color.gray_line)
                                        else
                                            colorResource(id = R.color.green_button)
                                    )
                            ) {}
                        }
                    }
                    CardTimeLine(
                        title = stringResource(id = R.string.aguardando_retirada),
                        date = "03-02-2023",
                        hour = "10:15 AM",
                        atualStatus = statusAtual >= StatusPedido.AGUARDANDO_RETIRADA || statusAtual == StatusPedido.AGUARDANDO_RETIRADA
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .background(
                                color =
                                if (statusAtual >= StatusPedido.RETIRADO)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_circle),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.entrega),
                            modifier = Modifier
                                .height(25.dp)
                                .width(30.dp),
                            contentDescription = null,
                            tint =
                            if (statusAtual >= StatusPedido.RETIRADO)
                                Color.White
                            else
                                Color.Black
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.size(35.dp),
                            shape = CircleShape,
                            backgroundColor =
                            if (statusAtual >= StatusPedido.RETIRADO)
                                Color.White
                            else
                                colorResource(id = R.color.gray_circle),
                            border = BorderStroke(
                                3.dp,
                                if (statusAtual >= StatusPedido.RETIRADO)
                                    colorResource(id = R.color.green_button)
                                else
                                    colorResource(id = R.color.gray_stroke_circle)
                            )
                        ) {}
                        Box(modifier = Modifier.size(4.dp, 100.dp)) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(6.dp)
                                    .background(
                                        if (statusAtual <= StatusPedido.RETIRADO)
                                            colorResource(id = R.color.gray_line)
                                        else
                                            colorResource(id = R.color.green_button)

                                    )
                            ) {}
                        }
                    }
                    CardTimeLine(
                        title = stringResource(id = R.string.pedido_retirado),
                        date = "03-02-2023",
                        hour = "10:15 AM",
                        atualStatus = statusAtual >= StatusPedido.RETIRADO || statusAtual == StatusPedido.RETIRADO
                    )
                }
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .background(
                                    color =
                                    if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                                        colorResource(id = R.color.green_button)
                                    else
                                        colorResource(id = R.color.gray_circle),
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.smile),
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(30.dp),
                                contentDescription = null,
                                tint =
                                if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                                    Color.White
                                else
                                    Color.Black
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.size(35.dp),
                                shape = CircleShape,
                                backgroundColor =
                                if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                                    Color.White
                                else
                                    colorResource(id = R.color.gray_circle),
                                border = BorderStroke(
                                    3.dp,
                                    if (statusAtual <= StatusPedido.RETIRADO)
                                        colorResource(id = R.color.gray_stroke_circle)
                                    else
                                        colorResource(id = R.color.green_button)
                                )

                            ) {}
                        }
                        CardTimeLine(
                            title = stringResource(id = R.string.sob_confirmacao_entrega),
                            date = "03-02-2023",
                            hour = "10:15 AM",
                            atualStatus = statusAtual >= StatusPedido.SOB_CONFIRMACAO || statusAtual == StatusPedido.SOB_CONFIRMACAO
                        )
                    }
                    if (statusAtual == StatusPedido.SOB_CONFIRMACAO)
                        CardConfirmDelivery(socket, order)
                }
            }
        }
    }

    @Composable
    fun CardTimeLine(title: String, date: String, hour: String, atualStatus: Boolean) {
        var colorText: Color = colorResource(id = R.color.green_options)
        var othercolorText: Color = colorResource(id = R.color.gray_title_no_selecioned)

        Card(
            Modifier
                .width(243.dp)
                .height(75.dp),
            backgroundColor = Color.White,
            border = BorderStroke(
                2.dp,
                if (atualStatus)
                    colorResource(id = R.color.green_button)
                else colorResource(id = R.color.gray_stroke_circle)
            ),
            shape = RoundedCornerShape(8.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 15.dp)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color =
                    if (atualStatus)
                        colorText
                    else othercolorText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 5.dp, end = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = date,
                        textAlign = TextAlign.Start,
                        color =
                        if (atualStatus)
                            colorText
                        else othercolorText,
                        fontSize = 15.sp
                    )
                    Text(
                        text = hour,
                        textAlign = TextAlign.Start,
                        color =
                        if (atualStatus)
                            colorText
                        else othercolorText,
                        fontSize = 15.sp
                    )
                }
            }

        }
    }
}



data class Order(
    @SerializedName("accepted")
    val accepted: Boolean,
    @SerializedName("order")
    val orderDetails: OrderDetails
)

data class OrderDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("accepted_status")
    val acceptedStatus: Boolean,
    @SerializedName("delivered_status_for_client")
    val deliveredStatusForClient: Boolean,
    @SerializedName("retreat_products_status")
    val retreatProductsStatus: Boolean,
    @SerializedName("deliverymanId")
    val deliverymanId: Int,
    @SerializedName("shopping_listId")
    val shoppingListId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("costumer_addressesId")
    val customerAddressesId: Int,
    @SerializedName("intent_payment_id")
    val intentPaymentId: String,
    @SerializedName("deliveryman")
    val deliveryman: Deliveryman,
    @SerializedName("costumer_addresses")
    val customerAddresses: CustomerAddresses,
    @SerializedName("shopping_list")
    val shoppingList: ShoppingList,
    @SerializedName("products_in_shopping_list")
    val productsInShoppingList: List<ProductInShoppingList>
)

data class Deliveryman(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("gender")
    val gender: Gender
)

data class CustomerAddresses(
    @SerializedName("id")
    val id: Int,
    @SerializedName("addressId")
    val addressId: Int,
    @SerializedName("costumerId")
    val customerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("address")
    val address: Address
)

data class Address(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("logradouro")
    val logradouro: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("address_typeId")
    val addressTypeId: Int,
    @SerializedName("complemento")
    val complemento: String,
    @SerializedName("cityId")
    val cityId: Int,
    @SerializedName("uFId")
    val uFId: Int,
    @SerializedName("neighborhoodId")
    val neighborhoodId: Int,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("location")
    val location: Location
)

data class ShoppingList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("freight")
    val freight: Double,
    @SerializedName("total")
    val total: Int,
    @SerializedName("costumerId")
    val customerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("costumer")
    val customer: Customer,
    @SerializedName("products_in_shopping_list")
    val productsInShoppingList: List<ProductInShoppingList>
)

data class Customer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("cpf")
    val cpf: String?,
    @SerializedName("gender")
    val gender: Gender
)

data class ProductInShoppingList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("shopping_listId")
    val shoppingListId: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("product")
    val product: Product
)

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("review")
    val review: Int,
    @SerializedName("active_for_selling")
    val activeForSelling: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("marketerId")
    val marketerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category_of_productId")
    val categoryOfProductId: Int,
    @SerializedName("type_of_productId")
    val typeOfProductId: Int,
    @SerializedName("image_of_product")
    val imageOfProduct: List<ProductImage>,
    @SerializedName("marketer")
    val marketer: Marketer
)

data class ProductImage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageId")
    val imageId: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("image")
    val image: Image
)

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("fairId")
    val fairId: Int?
)

data class Marketer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String,
    @SerializedName("review")
    val review: Int,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("cnpj")
    val cnpj: String?,
    @SerializedName("cpf")
    val cpf: String?,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("tent_name")
    val tentName: String,
    @SerializedName("location")
    val location: Location
)

data class Location(
    @SerializedName("id")
    val id: Int,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Gender(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
