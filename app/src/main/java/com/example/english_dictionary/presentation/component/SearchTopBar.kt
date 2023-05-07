package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onCleanSearchQuery: (String) -> Unit,
    onCloseSearch: () -> Unit,
    topState: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            AppTextField(
                query = query,
                onValueChange = onValueChange,
                onSearch = onSearch,
                onCleanSearchText = onCleanSearchQuery
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (topState) {
                    onCloseSearch()
                    keyboardController?.hide()
                } else {
                    openDrawer()
                }
            }) {
                Icon(
                    imageVector = if (topState) Icons.Default.ArrowBack else Icons.Default.Menu,
                    contentDescription = null,
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchTopBarPreview() {
    EnglishDictionaryTheme {
        SearchTopBar(
            openDrawer = {},
            query = "Live",
            onValueChange = {},
            onSearch = {},
            onCleanSearchQuery = {},
            onCloseSearch = {},
            topState = true
        )
    }
}