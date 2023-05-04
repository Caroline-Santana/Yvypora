package com.example.yvypora.ScreenClients

import android.content.Intent
import android.location.Address
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.yvypora.R
import com.example.yvypora.theme.YvyporaTheme

class CheckoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val address = intent.getStringExtra("ADDRESS") ?: ""
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth())
                    {
                        Header()
                        MainCheckout()
                    }

                }
            }
        }
    }
}
//val selectedCards = mutableStateListOf<Int>()
class CheckOutViewModel: ViewModel(){
    private val _mainAddress = mutableStateOf<Address?>(null)
    val mainAddress: State<Address?> = _mainAddress

    fun setMainAddress(address: Address){
        _mainAddress.value = address
    }


}
@Composable
fun MainCheckout(viewModel: CheckOutViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val mainAddress = viewModel.mainAddress.value

 val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 35.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            modifier = Modifier
                .width(43.dp)
                .height(46.dp)
                .clickable {
                    val intent = Intent(context, ShoppingCartActivity()::class.java)
                    context.startActivity(intent)
                },
            contentDescription = "back",
            tint = colorResource(id = R.color.green_width)
        )
        Text(
            text = stringResource(id = R.string.checkout),
            modifier = Modifier.width(250.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier
                .padding(start = 37.dp, end = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.address),
                fontSize = 22.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )
            ClickableText(
                text = AnnotatedString(text =  stringResource(id = R.string.change),
                    SpanStyle(
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_camps),
                        textDecoration = TextDecoration.Underline,
                    )
                ), onClick = {
//                    val intent = Intent(context, DecisionSreenActivity()::class.java)
//                    context.startActivity(intent)

                })
        }
        if (mainAddress != null) {
            Text(text = "Endereço principal")
        } else {
            Text(
                text = "Selecione um endereço",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
        }
            Text(
                text = stringResource(id = R.string.payment_method),
                fontSize = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 37.dp),
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )
        Column(modifier = Modifier.padding(20.dp)) {
            CardPayMethods1()
            Spacer(modifier = Modifier.height(15.dp))
            CardPayMethods2()
            Spacer(modifier = Modifier.height(15.dp))
            CardPayMethods3()
            Spacer(modifier = Modifier.height(15.dp))
            CardPayMethods4()
        }
        Text(
            text = stringResource(id = R.string.order),
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        //Card dos produtos selecionados anteriormente aqui
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            text = "Subtotal   R$ 48,00" ,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        Text(
            text = stringResource(id = R.string.delivery_fee) + " R$ 8,32" ,
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Column(modifier = Modifier.padding(bottom = 25.dp),) {
                Text(
                    text = "Total:",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 37.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.darkgreen_yvy2)
                )
                Text(
                    text = "R$ 56,32",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 37.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.green_width)
                )
            }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(58.dp)
                        .fillMaxWidth()
                        .padding(start = 192.dp, end = 30.dp),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 13.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 13.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color(36, 85, 1, 255)),
                ) {
                    Text(
                        text = stringResource(id = R.string.pay),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    YvyporaTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth())
        {
            Header()
            MainCheckout()
        }
    }
}