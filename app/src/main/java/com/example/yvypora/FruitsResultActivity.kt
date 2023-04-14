package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme

class FruitsResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface {
                    Image(
                        painter = painterResource(id = R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(108.dp)
                            .padding(top = 25.dp),
                        alignment = Alignment.Center,
                        contentDescription = "logo",
                    )
                    Spacer(
                        modifier = Modifier.height(36.dp)
                            .width(20.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = stringResource(id = R.string.fruits_result),
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.darkgreen_yvy),
                            fontSize = 48.sp
                        )
                    }
                }
                FruitsResultMain()
            }
        }

    }
}

@Composable
fun FruitsResultMain() {
    Surface() {
        Column() {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun FruitsPreview() {
    FruitsResultActivity()
}