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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.yvypora.R
import com.example.yvypora.api.product.ProductService
import com.example.yvypora.domain.models.ProductCardSale
import com.example.yvypora.domain.models.product.ProductResponse
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlin.math.roundToInt


class ResultSearch : ComponentActivity() {
    var products = mutableStateOf(listOf<ProductCardSale>())
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
                    val intent = (context as ResultSearch).intent
                    val searchParam = intent.getStringExtra("search");


                    LaunchedEffect(searchParam) {
                        ProductService.search(searchParam ?: "") {
                            val _products = it.data?.products
                            if (!_products.isNullOrEmpty()) {
                                products.value =
                                    _products.map { product -> bindDataToProductCardSale(product!!) }
                            }
                        }
                    }

                    ResultSearchMain(searchParam ?: "", products.value)
                }
            }
        }
    }


    fun bindDataToProductCardSale(data: ProductResponse): ProductCardSale {
        return ProductCardSale(
            id = data.id,
            name = data.name,
            photo = data.imageOfProduct.get(0).image.uri,
            weight_product = data.availableQuantity,
            promo = !data.saleOff.isNullOrEmpty(),
            price = data.price,
            qntd_product = data.availableQuantity,
            type_weight = data.typeOfPrice.name
        )
    }

    @Composable
    fun ResultSearchMain(nameProductSearch: String, resultList: List<ProductCardSale>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
        ) {
            CampResultSearch()
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.title_result_search) + " $nameProductSearch",
                modifier = Modifier.padding(start = 20.dp),
                color = colorResource(id = R.color.green_options),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            ListOfProductData(products = resultList)
        }

    }

    val OptionFilter = listOf<com.example.yvypora.domain.models.Filter>(
        com.example.yvypora.domain.models.Filter(
            id = 1,
            titulo = "Próx. a mim",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 2,
            titulo = "R$ 5,00",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 3,
            titulo = "R$ 25,00",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 4,
            titulo = "2 ou mais",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 5,
            titulo = "2 ou mais",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 6,
            titulo = "3 ou mais",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 7,
            titulo = "4 ou mais",
            isEnabled = false
        ),
        com.example.yvypora.domain.models.Filter(
            id = 8,
            titulo = "5 apenas",
            isEnabled = false
        ),
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun FilterSearch(
        showDialog: Boolean,
        onDismiss: () -> Unit,
        onApplyFilter: (filter: String) -> Unit
    ) {
        var selectedFilter by remember { mutableStateOf("") }
        var inicialPrice = 0.0
        var finalPrice = 50.0

        if (showDialog) {
            Dialog(
                onDismissRequest = onDismiss
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.___icon__close_outline_),
                            modifier = Modifier
                                .height(35.dp)
                                .width(40.dp)
                                .clickable {
                                    onDismiss()
                                },
                            alignment = Alignment.BottomStart,
                            contentDescription = stringResource(id = R.string.back_screen)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(id = R.string.filter),
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Medium,
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.green_width),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = stringResource(id = R.string.distance_filter),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.green_options),
                            textAlign = TextAlign.Start
                        )
                        FilterButton(
                            text = stringResource(id = R.string.next_to_me),
                            isSelected = selectedFilter == "Filter 1",
                            onClick = {
                                selectedFilter = "Filter 1"
                            }
                        )
                        Text(
                            text = stringResource(id = R.string.price_filter),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.green_options),
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        SliderPrice()
                        Text(
                            text = stringResource(id = R.string.rating),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.green_options),
                            textAlign = TextAlign.Start
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 20.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FilterButtonRating(
                                text = stringResource(id = R.string.two_more),
                                isSelected = selectedFilter == "Filter 3",
                                onClick = {
                                    selectedFilter = "Filter 3"
                                }
                            )
                            FilterButtonRating(
                                text = stringResource(id = R.string.three_more),
                                isSelected = selectedFilter == "Filter 4",
                                onClick = {
                                    selectedFilter = "Filter 4"
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 20.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            FilterButtonRating(
                                text = stringResource(id = R.string.four_more),
                                isSelected = selectedFilter == "Filter 5",
                                onClick = {
                                    selectedFilter = "Filter 5"
                                }
                            )
                            FilterButtonRating(
                                text = stringResource(id = R.string.five_only),
                                isSelected = selectedFilter == "Filter 6",
                                onClick = {
                                    selectedFilter = "Filter 6"
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(35.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick =
                                {
                                    onApplyFilter(selectedFilter)
                                    onDismiss
                                },
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(39.dp),
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255))
                            ) {
                                Text(
                                    text = stringResource(id = R.string.apply_filter),
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                        }

                    }
                }

            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SliderPrice() {
        var sliderValues by remember {
            mutableStateOf(5f..30f)
        }
        val formattedStartValue = sliderValues.start.roundToInt().toString() + ",00"
        val formattedEndValue = sliderValues.endInclusive.roundToInt().toString() + ",00"
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .width(87.dp)
                        .height(35.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(1.dp, colorResource(id = R.color.green_width))
                ) {
                    Text(
                        text = "R$${formattedStartValue}",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = colorResource(id = R.color.green_width),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = stringResource(id = R.string.to),
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.green_width),
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(87.dp)
                        .height(35.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(1.dp, colorResource(id = R.color.green_width))
                ) {
                    Text(
                        text = "R$ ${formattedEndValue}",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = colorResource(id = R.color.green_width),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            RangeSlider(
                value = sliderValues,
                onValueChange = { sliderValues_ ->
                    sliderValues = sliderValues_
                },
                valueRange = 5f..30f,
                onValueChangeFinished = {
                    Log.d(
                        "MainActivity",
                        "First: ${sliderValues.start}, Last: ${sliderValues.endInclusive}"
                    )
                }
            )
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
        var colorText: Color = colorResource(id = R.color.green_width)
        if (isSelected)
            colorText = Color.White
        else
            colorText

        Button(
            onClick = onClick,
            modifier = Modifier.padding(start = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isSelected) colorResource(id = R.color.green_width) else Color.White,
                contentColor = if (isSelected) colorResource(id = R.color.green_width) else Color.White,
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.green_width))
        ) {
            Text(text = text, color = colorText)
        }
    }

    @Composable
    fun FilterButtonRating(text: String, isSelected: Boolean, onClick: () -> Unit) {

        var colorText = colorResource(id = R.color.green_width)
        if (isSelected)
            colorText = Color.White
        else
            colorText
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(46.dp)
                .width(124.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isSelected) colorResource(id = R.color.green_width) else Color.White,
                contentColor = if (isSelected) colorResource(id = R.color.green_width) else Color.White,
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.green_width))
        ) {
            Row() {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    tint = colorResource(id = R.color.yellow_star)
                )
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    color = colorText
                )
            }

        }
    }


    fun listProductsData() = listOf<ProductCardSale>(

        ProductCardSale(
            id = 1,
            name = "Manga",
            qntd_product = 3,
            photo = "",
            type_weight = "g",
            weight_product = 800,
            price = 24.00,
            promo = false
        ),
        ProductCardSale(
            id = 1,
            name = "Manga",
            qntd_product = 3,
            photo = "",
            type_weight = "g",
            weight_product = 800,
            price = 24.00,
            promo = true,
        ),
        ProductCardSale(
            id = 1,
            name = "Manga",
            qntd_product = 3,
            photo = "",
            type_weight = "g",
            weight_product = 800,
            price = 24.00,
            promo = true,
        ),
        ProductCardSale(
            id = 1,
            name = "Manga",
            qntd_product = 3,
            photo = "",
            type_weight = "g",
            weight_product = 800,
            price = 24.00,
            promo = false,
        )
    )

    @Composable
    fun CampResultSearch() {
        var isLaunchedEffectTrigger by remember {
            mutableStateOf(false)
        }
        var showDialog by remember { mutableStateOf(false) }
        var searchState by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                modifier = Modifier
                    .height(45.dp)
                    .width(50.dp)
                    .clickable {
                        val intent = Intent(context, InicialScreen::class.java)
                        context.startActivity(intent)
                    },
                alignment = Alignment.BottomStart,
                contentDescription = stringResource(id = R.string.back_screen)
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(330.dp)
                        .height(48.dp)
                        .padding(start = 15.dp, end = 10.dp),
                    value = searchState,
                    onValueChange = { newText ->
                        searchState = newText
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        backgroundColor = colorResource(id = R.color.green_yvy),
                        focusedIndicatorColor = colorResource(id = R.color.green_yvy),
                        unfocusedIndicatorColor = colorResource(id = R.color.green_yvy),
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.lupa_icon),
                            contentDescription = stringResource(id = R.string.lupa),
                            modifier = Modifier
                                .width(35.dp)
                                .height(35.dp)
                                .padding(end = 10.dp)
                                .clickable {
                                    isLaunchedEffectTrigger = true
                                },
                            tint = Color.White
                        )
                    }
                )
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .width(56.dp)
                        .height(48.dp)
                        .padding(end = 5.dp)
                        .background(
                            color = colorResource(id = R.color.green_width),
                            shape = RoundedCornerShape(5.dp)
                        ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                FilterSearch(
                    showDialog = showDialog,
                    onDismiss = {
                        showDialog = false
                    },
                    onApplyFilter = { filter ->
                        showDialog = false
                    }
                )
            }
        }

        if (isLaunchedEffectTrigger) {
            LaunchedEffect(searchState){
                ProductService.search(searchState) {
                    val _products = it.data?.products
                    products.value = _products!!.map {product ->
                        bindDataToProductCardSale(product!!)
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun ResultSearchPreview() {
        YvyporaTheme {
            ResultSearchMain("Abobrinha", emptyList())
        }
    }

}