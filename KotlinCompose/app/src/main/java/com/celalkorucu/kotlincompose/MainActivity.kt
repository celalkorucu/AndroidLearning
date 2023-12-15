package com.celalkorucu.kotlincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.celalkorucu.kotlincompose.ui.theme.KotlinComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainScreen()
                }
            }
        }
    }
}

@Composable
fun mainScreen() {

    //Column , Row , Box
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement
            .Center,
        horizontalAlignment = Alignment
            .CenterHorizontally
    ){
        CustomText("Hello Kotlin")
        Spacer(modifier = Modifier
            .padding(10.dp))
        CustomText("Hello Android")
        Spacer(modifier = Modifier
            .padding(10.dp))
        CustomText("Hello Java")

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .blur(1.dp)
                .padding(10.dp)
                .background(color = Color.Magenta),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CustomText("Hello World1")
            Spacer(modifier = Modifier
                .padding(10.dp))
            CustomText("Hello World2")
        }
    }

}

@Composable
fun CustomText(text : String){
    Text(modifier = Modifier
        .clickable {
            textClick()
        }
        .background(color = Color.Green)
        .padding(20.dp)
        .border(1.dp, color = Color.Green),
        text = "$text!",
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,

    )
}
fun textClick(){
    println("TEXT CLICKED")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinComposeTheme {
        mainScreen()
    }
}