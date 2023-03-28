package com.example.english_dictionary.presentation.screen.search

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.presentation.component.*
import com.example.english_dictionary.presentation.navigation.Screen
import com.example.english_dictionary.presentation.navigation.toNavigateWordDetail
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    navController: NavController
) {
    SearchScreen(
        onOpenDrawer = onOpenDrawer,
        navController = navController,
        modifier = modifier,
        viewModel = hiltViewModel()
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    viewModel: WordSearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val isSearched = viewModel.isSearched.value
    val uiState =
        viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(
                openDrawer = onOpenDrawer,
                query = viewModel.searchQuery.value,
                onValueChange = viewModel::getSearchQuery,
                onSearch = viewModel::onSearch,
                onCleanSearchQuery = viewModel::getSearchQuery,
                onCloseSearch = viewModel::cleanSearchQuery,
                topState = isSearched
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                Log.d("Search Request Api", "${viewModel.searchQuery.value} | $isSearched | $uiState")
                if (isSearched) {
                    when (uiState) {
                        is SearchUiState.Loading -> {
                            LoadingProgress()
                        }
                        is SearchUiState.Success -> if (uiState.words.results?.isNotEmpty()!!) {
                            LazyColumn(modifier = modifier.fillMaxSize()) {
                                items(uiState.words.results) { word ->
                                    WordListItem(
                                        word = word!!,
                                        onNavigateTo = navController::toNavigateWordDetail,
                                        onRecentWord = viewModel::addLatestSearchedWord
                                    )
                                }
                            }
                        } else {
                            Text("Empty")
                        }
                        is SearchUiState.Error -> {
                            ErrorMessageButton(onRetryClick = viewModel::onSearch)
                        }
                        is SearchUiState.Empty -> Unit
                    }
                } else {
                    val words =
                        viewModel.recentSearchWord.collectAsStateWithLifecycle().value
                    RecentWordSearch(
                        words = words,
                        onNavigateTo = navController::toNavigateWordDetail,
                        onRecentWord = viewModel::addLatestSearchedWord
                    )
                }
            }
        }
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SearchScreenPreview() {
    EnglishDictionaryTheme {
        SearchScreen(onOpenDrawer = {

        }, navController = rememberNavController())
    }
}