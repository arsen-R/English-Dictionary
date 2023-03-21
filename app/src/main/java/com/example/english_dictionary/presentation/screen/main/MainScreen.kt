package com.example.english_dictionary.presentation.screen.main

import android.content.res.Configuration
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.presentation.component.NavigationDrawer
import com.example.english_dictionary.presentation.navigation.NavGraph
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                currentRoute = currentRoute,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                })
        },
    ) {
        NavGraph(
            navHostController = navController,
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    EnglishDictionaryTheme {
        MainScreen()
    }
}
