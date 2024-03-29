package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalUriHandler
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
import com.example.yvypora.api.commons.getDetailsOfUser
import com.example.yvypora.api.purchases.PurchaseService
import com.example.yvypora.composables.Header
import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.Address
import com.example.yvypora.domain.models.ProductCardShopping
import com.example.yvypora.domain.dto.ProductToStripe
import com.example.yvypora.domain.dto.StripePaymentIntent
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.services.datastore.UserStore
import com.example.yvypora.theme.YvyporaTheme
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.math.RoundingMode
import java.text.DecimalFormat

class CheckoutActivity : ComponentActivity() {
    var total = mutableStateOf<String>("")
    var products: List<ProductCardShopping> = emptyList()
    private var purchaseData = mutableStateOf<StripePaymentIntent?>(null)
    var _token = mutableStateOf<String>("")

    @SuppressLint("CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {

                val context = LocalContext.current
                val intent = (context as CheckoutActivity).intent

                val buynow = intent.getStringExtra("buy_now")
                if (!buynow.isNullOrEmpty()) {
                    val product = Gson().fromJson(buynow, ProductCardShopping::class.java)
                    products.toMutableList().add(product)
                }

                var user by remember {
                    mutableStateOf<User>(User())
                }

                val token = TokenStore(context).getToken.collectAsState(initial = "").value

                _token.value = token

                if (token.isNotEmpty()) {
                    LaunchedEffect(token) {
                        getDetailsOfUser(token) {
                            user = it
                        }
                    }
                }

                val _products = intent.getStringExtra("products") ?: "no data";

                total.value = intent.getStringExtra("total_value") ?: ""

                var products: List<ProductCardShopping> =
                    Gson().fromJson(_products, Array<ProductCardShopping>::class.java)
                        .toList()

                val _mainAddressesCostumer =
                    user.costumerAddresses?.find { it.address.default }

                var productsToStripe = remember {
                    mutableStateListOf<ProductToStripe>()
                }

                productsToStripe.clear();

                products.forEach {
                    productsToStripe.add(
                        ProductToStripe(
                            id = it.id,
                            amount = it.qtde
                        )
                    )
                }

                purchaseData.value = StripePaymentIntent(
                    costumer_address_id = _mainAddressesCostumer?.id ?: 0,
                    freight = 19.99,
                    products = productsToStripe.toList(),
                )

                Log.i("stripe", purchaseData.value.toString())

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    )
                    {

                        Header()

                        MainCheckout(_mainAddressesCostumer?.address, user, products)
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
    fun MainCheckout(
        address: com.example.yvypora.domain.models.Address?,
        user: User,
        products: List<ProductCardShopping>
    ) {
        var subtotal = this.total.value.toDouble()
        val isLaunched = remember { mutableStateOf(false) }
        var taxa_entrega = 19.99
        var total = subtotal.plus(taxa_entrega)

        try {
            total = DecimalFormat("#.##").format(total).toDouble()
        } catch (e: Exception) {
            Log.i("teste", e.message!!)
        }


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
            CardAddressDelivery(address, user)
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
                        selected = index == selectedOptionsIndex
                    )
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
                        text = "R$" +"%.2f".format(total),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 37.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.green_width)
                    )
                }
                val uriHandler = LocalUriHandler.current
                val resultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == RESULT_OK) {
                        // Handle the result here if needed
                    }
                    PurchaseService.updateCall {
                        Log.i("chamei update", it.toString())
                    }
                    val intent = Intent(context, StatusOrder::class.java)

                    intent.putExtra("products", Gson().toJson(products))

                    context.startActivity(intent)
                }

                Button(
                    onClick = {
                        PurchaseService.createPurchaseIntent(purchaseData.value!!, _token.value) {
                            val urlIntent : Intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(it.data)
                            )
                            resultLauncher.launch(urlIntent)
                            isLaunched.value = true
                        }
                    },
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
        if (isLaunched.value) {
            Text("Waiting for the result...")
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
                com.example.yvypora.domain.models.CardPayment(
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
                com.example.yvypora.domain.models.CardPayment(
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
    fun CardMethodPayment(
        methods: PaymentMethodDescription,
        selected: Boolean,
        onSelected: () -> Unit
    ) {
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
                    selected = selected,
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
    fun CardAddressDelivery(address: com.example.yvypora.domain.models.Address?, user: User) {
        var titleAddress = address?.type?.name ?: ""
        var name_remetente = user.name ?: ""
        var telefone_remetente = user.phone ?: "Sem Telefone Cadastradado!"

        var rua =
            "${address?.logradouro} ${address?.number} ${address?.city?.name} ${address?.uf?.name} Brasil"

        Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {

            Card(
                modifier = Modifier
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
                    Row(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.house2),
                            modifier = Modifier
                                .width(28.dp)
                                .height(28.dp),
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
                    Row(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                    Row(
                        modifier = Modifier
                            .padding(start = 15.dp, top = 5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = rua,
                            modifier = Modifier
                                .padding(start = 2.dp),
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                    }
                }
            }
        }
    }



//@Preview(showBackground = true)
//@Composable
//fun CheckoutPreview() {
//    YvyporaTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .fillMaxWidth()
//        )
//        {
//            Header()
//            MainCheckout()
//        }
//    }
//}

