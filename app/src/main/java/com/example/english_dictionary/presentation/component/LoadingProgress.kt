package com.example.english_dictionary.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun LoadingProgress(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(60.dp),
            backgroundColor = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview(name = "Light mode", showBackground = true)
@Preview(
    name = "Dark mode",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun LoadingProgressPreview() {
    EnglishDictionaryTheme {
        LoadingProgress()
    }
}