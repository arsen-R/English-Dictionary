package com.example.english_dictionary.presentation.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.Entry
import com.example.english_dictionary.domain.model.LexicalCategory

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun SynonymRow(
    modifier: Modifier = Modifier,
    entry: Entry,
    lexicalCategory: LexicalCategory?
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(id = R.string.synonym_label))
            }
            append(" | ")
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                append(lexicalCategory?.text)
            }
        },
        fontSize = 18.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 7.5.dp
            )
    )
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        entry.senses?.forEach { sense ->
            sense.synonyms?.forEach { synonym ->
                Chip(
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .padding(horizontal = 5.dp),
                ) {
                    Text(text = synonym.text!!)
                }
            }
        }
    }
}