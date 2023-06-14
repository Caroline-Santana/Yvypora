package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import kotlin.math.max
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.preference.PreferenceManager
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yvypora.MarketerScreens.imageUri
import com.example.yvypora.R
import com.example.yvypora.api.commons.auth
import com.example.yvypora.api.product.ProductService
import com.example.yvypora.domain.models.Credentials
import com.example.yvypora.domain.models.ProductCardSale
import com.example.yvypora.domain.models.ProductCardShopping
import com.example.yvypora.domain.models.product.ProductResponse
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.views.CartViewModel
import kotlin.math.round
import kotlin.math.roundToInt

class DescriptionProducts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colors.background)
                {
                        HeaderDescriptionProducts()

                        val context = LocalContext.current
                        val intent = (context as DescriptionProducts).intent
                        val productId = intent.getIntExtra("id", -1)
                        Log.i("teste", intent.extras.toString())
                        product.value = fetchProduct(productId)
                }
            }
        }
    }
}

var product = mutableStateOf<ProductResponse?>(null)
var quantity = mutableStateOf(1)

@Composable
fun fetchProduct(id: Int): ProductResponse? {
    var data by remember { mutableStateOf<ProductResponse?>(null) }

    LaunchedEffect(Unit) {
        ProductService.get(id) { it ->
            data = it.data
        }
    }

    return data;
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeaderDescriptionProducts() {
    val context = LocalContext.current
    var rating by remember{mutableStateOf(product.value?.review?.toInt() ?: 0) }
    val image = rememberImagePainter(product.value?.imageOfProduct?.get(0)?.image?.uri ?: "")
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .statusBarsPadding(),
        contentAlignment = Alignment.TopStart
    ){
        Image(
            painter = painterResource(id = R.drawable.wave_products),
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp),
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow_white),
            modifier = Modifier
                .height(65.dp)
                .width(65.dp)
                .padding(start = 15.dp, top = 30.dp)
                .clickable {
                    val intent = Intent(context, InicialScreen::class.java)
                    context.startActivity(intent)
                },
            alignment = Alignment.BottomStart,
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 90.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                modifier = Modifier
                    .height(265.dp)
                    .width(260.dp),
                contentDescription = "product",
                alignment = Alignment.Center
            )
            Row(modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = product.value?.name ?: "",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.green_text_dark)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.value?.marketer?.name ?: "",
                    modifier = Modifier
                        .padding(start = 24.dp),
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.gray_yvy)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Rating(score = rating)
                    Text(
                        text = round(product.value?.review ?: 0F).toString(),
                        modifier = Modifier.padding(start = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
            MainDescriptionProducts()
        }
    }

}

@Composable
fun Star(isFilled: Boolean){
    if (isFilled){
        Icon(
            painter = painterResource(id = R.drawable.star),
            modifier = Modifier
                .height(16.dp)
                .padding(end = 5.dp)
                .width(18.dp),
            contentDescription = "Star",
            tint = colorResource(id = R.color.yellow_star)
        )
    }else{
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Star",
            modifier = Modifier
                .height(16.dp)
                .padding(end = 5.dp)
                .width(18.dp),
            tint = colorResource(id = R.color.gray_star)
        )
    }

}

@Composable
fun Rating(score: Int){
    Row {
       repeat(score){
           Star(isFilled = true)
       }
        repeat(5 - score){
            Star(isFilled = false)
        }
    }
}

@Composable
fun MainDescriptionProducts() {
    val context = LocalContext.current
    val cartViewModel: CartViewModel = viewModel()
    val saleOffPrice = product.value?.saleOff?.firstOrNull()?.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 10.dp, start = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (saleOffPrice != null) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "R$${saleOffPrice}",
                        fontSize = 36.sp,
                        modifier = Modifier.padding(end = 90.dp),
                        color = colorResource(id = R.color.green_width)
                    )
                    Text(
                        text = "R$${product.value?.price}",
                        fontSize = 20.sp,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.padding(end = 90.dp),
                        color = colorResource(id = R.color.gray_title_no_selecioned)
                    )
                }
            } else {
                Text(
                    text = "R$${product.value?.price}",
                    fontSize = 36.sp,
                    modifier = Modifier.padding(end = 90.dp),
                    color = colorResource(id = R.color.green_options)
                )
            }

            WidhtProductTeste()
        }
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier.padding(top = 10.dp),
            fontSize = 32.sp,
            color = colorResource(id = R.color.green_text_dark)
        )
        Text(
            text = product.value?.description ?: "",
            modifier = Modifier.padding(top = 15.dp, end = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            fontSize = 15.sp,
            color = colorResource(id = R.color.green_text_dark)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
                    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
                    cartViewModel.addCart(bindDataToProductCardShopping(product.value!!, quantity.value), context, sharedPrefs)
                },
                modifier = Modifier
                    .width(128.dp)
                    .height(45.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.green_button)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255)),


                ) {
                Icon(
                    painter = painterResource(id = R.drawable.shopadd),
                    modifier = Modifier.padding(end = 3.dp),
                    contentDescription = "content description",
                    tint = colorResource(id =R.color.green_button )
                )
                Text(
                    text = stringResource(id = R.string.add_affection),
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.green_button),
                    fontSize = 12.sp
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .width(128.dp)
                    .height(45.dp)
                    ,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),


                ) {
                Text(
                    text = stringResource(id = R.string.buy_now),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

fun bindDataToProductCardShopping(data: ProductResponse, quantity: Int): ProductCardShopping {
    return ProductCardShopping(
        id = data.id,
        name = data.name,
        photo = data.imageOfProduct.get(0).image.uri,
        weight_product = data.availableQuantity,
        price = data.price,
        type_weight = data.typeOfPrice.name,
        qtde = quantity,
        isSelected = false,
        marketerId = data.marketerId,
        marketerName =  data.marketer.name,
        marketerPhoto = data.marketer.picture_uri,
        marketerTentName = data.marketer.tent_name
    )
}




@Composable
fun WidhtProductTeste() {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.green_widht_transparent),
                RoundedCornerShape(7.dp)
            )
            .width(105.dp)
            .height(30.dp)
    ) {
        Row(
            modifier = Modifier
                .width(105.dp)
                .height(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { quantity.value = (quantity.value - 1).coerceAtLeast(1)},
                modifier = Modifier
                    .height(30.dp)
                    .width(32.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217, 255))
            ) {}

            Text(
            text = "${quantity.value}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.green_width)
        )
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = { quantity.value += 1 },
                    modifier = Modifier
                        .height(30.dp)
                        .width(32.dp),
                    shape = RoundedCornerShape(7.dp),
                ) {}
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    modifier = Modifier
                        .clickable { quantity.value += 1 },
                    contentDescription = "",
                    tint = Color.White
                )
            }

        }

        Icon(
            painter = painterResource(id = R.drawable.remove),
            modifier = Modifier.clickable {  quantity.value = (quantity.value - 1).coerceAtLeast(1) },
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionProductsPreview() {
    YvyporaTheme {

            HeaderDescriptionProducts()

    }
}