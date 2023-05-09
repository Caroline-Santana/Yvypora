package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.yvypora.ScreenClients.CardProduct
import com.example.yvypora.ScreenClients.Header
import com.example.yvypora.ScreenClients.ListOfProductCardSale
import com.example.yvypora.ScreenClients.ProfileClient
import com.example.yvypora.models.ProductCardSale
import com.example.yvypora.ui.theme.SpaceGrotesk
import com.example.yvypora.ui.theme.YvyporaTheme
import com.example.yvypora.MarketerScreens.HeaderProduct as HeaderProduct

class ProductsMarketer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface {
                    Column() {
                        HeaderProduct()
                        ListOfProductCardSaleMarketer(cards = listProductMarketer)
                    }

                }
            }
        }
    }
}


@Composable
fun HeaderProduct() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 35.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_no_name),
            modifier = Modifier
                .height(55.dp)
                .width(55.dp),
            alignment = Alignment.BottomStart,
            contentDescription = "logo",

            )
        Text(
            text = stringResource(id = R.string.my_products),
            Modifier.padding(start = 25.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        Image(
            painter = painterResource(id = R.drawable.add),
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, AddProducts()::class.java)
                    context.startActivity(intent)
                }
                .height(100.dp)
                .width(75.dp),
            contentDescription = "add",
        )
    }
}

val listProductMarketer = listOf<ProductCardSale>(
    ProductCardSale(
        id = 1,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 10.00,
        promo = false
    ),
    ProductCardSale(
        id = 2,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = false
    ),
    ProductCardSale(
        id = 3,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 10.00,
        promo = false
    ),
    ProductCardSale(
        id = 4,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = false
    ),
    ProductCardSale(
        id = 5,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 15.00,
        promo = false
    ),
    ProductCardSale(
        id = 6,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 25.00,
        promo = false
    ),
    ProductCardSale(
        id = 6,
        name = "Abóbora",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 25.00,
        promo = false
    ),
)

@Composable
fun ListOfProductCardSaleMarketer(cards: List<ProductCardSale>) {
    LazyColumn() {
        items(cards) { card -> CardProductMarketer(product = card) }
    }
}

@Composable
fun CardProductMarketer(product: ProductCardSale) {
    var nameProduct = product.name
//    var photoProduct = card.photo
    var photoProduct = painterResource(id = R.drawable.abobora_shopping)
    var typeProduct = product.type_weight
    var weightProduct = product.weight_product
    var priceProduct = product.price
    var (qtde, setQtde) = remember { mutableStateOf(product.qntd_product) }

    Column() {
        Card(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(25.dp),
            border = BorderStroke(1.dp, colorResource(id = R.color.green_yvy))
        ) {
            Row(
                Modifier.width(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = nameProduct,
                        Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = SpaceGrotesk,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Image(
                        painter = photoProduct,
                        contentDescription = "",
                        modifier = Modifier
                            .width(150.dp)
                            .height(100.dp)
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row() {
                        Text(
                            text = weightProduct.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = SpaceGrotesk,
                            color = colorResource(id = R.color.dark_gray)
                        )
                        Text(
                            text = typeProduct, fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = SpaceGrotesk,
                            color = colorResource(id = R.color.dark_gray)
                        )
                    }
                    Text(
                        text = "R$$priceProduct",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = SpaceGrotesk,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.height(1000.dp) .width(150.dp)){
                Text(
                    text = stringResource(id = R.string.qnt),
                    color = colorResource(id = R.color.green_options),
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        setQtde(qtde - 1)
                    },
                    modifier = Modifier
                        .height(24.dp)
                        .width(28.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(Color(217, 217, 217, 255))
                ) {

                }
                Icon(
                    painter = painterResource(id = R.drawable.remove),
                    modifier = Modifier
                        .clickable {
                            setQtde(qtde - 1)
                        },
                    contentDescription = ""
                )
            }
            if (qtde < 0) {
                Text(
                    text = "0",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
            } else {
                Text(
                    text = "${qtde}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = { setQtde(qtde + 1) },
                    modifier = Modifier
                        .height(24.dp)
                        .width(28.dp),
                    shape = RoundedCornerShape(7.dp),
                ) {}
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp)
                        .clickable {
                            setQtde(qtde + 1)
                        },
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.trash),
                contentDescription = "",
                modifier = Modifier.clickable { })
            Image(painter = painterResource(id = R.drawable.atualizacao), contentDescription = "",
                Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {})
            Image(
                painter = painterResource(id = R.drawable.pause),
                contentDescription = "",
                modifier = Modifier.clickable { })

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProduct() {
    YvyporaTheme {
        ProductsMarketer()
    }
}


