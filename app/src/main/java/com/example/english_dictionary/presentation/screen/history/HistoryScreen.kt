package com.example.english_dictionary.presentation.screen.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.component.TopBar

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
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
                Text(text = stringResource(id = R.string.history_label))
            }
        }
    )
}