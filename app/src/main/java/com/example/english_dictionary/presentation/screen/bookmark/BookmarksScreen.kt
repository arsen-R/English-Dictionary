package com.example.english_dictionary.presentation.screen.bookmark

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.presentation.component.SearchWordListItem
import com.example.english_dictionary.presentation.component.TopBar
import com.example.english_dictionary.presentation.component.WordListItem
import com.example.english_dictionary.presentation.navigation.toNavigateWordDetail
import com.example.english_dictionary.presentation.screen.history.EmptyHistoryScreen
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier,
    navigate: NavController,
    onOpenDrawer: () -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    BookmarksScreen(
        modifier = modifier,
        onOpenDrawer = onOpenDrawer,
        navigate = navigate,
        words = viewModel.words.collectAsStateWithLifecycle().value,
        onRemoveWord = viewModel::removeWord
    )
}

@Composable
internal fun BookmarksScreen(
    modifier: Modifier = Modifier,
    navigate: NavController,
    onOpenDrawer: () -> Unit,
    words: List<Word>,
    onRemoveWord: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBar(
                openDrawer = onOpenDrawer,
                title = stringResource(id = R.string.bookmarks_label)
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    if (words.isEmpty()) {
                        EmptyBookmarkScreen()
                    }
                    words.forEach { word ->
                        WordListItem(
                            word = word,
                            onNavigateTo = navigate::toNavigateWordDetail,
                            onRemoveWord = onRemoveWord
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun EmptyBookmarkScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment =
        Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_bookmark_border_24),
            contentDescription = null
        )
        Text(
            "Your Bookmarks list is empty",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Search for definitions a tap the icon on any words to add to your bookmarks.",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BookmarksScreenPreview() {
    EnglishDictionaryTheme {
        BookmarksScreen(onOpenDrawer = {},
            navigate = rememberNavController(),
            words = listOf(
                Word(word = "Have")
            ),
            onRemoveWord = {})
    }
}