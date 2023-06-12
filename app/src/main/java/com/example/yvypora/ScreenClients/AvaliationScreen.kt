package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.yvypora.R
import com.example.yvypora.api.product.ProductService
import com.example.yvypora.domain.models.Order
import com.example.yvypora.domain.dto.ReviewDeliveryman
import com.example.yvypora.domain.dto.ReviewDeliverymanDetails
import com.example.yvypora.domain.dto.ReviewProduct
import com.example.yvypora.domain.dto.ReviewProductDetails
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.gson.Gson
import kotlinx.coroutines.delay

class AvaliationScreen : ComponentActivity() {
    private var productNote = mutableStateOf(0.0)
    private var deliverymanNote = mutableStateOf(0.0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val gson = Gson()
                    val context = LocalContext.current
                    val intent = (context as AvaliationScreen).intent

                    val pedido = intent.getStringExtra("pedido")

                    val order: Order = gson.fromJson(pedido, Order::class.java)



                    val productIds = remember { mutableStateListOf<Int>() }

                    order.orderDetails.shoppingList.productsInShoppingList.map {
                        productIds.add(it.productId)
                    }

                    Log.i("review", "lista de ids => ${productIds.toList()}")

                    HeaderAvaliation(order.orderDetails.deliverymanId, productIds.toList())
                }
            }
        }
    }


    @Composable
    fun HeaderAvaliation(deliverymanId: Int, productIds: List<Int>) {
        val context = LocalContext.current
        val user = fetchDetails()
        val profileImage = rememberAsyncImagePainter(user.picture_uri ?: "")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .statusBarsPadding(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.wave__6_),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp),
                contentDescription = stringResource(id = R.string.back_screen)
            )
            Image(
                painter = profileImage,
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, ProfileClient()::class.java)
                        context.startActivity(intent)
                    }
                    .padding(25.dp)
                    .height(50.dp)
                    .width(55.dp),
                contentDescription = "logo",
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 90.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Compra #109189",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.green_options)
                )
            }
            ColumnAvaliations(deliverymanId, productIds)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun ColumnAvaliations(deliverymanId: Int, productIds: List<Int>) {
        var launchAvaliation by remember {
            mutableStateOf(false)
        }


        val context = LocalContext.current
        var comentarioState by rememberSaveable {
            mutableStateOf("")
        }
        val inputsFocusRequest = FocusRequester()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 10.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(145.dp))
            Text(
                text = stringResource(id = R.string.avalie_order),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            OutlinedTextField(
                value = comentarioState,
                onValueChange = { comentarioState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .focusRequester(inputsFocusRequest),
                singleLine = false,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 10.dp
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green_camps),
                    unfocusedBorderColor = colorResource(id = R.color.green_camps),
                    cursorColor = colorResource(id = R.color.darkgreen_yvy),
                    textColor = colorResource(id = R.color.green_text_comentario),
                    backgroundColor = colorResource(id = R.color.green_camps)
                ),
                textStyle = MaterialTheme.typography.body1,
                label = {
                    Text(
                        stringResource(id = R.string.ex_comentario),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.green_text_comentario),
                        fontSize = 18.sp
                    )
                }
            )
            Text(
                text = stringResource(id = R.string.nota_delivery),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            RatingClickDeliveryman()
            Text(
                text = stringResource(id = R.string.nota_products),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.green_options)
            )
            RatingClickProduct()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.text_avaliation),
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(end = 40.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    ClickableText(
                        AnnotatedString(
                            text = stringResource(id = R.string.click_avaliation),
                            SpanStyle(
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.green_camps),
                                textDecoration = TextDecoration.Underline,
                            )
                        ), onClick = {
                            launchAvaliation = true
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        launchAvaliation = true
                    },
                    modifier = Modifier
                        .height(58.dp)
                        .width(170.dp),
                    shape = RoundedCornerShape(
                        topStart = 13.dp,
                        topEnd = 0.dp,
                        bottomEnd = 13.dp,
                        bottomStart = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),
                ) {
                    Text(
                        text = stringResource(id = R.string.button_avaliation),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
            }
        }

        if (launchAvaliation) {
            val reviews = remember { mutableStateListOf<ReviewProductDetails>() }

            var finish_1 by remember {
                mutableStateOf(false)
            }

            var finish_2 by remember {
                mutableStateOf(false)
            }



            productIds.forEach {
                ReviewProductDetails(
                        productId = it,
                        avaliation = productNote.value,
                )
            }


            val reviewProduct = ReviewProduct(
                reviews = reviews.toList()
            )

            val reviewDeliveryman = ReviewDeliveryman(
                review = ReviewDeliverymanDetails(
                    deliverymanId = deliverymanId,
                    avaliation = deliverymanNote.value
                )
            )

            Log.i("review", reviewDeliveryman.toString())

            val service = ProductService

            LaunchedEffect(Unit) {
                TokenStore(context).getToken.collect { token ->
                    service.reviewProducts(token, reviewProduct) {
                        finish_1 = it
                        if (!it) {
                            Log.i("review", "error na avaliacao")
                        } else {
                            finish_1 = true
                        }
                    }


                    service.reviewDeliveryman(token, reviewDeliveryman) {
                        finish_2 = it
                        if (!it) {
                            Log.i("review", "error na avaliacao")
                        } else {
                            finish_2 = true
                        }
                    }

                    Log.i("review", "$finish_1 $finish_2")

                    delay(5000)

                    if (!finish_1 && !finish_2) {
                        val intent = Intent(context, InicialScreen::class.java)
                        context.startActivity(intent)
                    }
                }
            }

        }


    }

    @Composable
    fun RatingClickProduct() {
        val selectedStars = remember { mutableStateOf(0.0) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { index ->
                val isSelected = index < selectedStars.value
                val starColor =
                    if (isSelected) colorResource(id = R.color.yellow_star) else colorResource(id = R.color.gray_star)
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    modifier = Modifier
                        .clickable {
                            selectedStars.value = (index + 1).toDouble()
                            productNote.value = selectedStars.value
                        }
                        .height(55.dp)
                        .width(48.dp),
                    contentDescription = null,
                    tint = starColor,
                )
            }
        }
    }

    @Composable
    fun RatingClickDeliveryman() {
        val selectedStars = remember { mutableStateOf(0.0) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { index ->
                val isSelected = index < selectedStars.value
                val starColor =
                    if (isSelected) colorResource(id = R.color.yellow_star) else colorResource(id = R.color.gray_star)
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    modifier = Modifier
                        .clickable {
                            selectedStars.value = (index + 1).toDouble()
                            deliverymanNote.value = selectedStars.value
                        }
                        .height(55.dp)
                        .width(48.dp),
                    contentDescription = null,
                    tint = starColor,
                )
            }

        }
    }


    @Preview(showBackground = true)
    @Composable
    fun AvaliationScreenPreview() {
        YvyporaTheme {
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
//                HeaderAvaliation()
            }
        }
    }
}