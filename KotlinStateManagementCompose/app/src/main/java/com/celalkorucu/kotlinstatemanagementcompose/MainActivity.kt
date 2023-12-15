package com.celalkorucu.kotlinstatemanagementcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.celalkorucu.kotlinstatemanagementcompose.ui.theme.KotlinStateManagementComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinStateManagementComposeTheme {
                    MainScreen()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun MainScreen( modifier: Modifier = Modifier) {

    var string = remember {
        mutableStateOf("")
    }

    Surface (modifier = Modifier
        .background(color = Color.Cyan)
        .fillMaxSize()){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

           SpecialTextField(string = string.value, function = {
               string.value=it
           })


            Text(
                text = "Hello",
                modifier = modifier,
                fontSize = 24.sp
            )
            Button(onClick = {
                string.value=""
            }){

                Text(
                    text = "Button"
                )
                Text(
                    text = "Text"
                )
            }

            Image(
                bitmap = ImageBitmap.imageResource
                    (id = R.drawable.got),
                contentDescription ="Boş Ekran"
            )
            Text(text = string.value)
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun SpecialTextField(string : String , function : (String) -> Unit){
    TextField(value = string, onValueChange = function, modifier = Modifier
        .padding(10.dp)
        .clip(CircleShape)
        .height(60.dp)
        .background(color = Color.Green)
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinStateManagementComposeTheme {
        MainScreen()
    }
}