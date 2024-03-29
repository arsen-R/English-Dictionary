package com.example.english_dictionary.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.english_dictionary.domain.util.Constant
import com.example.english_dictionary.presentation.screen.bookmark.BookmarksScreen
import com.example.english_dictionary.presentation.screen.details.WordDetailScreen
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
                onOpenDrawer = openDrawer,
                navigate = navHostController
            )
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onOpenDrawer = openDrawer,
                navController = navHostController
            )
        }
        composable(route = Screen.History.route) {
            HistoryScreen(
                onOpenDrawer = openDrawer,
                navController = navHostController
            )
        }
        composable(
            route = Screen.WordDetail.route,
            arguments = listOf(
                navArgument(Constant.WORD_ARGUMENT_KEY) { type = NavType.StringType }
            )
        ) {
            WordDetailScreen(navController = navHostController)
        }
    }
}

internal class WordArgs(val wordId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[Constant.WORD_ARGUMENT_KEY]) as String)
}

internal class SourceLangArgs(val sourceLang: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[Constant.SOURCE_LANG_ARG_KEY]) as Int)
}

fun NavController.toNavigateWordDetail(wordId: String) {
    this.navigate(Screen.WordDetail.passWordId(wordId))
}