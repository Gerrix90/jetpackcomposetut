package com.learn.composetest

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.learn.composetest.service.DataUtil


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val materialBlue700 = Color(0xFF1976D2)
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

            Content()


        }
    }


    @Composable
    fun Content() {
        var menuOpened by remember { mutableStateOf(false) }
        Scaffold(topBar = {
            TopAppBar(title = {
                Column {
                    Text("Title")
                    Text("Subtitle", style = MaterialTheme.typography.subtitle1)
                }
            },
                actions = {
                    var height = 0
                    Box(modifier = Modifier.onGloballyPositioned {
                        height = it.size.height
                    }) {
                        IconButton(onClick = {
                            menuOpened = true
                        }) {
                            Icon(Default.Search, null)
                            DropdownMenu(expanded = menuOpened,
                                offset = DpOffset(
                                    0.dp,
                                    -(LocalDensity.current.density * height).dp
                                ),
                                onDismissRequest = {
                                    menuOpened = false
                                }) {
                                DropdownMenuItem(onClick = {
                                    menuOpened = false
                                }) {
                                    Text("Item #1")
                                }
                                Divider()
                                DropdownMenuItem(onClick = {
                                    menuOpened = false
                                }) {
                                    Text("Item #2")
                                }
                            }
                        }
                        IconButton(onClick = {
                            menuOpened = true
                        }) {
                            Icon(Default.MoreVert, null)
                            DropdownMenu(expanded = menuOpened,
                                offset = DpOffset(
                                    0.dp,
                                    -(LocalDensity.current.density * height).dp
                                ),
                                onDismissRequest = {
                                    menuOpened = false
                                }) {
                                DropdownMenuItem(onClick = {
                                    menuOpened = false
                                }) {
                                    Text("Item #1")
                                }
                                Divider()
                                DropdownMenuItem(onClick = {
                                    menuOpened = false
                                }) {
                                    Text("Item #2")
                                }
                            }
                        }
                    }
                })
        }
        ) {
            MyContent()
        }
    }

    @Composable
    fun MyContent() {
        screenContent()
    }

    @Composable
    fun screenContent() {
        var context = LocalContext.current


        LazyColumn (
            contentPadding = PaddingValues(bottom=10.dp, top= 10.dp, start = 10.dp, end = 10.dp),

            ) {
            itemsIndexed(DataUtil.items) { index, item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {

                            var intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("student", index)
                            context.startActivity(intent)

                        }),
                    elevation = 8.dp

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
//                            .padding(16.dp)
                    ) {

                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = painterResource(R.drawable.person),
                            contentDescription = "Default person",
                            contentScale = ContentScale.Fit
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.Transparent,
                                            Color.Black
                                        ),
                                        startY = 300f
                                    )
                                )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Text(
                                "$item",
                                modifier = Modifier.padding(
                                    start = 0.dp,
                                    top = 16.dp,
                                    end = 8.dp,
                                    bottom = 0.dp
                                ), style = TextStyle(color = Color.White)
                            )
                        }


                    }
                }
                Box(modifier = Modifier.height(8.dp))
            }
        }
    }
}






