package com.example.yvypora.ScreenClients


import android.content.Intent
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.yvypora.R
import com.example.yvypora.models.Filter
import com.example.yvypora.models.ProductCardSale
import com.example.yvypora.ui.theme.YvyporaTheme


class ResultSearch : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ResultSearchMain()
                }
            }
        }
    }
}

@Composable
fun ResultSearchMain() {
    var nameProductSearch = "Abobrinha"
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
        ListOfProductData(products = listProductsData())

    }

}

val OptionFilter = listOf<Filter>(
    Filter(
        id = 1,
        titulo = "Próx. a mim",
        isEnabled = false
    ),
    Filter(
        id = 2,
        titulo = "R$ 5,00",
        isEnabled = false
    ),
    Filter(
        id = 3,
        titulo = "R$ 25,00",
        isEnabled = false
    ),
    Filter(
        id = 4,
        titulo = "2 ou mais",
        isEnabled = false
    ),
    Filter(
        id = 5,
        titulo = "2 ou mais",
        isEnabled = false
    ),
    Filter(
        id = 6,
        titulo = "3 ou mais",
        isEnabled = false
    ),
    Filter(
        id = 7,
        titulo = "4 ou mais",
        isEnabled = false
    ),
    Filter(
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
){
    var selectedFilter by remember { mutableStateOf("") }

    if (showDialog){
        Dialog(onDismissRequest =  onDismiss ) {
            Surface(
                modifier = Modifier.padding(16.dp)
            )  {
                Column(
                    modifier = Modifier.fillMaxWidth()
                )  {
                    Text(
                        text = "Selecione um filtro:"
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FilterButton(
                            text = "Filtro 1",
                            isSelected = selectedFilter == "Filter 1",
                            onClick = {
                                selectedFilter = "Filter 1"
                            }
                        )
                        FilterButton(
                            text = "Filtro 2",
                            isSelected = selectedFilter == "Filter 2",
                            onClick = {
                                selectedFilter = "Filter 2"
                            }
                        )
                        FilterButton(
                            text = "Filtro 3",
                            isSelected = selectedFilter == "Filter 3",
                            onClick = {
                                selectedFilter = "Filter 3"
                            }
                        )
                    }
                    Button(onClick = {
                        onApplyFilter(selectedFilter)
                        onDismiss
                    }) {
                        Text(text = "Aplicar filtro")
                    }
                }
            }

        }
    }
}

@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isSelected) Color.Magenta else Color.Black,
                contentColor = if (isSelected) Color.Magenta else Color.Black,
            )
        ) {
            Text(text = text)
        }
}

fun listProductsData() = listOf<ProductCardSale>(

    ProductCardSale(
        id = 1,
        name = "Manga",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = false
    ),
    ProductCardSale(
        id = 1,
        name = "Manga",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = true,
    ),
    ProductCardSale(
        id = 1,
        name = "Manga",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = true,
    ),
    ProductCardSale(
        id = 1,
        name = "Manga",
        qntd_product = 3,
        photo = 1,
        type_weight = "g",
        weight_product = 800,
        price = 24.00,
        promo = false,
    )
)

@Composable
fun CampResultSearch() {
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
                            .clickable {},
                        tint = Color.White
                    )
                }
            )

            Box(
                modifier = Modifier
                    .width(56.dp)
                    .height(48.dp)
                    .padding(end = 5.dp)
                    .clickable {
//                        FilterSearch(
//                            showDialog = showDialog,
//                            onDismiss = {
//                                        showDialog =
//                            },
//                            onApplyFilter =
//                        )
                    }
                    .background(
                        color = colorResource(id = R.color.green_width),
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "",
                    tint = Color.White
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultSearchPreview() {
    YvyporaTheme {
        ResultSearchMain()
    }
}