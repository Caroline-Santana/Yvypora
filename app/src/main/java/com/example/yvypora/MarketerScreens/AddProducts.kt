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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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


    var nameStateProduct by rememberSaveable {
        mutableStateOf("")
    }
    var descriptionStateProduct by rememberSaveable {
        mutableStateOf("")
    }
    var isNameErrorProduct by rememberSaveable {
        mutableStateOf(false)
    }

    val typeProduct = arrayOf("Frutas", "Vegetais", "Especiarias", "Outros")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(typeProduct[0]) }
    Column(Modifier.fillMaxSize()) {

        HeaderAddProduct()

        Column(
            Modifier.verticalScroll(state = rememberScrollState(), enabled = true),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.add_product),
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )

            Text(text = stringResource(id = R.string.category_title))
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

            Text(text = stringResource(id = R.string.name))
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
            Text(
                text = stringResource(id = R.string.description),
            )
            TextField(
                value = descriptionStateProduct,
                onValueChange = { descriptionStateProduct = it },
                Modifier
                    .height(120.dp)
                    .width(188.dp)
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.green_yvy),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        PhotoInputProduct()
        Text(text = stringResource(id = R.string.prices_sizes))
        Row() {
            Image(
                painter = painterResource(id = R.drawable.check_product_on),
                contentDescription = ""
            )
            Text(text = "")

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
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(115.dp)
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
