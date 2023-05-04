package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.CardProduct
import com.example.yvypora.ScreenClients.Header
import com.example.yvypora.ScreenClients.ListOfProductCardSale
import com.example.yvypora.ScreenClients.ProfileClient
import com.example.yvypora.models.ProductCardSale
import com.example.yvypora.ui.theme.YvyporaTheme

class ProductsMarketer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface {
                    HeaderProduct()
//                    ListOfProductCardSale(cards = listProductMarketer)
                }
            }
        }
    }
}

@Composable
fun HeaderProduct(){
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 35.dp, start = 15.dp, end = 15.dp),
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
            painter = painterResource(id = R.drawable.add),
            modifier = Modifier
                .clickable {
                    val intent = Intent(context,AddProducts()::class.java)
                    context.startActivity(intent)
                }
                .height(50.dp)
                .width(55.dp),
            contentDescription = "add",
        )
    }
}

//val listProductMarketer = listOf<ProductCardSale>(
//    ProductCardSale(
//        id = 1,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 10.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 2,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 24.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 3,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 10.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 4,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 24.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 5,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 15.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 6,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 25.00,
//        promo = false
//    ),
//    ProductCardSale(
//        id = 6,
//        name = "Abóbora",
//        qntd_product = 3,
//        photo = 1,
//        type_weight = "g",
//        weight_product = 800,
//        price = 25.00,
//        promo = false
//    ),
//)
