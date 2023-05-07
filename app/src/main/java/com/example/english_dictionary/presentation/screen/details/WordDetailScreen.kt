package com.example.english_dictionary.presentation.screen.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.presentation.component.PronunciationItem
import com.example.english_dictionary.presentation.component.PronunciationsRow
import com.example.english_dictionary.presentation.tabs.TabItem
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun WordDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    WordDetailScreen(
        navController = navController,
        modifier = modifier,
        viewModel = hiltViewModel()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun WordDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: WordDetailViewModel
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }, title = {}
            )
        },
        modifier = modifier,
    ) {
        Box(modifier = modifier.padding(it)) {
            val pagerState = rememberPagerState()
            val coroutineState = rememberCoroutineScope()
            val tabList = listOf(
                TabItem("Definitions") { DefinitionScreen(viewModel = viewModel) },
                TabItem("Thesaurus") { ThesaurusScreen(viewModel = viewModel) },
            )

            Column(modifier = modifier.fillMaxWidth()) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                            color = MaterialTheme.colors.secondary
                        )
                    }
                ) {
                    tabList.forEachIndexed { index, tab ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineState.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = tab.title,
                                    color = MaterialTheme.colors.secondary
                                )
                            })
                    }
                }
                HorizontalPager(
                    count = tabList.size,
                    state = pagerState
                ) { page ->
                    tabList[page].screen()
                }
            }
        }
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WordDetailScreenPreview() {
    EnglishDictionaryTheme() {
        WordDetailScreen(navController = rememberNavController())
    }
}