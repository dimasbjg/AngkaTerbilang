package com.dimdimbjg.angkaterbilang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimdimbjg.angkaterbilang.ui.theme.AngkaTerbilangTheme
import com.dimdimbjg.angkaterbilang.util.NumberToWords.convert

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AngkaTerbilangTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Converter()
                }
            }
        }
    }
}

@Composable
fun Converter() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ) {
            Text(text = "Sebut angka\n[max milyaran]",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
            )

            var textInput by rememberSaveable {
                mutableStateOf("0")
            }

            OutlinedTextField(
                value = textInput,
                onValueChange = { value ->
                    if (value.isEmpty()) {
                        textInput = "0"
                    } else if (value.length <= 15) {
                        textInput = value.filter { it.isDigit() }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(0.1.dp, Color.Black), shape = RoundedCornerShape(50)),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.Black)
            )

            Spacer(modifier = Modifier.padding(vertical = 7.5.dp))

            var text by remember { mutableStateOf("Hasil") }

            OutlinedButton(
                onClick = {
                    text = convert(textInput.toLong())
                },
                modifier = Modifier
                    .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(50))
                    .padding(0.dp)
                    .align(CenterHorizontally),
            ) {
                Text(text = "Input", color = Color.Black, fontSize = 20.sp)
            }

            Text(text = text,
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterHorizontally),
                textAlign = TextAlign.Center)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AngkaTerbilangTheme {
        Converter()
    }
}

