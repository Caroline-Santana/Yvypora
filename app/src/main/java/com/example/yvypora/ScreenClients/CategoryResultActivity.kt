package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.api.product.ProductService
import com.example.yvypora.domain.models.MarketerData
import com.example.yvypora.domain.models.ProductCardSale
import com.example.yvypora.domain.models.product.BaseResponse
import com.example.yvypora.domain.models.product.ProductResponse
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlinx.coroutines.launch
import retrofit2.Call

class CategoryResultActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                val context = LocalContext.current
                val intent = (context as CategoryResultActivity).intent

                val categoryId = intent.getIntExtra("id", -1)

                var fetch by remember {
                    mutableStateOf(listOf(MarketerData()));
                }

                val scope = rememberCoroutineScope()


                    scope.launch {
                        ProductService.listAllProducts(
                            category = categoryId.toString(),
                            score = "0.0",
                            higherPrice = "10000",
                            lowerPrice = "0"
                        ) {
                            val products = it?.data
                            val data = products?.map { product ->
                                bindData(product)
                            }
                            fetch = data!!
                        }
                    }

                Log.i("teste", "$fetch fetched data");

                if (fetch.isNotEmpty()) {
                    val organized = organizeByMarketer(fetch)
                    CategoryResultMain(organized);
                } else {
                    Text("Loading.....")
                }
            }
        }
    }
}

fun bindData(data: ProductResponse): MarketerData {
    return MarketerData(
        name = data.marketer.name,
        photo = data.marketer.picture_uri,
        products = listOf(
            ProductCardSale(
                id = data.id,
                name = data.name,
                photo = data.imageOfProduct.get(0).image.uri ?: "",
                qntd_product = data.availableQuantity,
                price = data.price,
                promo = data.saleOff.isNullOrEmpty().not(),
                weight_product = data.availableQuantity,
                type_weight = data.typeOfPrice.name,
            )
        )
    )
}


fun organizeByMarketer(unorderedList: List<MarketerData>): List<MarketerData> {
    val dividedByName = unorderedList.groupBy { it.name }
    val orderList = mutableListOf<MarketerData>()

    dividedByName.forEach { s, marketerData ->
        val products = mutableListOf<ProductCardSale>()
        marketerData.forEach {
            it.products?.map { list -> products.add(list) }
        }
        marketerData.get(0).products = products
        orderList.add(marketerData.get(0))
    }

    return orderList
}

@Composable
fun CategoryResultMain(productList: List<MarketerData>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.category_result),
                    Modifier.padding(start = 150.dp),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.green_options),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_no_name),
                    modifier = Modifier
                        .height(64.dp)
                        .height(64.dp)
                        .width(164.dp)
                        .padding(start = 80.dp),
                    contentDescription = "logo",
                )
            }
            ListOfMarketerData(productList)
        }

    }
}


@Composable
fun ListOfMarketerData(list: List<MarketerData>) {
    LazyColumn(state = rememberLazyListState()) {
        items(list) { marketerData -> DataMarketer(marketer = marketerData) }
    }
}

@Composable
fun DataMarketer(marketer: MarketerData) {
    val nameMarketer = marketer.name
//        var photoMarketer = marketer.photo
    val photoMarketer = rememberImagePainter(marketer.photo)
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Card(
            Modifier.fillMaxHeight(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = photoMarketer,
                        contentDescription = "",
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)

                    )
                    Text(
                        text = nameMarketer ?: "",
                        Modifier.padding(start = 11.dp),
                        color = colorResource(id = R.color.green_yvy_title),
                        )
                }
                Card(
                    border = BorderStroke(
                        1.dp,
                        colorResource(id = R.color.transparentgreen_yvy)
                    )
                ) {
                    ListOfProductData(products = marketer.products!!)
                }
            }
        }
    }
}

@Composable
fun ListOfProductData(products: List<ProductCardSale>) {
    LazyRow() {
        items(products) { productsData -> DataProduct(product = productsData) }
    }
}

@Composable
fun DataProduct(product: ProductCardSale) {
    val context = LocalContext.current
//        val context = LocalContext.current
    var nameProduct = product.name
//    var photoProduct = card.photo
    var typeProduct = product.type_weight
    var photoProduct = rememberImagePainter(product.photo)
    var qntProduct = product.qntd_product
    var priceProduct = product.price
    var promoProduct = product.promo

    Surface {
        Card(
            elevation = 10.dp,
            contentColor = colorResource(id = R.color.darkgreen_yvy),
            modifier = Modifier
                .width(130.dp)
                .height(175.dp)
                .clickable {
                    val intent = Intent(context, DescriptionProducts::class.java)
                    intent.putExtra("id", product.id)
                    context.startActivity(intent)
                }
                .padding(3.dp),
            border = BorderStroke(1.dp, colorResource(id = R.color.transparentgreen_yvy))

        ) {
            Column(verticalArrangement = Arrangement.Center) {
                if (promoProduct) {
                    Text(
                        stringResource(id = R.string.promotion),
                        modifier = Modifier
                            .background(colorResource(id = R.color.green_yvy))
                            .fillMaxWidth(),
                        color = colorResource(id = R.color.white),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = nameProduct,
                        modifier = Modifier.padding(top = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    // MODIFICAR PARA USAR URL
                    Image(
                        painter = photoProduct,
                        contentDescription = "Product",
                        modifier = Modifier
                            .width(97.dp)
                            .height(70.dp),
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = qntProduct.toString() + typeProduct,
                        modifier = Modifier.padding(top = 2.dp, start = 4.dp),
                        color = colorResource(id = R.color.dark_gray),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        fontSize = 12.sp
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "R$$priceProduct",
                            modifier = Modifier.padding(
                                top = 20.dp,
                                start = 12.dp,
                                end = 15.dp
                            ),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left,
                            fontSize = 14.sp
                        )
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            border = BorderStroke(
                                5.dp,
                                colorResource(id = R.color.darkgreen_yvy)
                            ),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = colorResource(
                                    id = R.color.darkgreen_yvy
                                )
                            )
                        ) {
                            // Adding an Icon "Add" inside the Button
                            Icon(
                                painter = painterResource(id = R.drawable.shopping_cart),
                                modifier = Modifier.padding(start = 3.dp),
                                contentDescription = "content description",
                                tint = Color.White
                            )
                        }
                    }
                }

            }
/*
*       TODO:
*           - Add navbar
* */
        }

    }


}
//
//@Preview(showBackground = true)
//@Composable
//fun FruitsPreview() {
//    YvyporaTheme() {
//        FruitsResultMain()
//    }
//}
