/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val puppyList = remember { mutableListOf<Puppy>() }
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))
    puppyList.add(Puppy(R.drawable.dog1, "Cute Dog", 20, "Chapi"))

    val selectedPuppy = mutableStateOf<Puppy?>(null)

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Toolbar(title = "Let's find your pal!")
            Details(selectedPuppy, modifier = Modifier.weight(1f))
            BottomCard(puppyList, selectedPuppy) {
                selectedPuppy.value = it
            }
        }
    }
}

@Composable
fun Details(selectedPuppy: MutableState<Puppy?>, modifier: Modifier) {
    if (selectedPuppy.value != null)
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(7.dp)
                    .size(300.dp, 300.dp)
                    .shadow(5.dp, CircleShape)
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .clip(CircleShape),
                painter = painterResource(selectedPuppy.value!!.imageRes),
                contentDescription = "${selectedPuppy.value!!.name} ${selectedPuppy.value!!.breed} ${selectedPuppy.value!!.ageMonths} months old",
                contentScale = ContentScale.Crop,
            )
            Text(
                selectedPuppy.value!!.name,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.sp
            )
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colors.secondary,
                elevation = 8.dp,
                modifier = Modifier.clickable {

                }
            ) {
                Text(
                    "Adopt Me",
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }
        }
}

@Composable
fun BottomCard(
    puppyList: MutableList<Puppy>,
    selectedPuppy: MutableState<Puppy?>,
    onPuppyClick: (puppy: Puppy) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(if (selectedPuppy.value != null) 0.2f else 1f)
        ) {
            PuppysList(puppyList, onPuppyClick)
        }
    }
}

@Composable
fun PuppysList(puppyList: MutableList<Puppy>, onPuppyClick: (puppy: Puppy) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Dragger()
        Box {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                puppyList.forEach {
                    PuppyItem(it, onPuppyClick)
                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.White, Color.Transparent)
                        )
                    )
                    .height(10.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun Dragger() {
    Box(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .height(3.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray)
    )
}

@Composable
fun PuppyItem(puppy: Puppy, onClick: (puppy: Puppy) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
            .alpha(alpha = if (puppy.selected) 0.5f else 1.0f)
            .clickable { onClick(puppy) }
    ) {
        Row {
            Image(
                modifier = Modifier
                    .padding(7.dp)
                    .size(120.dp, 120.dp)
                    .shadow(5.dp, CircleShape)
                    .border(0.5.dp, Color.LightGray, CircleShape)
                    .clip(CircleShape),
                painter = painterResource(puppy.imageRes),
                contentDescription = "${puppy.name} ${puppy.breed} ${puppy.ageMonths} months old",
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(120.dp)
                    .padding(10.dp)
                    .weight(1f, fill = true)
            ) {
                Text(
                    puppy.name,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Text(
                    puppy.breed,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Light
                )
                Text(
                    "${puppy.ageMonths} months",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
