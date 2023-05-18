package com.example.yvypora.ScreenClients

import android.content.Intent
import android.location.Address
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.yvypora.models.AddressCard
import com.example.yvypora.models.CardPayment
import com.example.yvypora.models.PaymentMethodDescription
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    )
                    {
                        Header()
                        MainCheckout()
                    }

                }
            }
        }
    }
}

class CheckOutViewModel : ViewModel() {
    private val _mainAddress = mutableStateOf<Address?>(null)
    val mainAddress: State<Address?> = _mainAddress

    fun setMainAddress(address: Address) {
        _mainAddress.value = address
    }


}

@Composable
fun MainCheckout() {
    var subtotal = 48.00
    var taxa_entrega = 8.32
    var total = subtotal.plus(taxa_entrega)
    var selectedOptionsIndex by remember { mutableStateOf(0) }
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
    Column(
        modifier = Modifier
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
                text = AnnotatedString(
                    text = stringResource(id = R.string.change),
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
        CardAddressDelivery()
        Spacer(modifier = Modifier.height(25.dp))
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
            listPayMethods.forEachIndexed { index, paymentMethodDescription ->
                CardMethodPayment(
                    methods = paymentMethodDescription,
                    selected = index == selectedOptionsIndex )
                {
                    selectedOptionsIndex = index
                }
            }
//            ListOfPayMethod(methods = listPayMethods)
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
            text = "Subtotal   R$ ${subtotal}",
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        Text(
            text = stringResource(id = R.string.delivery_fee) + " R$ ${taxa_entrega}",
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 37.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.padding(bottom = 25.dp)) {
                Text(
                    text = "Total: ",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 37.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.darkgreen_yvy2)
                )
                Text(
                    text = "R$ ${total}",
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
                onClick = {
                    val intent = Intent(context, StatusOrder()::class.java)
                    context.startActivity(intent) },
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


val listPayMethods = mutableStateListOf<PaymentMethodDescription>(
    PaymentMethodDescription(
        id = 1,
        name_method = "Cartão de crédito",
        logo = 0,
        isSelected = false,
        description_method = "Pagamento pode ser parcelado até 4x",
        card = listOf(
            CardPayment(
                nome_titular = "Valeria Almeida",
                numero_cartao = "**** **** **** ****",
                cvv = 321,
                data_validade = "2/28",
                type_card = "crédito"
            )
        )
    ),
    PaymentMethodDescription(
        id = 2,
        name_method = "Pix",
        logo = 0,
        isSelected = false,
        description_method = "Pagamento feito pelo seu banco digital, aprovado na hora",
        card = null
    ),
    PaymentMethodDescription(
        id = 3,
        name_method = "Cartão de débito",
        logo = 0,
        isSelected = false,
        description_method = "Pagamento sujeito a taxa",
        card = listOf(
            CardPayment(
                nome_titular = "Rogério Ceni",
                numero_cartao = "**** **** **** ****",
                cvv = 321,
                data_validade = "11/23",
                type_card = "débito"
            )
        )
    ),
    PaymentMethodDescription(
        id = 4,
        name_method = "PayPal",
        logo = 0,
        isSelected = false,
        description_method = "Pagamento feito pelo seu banco digital",
        card = null
    )
)

//@Composable
//fun ListOfPayMethod(methods: List<PaymentMethodDescription>) {
//    LazyColumn(
//        Modifier
//            .fillMaxWidth()
//            .height(
//                250.dp
//            ), userScrollEnabled = false) {
//        items(methods) { methods -> CardMethodPayment(
//            methods = methods)}
//
//    }
//}


@Composable
fun CardMethodPayment(methods: PaymentMethodDescription, selected: Boolean, onSelected: () -> Unit) {
    var title_method = methods.name_method
    var photo = painterResource(id = R.drawable.visa)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Row() {
            RadioButton(
                selected = selected ,
                onClick = onSelected,
                modifier = Modifier.padding(end = 16.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.green_camps),
                    disabledColor = Color.LightGray
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.green_camps),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onSelected() }
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = photo,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    contentDescription = "icon"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.Unspecified
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title_method,
                        modifier = Modifier
                            .padding(start = 10.dp),
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                }

            }
        }
    }


}

@Composable
fun CardAddressDelivery(){
//    var titleAddress = address.titulo
//    var name_remetente = address.name_remetente
//    var telefone_remetente = address.telefone_remetente
//    var rua = address.rua
//    var numero = address.numero
//    var cidade = address.cidade
//    var estado = address.estado
//    var pais = address.pais
    var titleAddress = "Casa"
    var name_remetente = "Caroline"
    var telefone_remetente = "11 954009469"
    var rua = "Rua Rita Paes Bustamante"
    var numero = 90
    var cidade = "Carapicuíba"
    var estado = "São Paulo"
    var pais = "Brasil"
        Column (modifier = Modifier.padding(start= 15.dp, end = 15.dp)) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .height(140.dp),
            backgroundColor = colorResource(id = R.color.green_camps),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 10.dp
            )
        ) {
            Column() {
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.house2),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                        ,
                        tint = colorResource(id = R.color.darkgreen_yvy),
                        contentDescription = "icon"
                    )
                    Text(
                        text = titleAddress,
                        modifier = Modifier
                            .padding(start = 6.dp),
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
//                    OpcoesMenu()
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = name_remetente,
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                    Text(
                        text = " ${'-'} $telefone_remetente",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = rua,
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "$numero${','} ",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " $cidade${','} ",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }

                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = " $estado${','} ",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = pais,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    YvyporaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        )
        {
            Header()
            MainCheckout()
        }
    }
}