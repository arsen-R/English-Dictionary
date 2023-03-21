package com.example.english_dictionary.presentation.screen.search

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.component.*
import com.example.english_dictionary.presentation.navigation.Screen
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
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
                onValueChange = {
                    viewModel.getSearchQuery(it)
                },
                onSearch = {
                    viewModel.onSearch()
                },
                onCleanSearchQuery = {
                    viewModel.getSearchQuery("")
                },
                onCloseSearch = {
                    viewModel.cleanSearchQuery()
                },
                topState = isSearched
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                Log.d("Search Request Api", "$isSearched | $uiState")
                if (isSearched) {
                    when (uiState) {
                        is SearchUiState.Loading -> {
                            LoadingProgress()
                        }
                        is SearchUiState.Success -> {
                            LazyColumn(modifier = modifier.fillMaxSize()) {
                                items(uiState.words.results!!) { word ->
                                    WordListItem(
                                        word = word!!,
                                        onClick = {
                                            navController.navigate(
                                                route = Screen.WordDetail.passWordId(
                                                    wordId = word.id!!
                                                )
                                            )
                                            viewModel.addLatestSearchedWord(word)
                                        }
                                    )
                                }
                            }
                        }
                        is SearchUiState.Error -> {
                            ErrorMessageButton(onRetryClick = {
                                viewModel.onSearch()
                            })
                        }
                    }
                } else {
                    val words =
                        viewModel.recentSearchWord.collectAsStateWithLifecycle().value
                    FlowRow(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        words.forEach { word ->
                            RecentWordSearch(word = word,
                                onClick = {
                                    navController.navigate(
                                        route = Screen.WordDetail.passWordId(
                                            wordId = word.id!!
                                        )
                                    )
                                    viewModel.addLatestSearchedWord(word)
                                }
                            )
                        }
                    }
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