package com.example.english_dictionary.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.Word

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WordListItem(
    modifier: Modifier = Modifier,
    word: Word,
    onNavigateTo: (String) -> Unit,
    onRemoveWord: (String) -> Unit
) {
    Card(
        onClick = {
            onNavigateTo(word.id!!)
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
            Text(text = word.word!!)
            Icon(
                painter = painterResource(id = R.drawable.round_bookmark_24),
                contentDescription = null,
                modifier = modifier.clickable {
                    onRemoveWord(word.id!!)
                })
        }
    }
}