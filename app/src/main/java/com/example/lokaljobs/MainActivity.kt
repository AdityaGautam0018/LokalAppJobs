package com.example.lokaljobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lokaljobs.screens.BookmarksScreen
import com.example.lokaljobs.screens.BottomBarScreen
import com.example.lokaljobs.screens.JobsScreen
import com.example.lokaljobs.ui.theme.Blue
import com.example.lokaljobs.ui.theme.LokalJobsTheme
import com.example.lokaljobs.viewmodel.JobsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: JobsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokalJobsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyBottomAppBar(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

@Composable
fun MyBottomAppBar(modifier: Modifier = Modifier, viewModel: JobsViewModel) {

    val navController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Blue) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navController.navigate(BottomBarScreen.Jobs.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.DarkGray
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navController.navigate(BottomBarScreen.Bookmarks.screen) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmarkfilled),
                        contentDescription = "",
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Favorite) Color.White else Color.DarkGray
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Jobs.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomBarScreen.Jobs.screen) { JobsScreen(viewModel) }
            composable(BottomBarScreen.Bookmarks.screen) { BookmarksScreen(viewModel) }
        }
    }
}
