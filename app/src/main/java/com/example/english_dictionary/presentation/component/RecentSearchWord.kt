package com.example.english_dictionary.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.WordSearch
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecentWordSearch(
    modifier: Modifier = Modifier,
    words: List<WordSearch>,
    onNavigateTo: (String) -> Unit,
    onRecentWord: (WordSearch) -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        words.forEach { word ->
            Chip(
                onClick = {
                    onNavigateTo(word.id!!)
                    onRecentWord(word)
                },
                modifier
                    .padding(horizontal = 5.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_history_24),
                        contentDescription = null
                    )
                }
            ) {
                Text(text = word.label!!, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}


@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RecentWordSearchPreview() {
    EnglishDictionaryTheme {
        val words = WordSearch(label = "Have")
        RecentWordSearch(words = listOf(words), onNavigateTo = {}, onRecentWord = {})
    }
}