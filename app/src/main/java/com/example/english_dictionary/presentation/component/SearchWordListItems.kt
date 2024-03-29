package com.example.english_dictionary.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.domain.model.WordSearch
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import com.example.english_dictionary.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchWordListItem(
    modifier: Modifier = Modifier,
    word: WordSearch,
    onNavigateTo: (String) -> Unit,
    onRecentWord: (WordSearch) -> Unit
) {
    Card(
        onClick = {
            onNavigateTo(word.id!!)
            onRecentWord(word)
        },
        modifier = modifier.padding(horizontal = 5.dp, vertical = 3.dp),
        shape = RoundedCornerShape(10f),
        backgroundColor = MaterialTheme.colors.primarySurface
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = word.label!!)
            Icon(
                painter = painterResource(id = R.drawable.round_keyboard_arrow_right_24),
                contentDescription = null)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WordListItemPreview() {
    EnglishDictionaryTheme {
        val word = WordSearch(label = "Have to")
        SearchWordListItem(word = word, onNavigateTo = {}, onRecentWord = {})
    }
}