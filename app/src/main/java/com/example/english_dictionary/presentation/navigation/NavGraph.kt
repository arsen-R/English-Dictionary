package com.example.english_dictionary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.english_dictionary.domain.util.Constant
import com.example.english_dictionary.presentation.screen.bookmark.BookmarksScreen
import com.example.english_dictionary.presentation.screen.details.DetailScreen
import com.example.english_dictionary.presentation.screen.history.HistoryScreen
import com.example.english_dictionary.presentation.screen.search.SearchScreen
import com.example.english_dictionary.presentation.screen.settings.SettingsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    openDrawer: () -> Unit
) {
    NavHost(navController = navHostController, startDestination = Screen.Search.route) {
        composable(route = Screen.Search.route) {
            SearchScreen(
                onOpenDrawer = openDrawer,
                navController = navHostController
            )
        }
        composable(route = Screen.Bookmarks.route) {
            BookmarksScreen(
                onOpenDrawer = openDrawer
            )
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onOpenDrawer = openDrawer
            )
        }
        composable(route = Screen.History.route) {
            HistoryScreen(
                onOpenDrawer = openDrawer
            )
        }
        composable(
            route = Screen.WordDetail.route,
            arguments = listOf(
                navArgument(Constant.WORD_ARGUMENT_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(Constant.WORD_ARGUMENT_KEY)?.let {
                DetailScreen(wordId = it)
            }
        }
    }
}