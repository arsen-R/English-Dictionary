package com.example.english_dictionary.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.domain.model.Entry
import com.example.english_dictionary.domain.model.Inflection

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InflectionRow(
    modifier: Modifier = Modifier,
    entry: Entry
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp
        ),
        verticalAlignment = Alignment.CenterVertically,
        maxItemsInEachRow = 3
    ) {

        entry.inflections?.forEach { inflection ->
            InflectionsItem(inflection = inflection)
        }

    }
}

@Composable
fun InflectionsItem(
    modifier: Modifier = Modifier,
    inflection: Inflection
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 2.dp
        )
    ) {
        Text(
            text = inflection.inflectedForm!!,
            fontWeight = FontWeight.Bold
        )
        val pronunciation = inflection.pronunciations?.map { it.phoneticSpelling }
        pronunciation?.joinToString(", ")?.let { Text(text = "[$it]") }
    }
}