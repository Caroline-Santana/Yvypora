package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var isNameErrorProduct by rememberSaveable {
        mutableStateOf(false)
    }

    val typeProduct = arrayOf("Frutas", "Vegetais", "Especiarias", "Outros")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(typeProduct[0]) }


    Column(Modifier.fillMaxSize()) {
        HeaderAddProduct()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
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

            TextField(value = nameStateProduct, onValueChange = { nameStateProduct = it

            })
        }
    }


}

