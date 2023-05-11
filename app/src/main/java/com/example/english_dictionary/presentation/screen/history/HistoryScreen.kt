package com.example.english_dictionary.presentation.screen.history

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.component.TopBar
import com.example.english_dictionary.presentation.component.SearchWordListItem
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
    val words = viewModel.uiState.collectAsStateWithLifecycle().value
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
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.recent_label),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (words.isNotEmpty()) {
                            ClickableText(
                                text = buildAnnotatedString {
                                    append(stringResource(id = R.string.clear_all_label))
                                },
                                onClick = { viewModel.deleteAllSearchedWord() })
                        }
                    }
                    if (words.isEmpty()) {
                        EmptyHistoryScreen()
                    }
                    words.forEach { word ->
                        SearchWordListItem(
                            word = word,
                            onNavigateTo = navController::toNavigateWordDetail,
                            onRecentWord = viewModel::addLatestSearchedWord
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun EmptyHistoryScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Your Recent list is empty",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Search for destinations and they will appear here.",
            fontSize = 14.sp
        )
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