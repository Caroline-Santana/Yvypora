package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.graphics.drawable.shapes.Shape
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.ui.theme.YvyporaTheme

class AddProducts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface {
                    AddProductMain()
                }
            }
        }
    }
}

@Composable
fun HeaderAddProduct() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 35.dp, start = 15.dp, end = 15.dp),
        Arrangement.SpaceBetween,
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
        Image(
            painter = painterResource(id = R.drawable.saveadd),
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProductsMarketer()::class.java)
                    context.startActivity(intent)
                }
                .height(100.dp)
                .width(75.dp),
            contentDescription = "add",
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductMain() {
    var isSelectedPhoto = painterResource(id = R.drawable.check_product_off)


    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }
    var checkState by rememberSaveable {
        mutableStateOf(false)
    }


    var nameStateProduct by rememberSaveable {
        mutableStateOf("")
    }
    var descriptionStateProduct by rememberSaveable {
        mutableStateOf("")
    }
    var isNameErrorProduct by rememberSaveable {
        mutableStateOf(false)
    }
    var customizedStateProduct by remember {
        mutableStateOf("")
    }
    var gramaStateProduct by remember {
        mutableStateOf("")
    }
    var kiloStateProduct by remember {
        mutableStateOf("")
    }
    var kiloemeioStateProduct by remember {
        mutableStateOf("")
    }
    var promoStateProduct by remember {
        mutableStateOf("")
    }

    val typeProduct = arrayOf("Frutas", "Vegetais", "Especiarias", "Outros")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(typeProduct[0]) }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderAddProduct()

        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.add_product),
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )

            Text(
                text = stringResource(id = R.string.category_title),
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)

            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    Modifier
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)),
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },

                    )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    typeProduct.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                selectedText = item
                                expanded = false

                            }
                        ) {
                            Text(text = item)
                        }
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.name),
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )
            TextField(
                value = nameStateProduct,
                onValueChange = {
                    nameStateProduct = it
                    if (it.length == 0) {
                        isNameErrorProduct = true
                    }
                },
                Modifier
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.green_yvy),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))


            )
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.description),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.darkgreen_yvy2)
                )
                Text(
                    text = descriptionStateProduct.length.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.darkgreen_yvy2)
                )
            }
            TextField(
                value = descriptionStateProduct,
                onValueChange = { descriptionStateProduct = it },
                Modifier
                    .height(200.dp)
                    .width(288.dp)
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.green_yvy),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        PhotoInputProduct()
        Text(
            text = stringResource(id = R.string.prices_sizes),
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = colorResource(id = R.color.darkgreen_yvy2),
            textAlign = TextAlign.Start
        );
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column() {
                Row() {
                    Image(
                        painter = isSelectedPhoto,
                        contentDescription = "", modifier = Modifier.clickable { isSelected = true }
                    )
                    Text(
                        text = stringResource(id = R.string.customized),
                        Modifier
                            .height(40.dp)
                            .width(116.dp)

                    )
                }
                TextField(
                    value = customizedStateProduct,
                    onValueChange = { customizedStateProduct = it },
                    Modifier
                        .width(150.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)),
                    label = {
                        Text(text = "R$")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Column() {
                Row() {
                    Image(
                        painter = isSelectedPhoto,
                        contentDescription = "", modifier = Modifier.clickable { isSelected = true }
                    )
                    Text(text = stringResource(id = R.string.g500))
                }
                TextField(
                    value = gramaStateProduct,
                    onValueChange = { gramaStateProduct = it },
                    Modifier
                        .width(154.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)),
                    label = {
                        Text(text = "R$")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column() {
                Row() {
                    Image(
                        painter = isSelectedPhoto,
                        contentDescription = "", modifier = Modifier.clickable { isSelected = true }
                    )
                    Text(
                        text = stringResource(id = R.string.kg1), Modifier
                            .width(150.dp)
                    )
                }
                TextField(
                    value = kiloStateProduct,
                    onValueChange = { kiloStateProduct = it },
                    Modifier
                        .width(154.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)),
                    label = {
                        Text(text = "R$")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Column() {
                Row() {
                    Image(
                        painter = isSelectedPhoto,
                        contentDescription = "", modifier = Modifier.clickable { isSelected = true }
                    )
                    Text(text = stringResource(id = R.string.kg15))
                }
                TextField(
                    value = kiloemeioStateProduct,
                    onValueChange = { kiloemeioStateProduct = it },
                    Modifier
                        .width(154.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)),
                    label = {
                        Text(text = "R$")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

        }
        Row(
            Modifier.fillMaxSize()
        ) {
            Column(Modifier.height(200.dp)) {
                Text(
                    text = stringResource(id = R.string.promotion_question),
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.darkgreen_yvy2)
                )
                Switch(checked = checkState, onCheckedChange = { checkState = it })
            }
            if (checkState) {
                TextField(
                    value = promoStateProduct,
                    onValueChange = { promoStateProduct = it },
                    Modifier
                        .width(154.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.green_yvy),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp)) ,
                    label = {
                        Text(text = "% da Promoção")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoInputProduct() {
//    val painter = rememberImagePainter(
//        if (imageUri.value.isEmpty())
//            R.drawable.adicionar_foto
//        else
//            imageUri.value
//    )
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let { imageUri.value = it.toString() }
//    }

    val painter = painterResource(id = R.drawable.adicionar_foto)

    Column(
        modifier =
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(id = R.string.product_picture),
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        Text(
            text = stringResource(id = R.string.photo_product_description),
            Modifier.width(168.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.gray_text)
        )
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(115.dp)
                .border(
                    width = 2.5.dp,
                    color = colorResource(id = R.color.green_yvy),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp, bottom = 15.dp, top = 12.dp, end = 5.dp),
//                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
    }

}
