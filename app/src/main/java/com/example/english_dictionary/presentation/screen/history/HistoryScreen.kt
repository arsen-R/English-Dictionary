package com.example.english_dictionary.presentation.screen.history

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.component.LoadingProgress
import com.example.english_dictionary.presentation.component.TopBar
import com.example.english_dictionary.presentation.component.WordListItem
import com.example.english_dictionary.presentation.navigation.Screen
import com.example.english_dictionary.presentation.navigation.toNavigateWordDetail
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    navController: NavController,
) {
    HistoryScreen(
        viewModel = hiltViewModel(),
        onOpenDrawer = onOpenDrawer,
        navController = navController,
        modifier = modifier
    )
}

@Composable
internal fun HistoryScreen(
    viewModel: HistoryViewModel,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBar(
                openDrawer = onOpenDrawer,
                title = stringResource(id = R.string.history_label)
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                when (uiState) {
                    HistoryUiState.Loading -> {
                        LoadingProgress()
                    }
                    is HistoryUiState.Success -> if (uiState.words.isNotEmpty()) {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            item {
                                Row(
                                    modifier = modifier.fillMaxWidth()
                                        .padding(horizontal = 20.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = stringResource(R.string.recent_label),
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stringResource(id = R.string.clear_all_label),
                                        modifier = modifier.clickable {

                                        }
                                    )
                                }
                            }
                            items(uiState.words) { word ->
                                WordListItem(
                                    word = word,
                                    onNavigateTo = navController::toNavigateWordDetail,
                                    onRecentWord = viewModel::addLatestSearchedWord
                                )
                            }
                        }
                    } else {

                    }
                }
            }
        }
    )
}

@Composable
fun EmptyHistoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(text = "Empty")
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(name = "Dark Theme", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EmptyHistoryScreenPreview() {
    EnglishDictionaryTheme {
        EmptyHistoryScreen()
    }
}